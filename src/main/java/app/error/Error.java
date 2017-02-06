package app.error;

public class Error {

    private int status;

    private String message;

    public Error(int status, String message) {
        this.status = status;
        this.message = message;
    }

}