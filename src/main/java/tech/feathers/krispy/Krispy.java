package tech.feathers.krispy;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.IOException;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;

import tech.feathers.krispy.context.AppSyncContext;
import tech.feathers.krispy.util.BaseUtilities;

public class Krispy {
    private BaseUtilities baseUtilities;

    public static void init() {
        Velocity.init();
    }

    public Krispy() {
        this(new BaseUtilities());
    }

    public Krispy(BaseUtilities baseUtilities) {
        this.baseUtilities = baseUtilities;
    }

    public String renderToString(String templatePath) throws IOException {
        return renderToString(templatePath, new AppSyncContext());
    }

    public String renderToString(String templatePath, AppSyncContext context) throws IOException {
        BufferedReader r = new BufferedReader(render(templatePath, context));
        String result = "";
        String line;
        while ((line = r.readLine()) != null) {
            result += line;
        }
        return result;
    }
    
    /**
     * Renders the VTL file at the given path.
     * @param templatePath  Location of the VTL file to render.
     * @return Output of the evaluated VTL.
     */
    public Reader render(String templatePath) throws IOException {
        return render(templatePath, new AppSyncContext());
    }

    /**
     * Renders the VTL file at the given path.
     * @param templatePath  Location of the VTL file to render.
     * @param context       AppSync context object to use.
     * @return Output of the evaluated VTL.
     */
    public Reader render(String templatePath, AppSyncContext context) throws IOException {
        StringWriter writer = new StringWriter();

        VelocityContext vc = createVelocityContext(context);

        Template template = Velocity.getTemplate(templatePath);

        template.merge(vc, writer);

        return new StringReader(writer.toString());
    }

    private VelocityContext createVelocityContext(AppSyncContext context) {
        VelocityContext vc = new VelocityContext();
        vc.put("util", baseUtilities);
        vc.put("utils", baseUtilities);
        vc.put("context", context);
        vc.put("ctx", context);

        return vc;
    }
}
