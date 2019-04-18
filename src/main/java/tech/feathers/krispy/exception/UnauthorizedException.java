package tech.feathers.krispy.exception;

public class UnauthorizedException extends Exception {
    static final long serialVersionUID = 403L;

    public String toString() {
        return "unauthorized";
    }
}