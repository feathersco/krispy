package tech.feathers.krispy.context;

import java.util.Map;
import java.util.HashMap;

public class AppSyncContextArgs {
    private Map<String, Object> args;

    public AppSyncContextArgs() {
        args = new HashMap<String, Object>();
    }

    public void putArg(String argName, Object argVal) {
        args.put(argName, argVal);
    }

    public Map<String, Object> getArgsMap() {
        return args;
    }
}