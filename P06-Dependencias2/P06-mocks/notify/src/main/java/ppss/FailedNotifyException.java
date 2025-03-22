package ppss;

public class FailedNotifyException extends Exception {
    public FailedNotifyException(String dateError) {
        super(dateError);
    }
}
