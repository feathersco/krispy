package tech.feathers.krispy.util;

/**
 * Provides the current time to be used by utility functions.
 */
public interface TimeProvider {
    /**
     * @return Current system time in milliseconds from epoch.
     */
    long nowMillis();
}