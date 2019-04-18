package tech.feathers.krispy.exception;

public class EvaluationException extends Exception {
    static final long serialVersionUID = 400L;

    private String message;

    public EvaluationException(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}