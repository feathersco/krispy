package tech.feathers.krispy.context;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class AppSyncContextArgs {
    private Map<String, Object> args;

    public AppSyncContextArgs() {
        this(null);
    }

    public AppSyncContextArgs(Map<String, Object> args) {
        this.args = new HashMap<String, Object>();
        if (args != null) {
            for (Entry<String, Object> arg: args.entrySet()) {
                this.putArg(arg.getKey(), arg.getValue());
            }
        }
    }

    public void putArg(String argName, Object argVal) {
        args.put(argName, argVal);
    }

    public Map<String, Object> getArgsMap() {
        return args;
    }
}