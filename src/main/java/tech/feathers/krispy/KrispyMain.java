package tech.feathers.krispy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tech.feathers.krispy.context.AppSyncContext;
import tech.feathers.krispy.context.AppSyncContextArgs;
import tech.feathers.krispy.context.AppSyncContextIdentity;
import tech.feathers.krispy.diff.AddedDiff;
import tech.feathers.krispy.diff.JsonDiff;
import tech.feathers.krispy.diff.JsonDiffer;

public class KrispyMain {

    // TODO: Implement render and compare
    // TODO: Create test file format
    // TODO: Implement snapshotting
    // TODO: Implement interactive mode
    public static void main(String[] args) throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        Options options = getOptions();
        CommandLine cl;
        try {
            cl = parser.parse(options, args);
        } catch (ParseException pe) { 
            System.err.println(pe.getMessage());
            formatter.printHelp("krispy", options);
            throw pe;
        }

        if (cl.hasOption("h")) {
            formatter.printHelp("krispy", options);
            return;
        }

        List<File> files = new ArrayList<File>();
        String[] paths = cl.getArgs().length > 0 ? cl.getArgs() : new String[]{ "." };
        for (String path: paths) {
            Files.walk(Paths.get(path))
                .map(p -> p.toFile())
                .filter((f) -> f.getName().endsWith(".krispy.yml"))
                .forEach((f) -> files.add(f));
        }

        Path templateRoot = Paths.get(cl.getOptionValue("t", "templates/"));
        if (Files.notExists(templateRoot)) {
            System.err.println("Given template root " + templateRoot + " does not exist.");
            throw new FileNotFoundException();
        }

        Path snapshotRoot = Paths.get(cl.getOptionValue("s", "snapshots/"));
        if (Files.notExists(snapshotRoot)) {
            Files.createDirectory(snapshotRoot);
        }

        int passed = 0;
        int warning = 0;
        int failed = 0;
        int updated = 0;

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Krispy krispy = new Krispy();
        JsonDiffer differ = new JsonDiffer();
        for (File file: files) {
            try {
                KrispyCase testCase = mapper.readValue(file, KrispyCase.class);
                String title = "CASE : " + testCase.getTemplateFile() + (testCase.getDescription() != null ? " - " + testCase.getDescription() : "");
                System.out.println(title);
                if (testCase.getTemplateFile() == null) {
                    System.out.println("\t[FAILED] No template file was specified.");
                    failed++;
                    continue;
                }

                AppSyncContext context = new AppSyncContext(
                    new AppSyncContextIdentity(testCase.getCognitoIdentityId()), 
                    new AppSyncContextArgs(testCase.getArgs())
                );

                File template = templateRoot.resolve(testCase.getTemplateFile()).toFile();
                if (!template.exists()) {
                    System.err.println("\t[FAILED] Template file could not be found.");
                    failed++;
                    continue;
                }
                Reader actual = krispy.render(template.getPath(), context);

                File snapshot = snapshotRoot.resolve(testCase.getTemplateFile() + ".snapshot").toFile();
                if (snapshot.exists()) {
                    Reader expected = new FileReader(snapshot);
                    List<JsonDiff> diffs = differ.diff(expected, actual);
                    if (diffs.size() == 0) {
                        System.out.println("\t[PASSED]");
                        passed++;
                        continue;
                    }

                    boolean hasNonAdds = false;
                    for (JsonDiff diff: diffs) {
                        if (!(diff instanceof AddedDiff)) {
                            hasNonAdds = true;
                        }
                        System.out.println("\t" + diff.toString());
                    }

                    if (!hasNonAdds && testCase.getWarnOnAdd()) {
                        System.out.println("\t[WARNING] Diffs were detected, but changes were strictly additive.");
                        warning++;
                        continue;
                    } else if (cl.hasOption("u")) {
                        System.out.println("\tDiffs were detected, but --update-snapshots is set.");
                    } else {
                        System.out.println("\t[FAILED] Diffs were detected.");
                        failed++;
                        continue;
                    }
                } else if (cl.hasOption("u")) {
                    System.out.println("\tNo existing snapshot found, but --update-snapshots is set.");
                    Files.createDirectories(Paths.get(snapshot.getParent()));
                } else {
                    System.out.println("\t[FAILED] No snapshot found!");
                    failed++;
                    continue;
                }

                if (cl.hasOption("u")) {
                    System.out.println("\tAttempting to update snapshot...");
                    BufferedWriter bw = new BufferedWriter(new FileWriter(snapshot, false));
                    BufferedReader br = new BufferedReader(actual);
                    
                    String line;
                    while ((line = br.readLine()) != null) {
                        bw.write(line);
                    }
                    bw.close();

                    updated++;
                    System.out.println("\t[UPDATED] snapshot has been updated");
                }
            } catch (JsonParseException jpe) {
                System.err.println(file.getAbsolutePath() + " could not be parsed as YAML.");
                return;
            } catch (JsonMappingException jme) {
                System.err.println(file.getAbsolutePath() + " does not match the expected Krispy file schema.");
                return;
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
                return;
            }
        }

        System.out.println("====================");
        System.out.println("RESULTS");
        System.out.println("====================");
        System.out.println(passed + " PASSED");
        System.out.println(warning + " WARNING");
        System.out.println(updated + " UPDATED");
        System.out.println(failed + " FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }

    private static Options getOptions() {
        Options options = new Options();

        options.addOption("h", "help", false, "Displays usage information.");
        options.addOption("u", "update-snapshots", false, "Update snapshots that do not match.");
        options.addOption("t", "template-root", true, "Specify the root directory of the template files.");
        options.addOption("s", "snapshot-root", true, "Specify the root directory of the snapshot files.");

        return options;
    }
}