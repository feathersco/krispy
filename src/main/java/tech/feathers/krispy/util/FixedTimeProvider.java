package tech.feathers.krispy.util;

/**
 * Time provider to use for testing purposes.
 */
public class FixedTimeProvider implements TimeProvider {
    private long fixedTimeMs;

    public FixedTimeProvider(long fixedTimeMs) {
        this.fixedTimeMs = fixedTimeMs;
    }

    public long nowMillis() {
        return fixedTimeMs;
    }
}