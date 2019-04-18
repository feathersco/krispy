package tech.feathers.krispy.util;

import tech.feathers.krispy.exceptions.EvaluationException;
import tech.feathers.krispy.exceptions.UnauthorizedException;

import org.apache.commons.lang3.NotImplementedException; 

/**
 * Implementation of the AWS AppSync resolver mapping template utility methods.
 */
public class BaseUtilities {
    private TimeUtilities time;
    private DynamoDBUtilities dynamodb;

    public BaseUtilities() {
        this(new TimeUtilities(), new DynamoDBUtilities());
    }

    public BaseUtilities(TimeUtilities time, DynamoDBUtilities dynamodb) {
        this.time = time;
        this.dynamodb = dynamodb;
    }

    /**
     * @return time utility functions.
     */
    public TimeUtilities getTime() { return time; }

    /**
     * @return dynamodb utility functions.
     */
    public DynamoDBUtilities getDynamodb() { return dynamodb; }

    /**
     * Used as a wrapper to supress the return value of a method call from being outputed to the template. Functionally equivalent to `qiet`.
     * @param data Any value. Typically the result of a method call.
     */
    public void qr(Object data) {}

    /**
     * Used as a wrapper to supress the return value of a method call from being outputed to the template. Functionally equivalent to `qiet`.
     * @param data Any value. Typically the result of a method call.
     */
    public void qiet(Object data) {}

    public String escapeJavaScript(String str) { throw new NotImplementedException("Not yet implemented."); }

    public String urlEncode(String url) { throw new NotImplementedException("Not yet implemented."); }

    public String urlDecode(String url) { throw new NotImplementedException("Not yet implemented."); }

    public String base64Encode(byte[] b) { throw new NotImplementedException("Not yet implemented."); }

    public byte[] base64Decode(String encodedStr) { throw new NotImplementedException("Not yet implemented."); }

    public Object parseJson(String jsonStr) { throw new NotImplementedException("Not yet implemented."); }

    public String toJson(Object o) { throw new NotImplementedException("Not yet implemented."); }

    public String autoId() { throw new NotImplementedException("Not yet implemented."); }

    /**
     * @throws UnauthorizedException
     */
    public void unauthorized() throws UnauthorizedException { 
        throw new UnauthorizedException(); 
    }

    /**
     * @throws EvaluationException Generic error with the given message.
     */
    public void error(String message) throws EvaluationException {
        throw new EvaluationException(message); 
    }

    public void error(String message, String errorType) { throw new NotImplementedException("Not yet implemented."); }

    public void error(String message, String errorType, Object data) { throw new NotImplementedException("Not yet implemented."); }

    public void error(String message, String errorType, Object data, Object errorInfo) { throw new NotImplementedException("Not yet implemented."); }

    public void appendError(String message) { throw new NotImplementedException("Not yet implemented."); }

    public void appendError(String message, String errorType) { throw new NotImplementedException("Not yet implemented."); }

    public void appendError(String message, String errorType, Object data) { throw new NotImplementedException("Not yet implemented."); }

    public void appendError(String message, String errorType, Object data, Object errorInfo) { throw new NotImplementedException("Not yet implemented."); }

    public void validate(boolean condition, String message) { throw new NotImplementedException("Not yet implemented."); }

    public void validate(boolean condition, String message, String errorType) { throw new NotImplementedException("Not yet implemented."); }

    public void validate(boolean condition, String message, String errorType, Object data) { throw new NotImplementedException("Not yet implemented."); }

    /**
     * @param o Object to check.
     * @return True if the given object is null, and false otherwise.
     */
    public boolean isNull(Object o) {
        return o == null;
    }

    /**
     * @param str String to check.
     * @return True if the given string is either null or the empty string, and false otherwise.
     */
    public boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }

    /**
     * @param str String to check.
     * @return True if the given string is either null or blank, and false otherwise.
     */
    public boolean isNullOrBlank(String str) {
        return str == null || str.trim().equals("");
    }

    /**
     * @param o Object to check.
     * @return The original object if the given object is null, and the fallback object otherwise.
     */
    public Object defaultIfNull(Object o, Object fallback) {
        return isNull(o) ? fallback : o;
    }

    /**
     * @param str String to check.
     * @return The original string if the given string is either null or the empty string, and the fallback string otherwise.
     */
    public String defaultIfNullOrEmpty(String str, String fallback) {
        return isNullOrEmpty(str) ? fallback : str;
    }

    /**
     * @param str String to check.
     * @return The original string if the given string is either null or blank, and the fallback string otherwise.
     */
    public String defaultIfNullOrBlank(String str, String fallback) {
        return isNullOrBlank(str) ? fallback : str;
    }

    public boolean isString(Object o) { throw new NotImplementedException("Not yet implemented."); }

    public boolean isNumber(Object o) { throw new NotImplementedException("Not yet implemented."); }

    public boolean isBoolean(Object o) { throw new NotImplementedException("Not yet implemented."); }

    public boolean isList(Object o) { throw new NotImplementedException("Not yet implemented."); }

    public boolean isMap(Object o) { throw new NotImplementedException("Not yet implemented."); }

    public String typeOf(Object o) { throw new NotImplementedException("Not yet implemented."); }

    public boolean matches(String pattern, String str) { throw new NotImplementedException("Not yet implemented."); }
}