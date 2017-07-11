package util;

/**
 * Created by chris on 6/27/17.
 */
public final class LOG {

    public static String path = Configuration.getLogDirectory();

    public static String generateFileName()
    {
        return null;
    }

    public static void info(String entry)
    {
        System.out.println("\n[INFO] " + entry);
    }
}
