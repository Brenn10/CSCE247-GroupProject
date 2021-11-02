/**
 * Starts the Neurotic Job Search
 * @author Brennan Cain
 */
import utilities.Logger;
public class JobSystemDriver {
    public static void main(String[] args) {
        Logger.getInstance().doLogging(true);
        new MainUI().doMainMenu();
    }
}
