package tech.feathers.krispy.context;

import java.util.HashMap;
import java.util.Map;

public class AppSyncContextPrev {
    private Map<String, Object> prevMap;

    public AppSyncContextPrev() {
        this(new AppSyncContextResult());
    }

    public AppSyncContextPrev(AppSyncContextResult prevResult) {
        this.prevMap = new HashMap<String, Object>();
        prevMap.put("result", prevResult.getResultMap());
    }

    public Map<String, Object> getPrevMap() {
        return this.prevMap;
    }
}