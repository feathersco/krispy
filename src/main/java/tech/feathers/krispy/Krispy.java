package tech.feathers.krispy;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;

import tech.feathers.krispy.util.BaseUtilities;

public class Krispy {
    private static BaseUtilities baseUtilities;

    public static void init() {
        Velocity.init();

        baseUtilities = new BaseUtilities();
    }

    /**
     * Renders the VTL file at the given path.
     * @param templatePath Location of the VTL file to render.
     * @return Output of the evaluated VTL.
     */
    public String render(String templatePath) {
        StringWriter sw = new StringWriter();
        VelocityContext context = new VelocityContext();
        setupContext(context);

        Template template = Velocity.getTemplate(templatePath);

        template.merge(context, sw);

        return sw.toString();
    }

    private void setupContext(VelocityContext context) {
        context.put("util", baseUtilities);
    }
}
