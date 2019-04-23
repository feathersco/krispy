package tech.feathers.krispy.context;

import java.util.Map;

public class AppSyncContext {
    private AppSyncContextIdentity identity;
    private AppSyncContextArgs arguments;
    private AppSyncContextResult result;
    private AppSyncContextPrev prev;

    public AppSyncContext() {
        this(new AppSyncContextIdentity(null), new AppSyncContextArgs(), new AppSyncContextResult(), new AppSyncContextPrev());
    }

    public AppSyncContext(AppSyncContextIdentity identity, AppSyncContextArgs arguments, AppSyncContextResult result, AppSyncContextPrev prev) {
        this.identity = identity;
        this.arguments = arguments;
        this.result = result;
        this.prev = prev;
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

    public Map<String, Object> getResult() {
        return result.getResultMap();
    }

    public AppSyncContextPrev getPrev() {
        return prev;
    }
}