package tech.feathers.krispy.exceptions;

public class UnauthorizedException extends Exception {
    static final long serialVersionUID = 403L;

    public String toString() {
        return "unauthorized";
    }
}