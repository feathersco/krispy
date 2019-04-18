package tech.feathers.krispy.util;

/**
 * Uses the system time to provide the time.
 */
public class SystemTimeProvider implements TimeProvider {
    public long nowMillis() {
        return System.currentTimeMillis();
    }
}