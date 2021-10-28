package utilities;

public class Logger {
    private static Logger instance;
    private boolean loggingEnabled;

    private Logger() {
        loggingEnabled = false;
    }

    public static Logger getInstance() {
        if(instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        if(loggingEnabled) {
            System.out.println(message);
        }
    }

    public void doLogging(boolean enable) {
        loggingEnabled = enable;
    }
}
