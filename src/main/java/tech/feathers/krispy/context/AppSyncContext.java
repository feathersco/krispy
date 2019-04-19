package tech.feathers.krispy.context;

import java.util.Map;

public class AppSyncContext {
    private AppSyncContextIdentity identity;
    private AppSyncContextArgs arguments;

    public AppSyncContext() {
        this(new AppSyncContextIdentity(null), new AppSyncContextArgs());
    }

    public AppSyncContext(AppSyncContextIdentity identity, AppSyncContextArgs arguments) {
        this.identity = identity;
        this.arguments = arguments;
    }

    public AppSyncContextIdentity getIdentity() {
        return identity;
    }

    public Map<String, Object> getArguments() {
        return arguments.getArgsMap();
    }

    public Map<String, Object> getArgs() {
        return arguments.getArgsMap();
    }
}