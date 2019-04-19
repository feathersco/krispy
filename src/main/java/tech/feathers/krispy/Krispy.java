package tech.feathers.krispy;

import java.io.StringWriter;

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

    /**
     * Renders the VTL file at the given path.
     * @param templatePath  Location of the VTL file to render.
     * @return Output of the evaluated VTL.
     */
    public String render(String templatePath) {
        return render(templatePath, new AppSyncContext());
    }

    /**
     * Renders the VTL file at the given path.
     * @param templatePath  Location of the VTL file to render.
     * @param context       AppSync context object to use.
     * @return Output of the evaluated VTL.
     */
    public String render(String templatePath, AppSyncContext context) {
        StringWriter sw = new StringWriter();
        VelocityContext vc = createVelocityContext(context);

        Template template = Velocity.getTemplate(templatePath);

        template.merge(vc, sw);

        return sw.toString();
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
