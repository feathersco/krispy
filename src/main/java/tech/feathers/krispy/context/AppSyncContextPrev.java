package tech.feathers.krispy.context;

import java.util.Map;

public class AppSyncContextPrev {
    private AppSyncContextResult result;

    public AppSyncContextPrev() {
        this(null);
    }

    public AppSyncContextPrev(AppSyncContextResult prevResult) {
        this.result = prevResult;
    }

    public Map<String, Object> getResult() {
        return result.getResultMap();
    }
}