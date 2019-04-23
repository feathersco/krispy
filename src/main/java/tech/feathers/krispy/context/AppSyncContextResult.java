package tech.feathers.krispy.context;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class AppSyncContextResult {
    private Map<String, Object> result;

    public AppSyncContextResult() {
        this(null);
    }

    public AppSyncContextResult(Map<String, Object> result) {
        this.result = new HashMap<String, Object>();
        if (result != null) {
            for (Entry<String, Object> kv: result.entrySet()) {
                this.put(kv.getKey(), kv.getValue());
            }
        }
    }

    public void put(String argName, Object argVal) {
        result.put(argName, argVal);
    }

    public Map<String, Object> getResultMap() {
        return result;
    }
}