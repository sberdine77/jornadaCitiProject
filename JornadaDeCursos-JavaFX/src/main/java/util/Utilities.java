package util;

import com.sun.istack.internal.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by phts on 14/06/17.
 */
public class Utilities {
    private Utilities() {
        // Avoid class instantiation
    }

    public static synchronized void closeCloseables(@NotNull Closeable... closeables) {
        for (Closeable closeable : closeables)
            if (closeable != null)
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
    }

    public static String defaultDirectory() {
        String os = System.getProperty("os.name").toUpperCase();

        if (os.contains("WIN"))
            return System.getenv("APPDATA");
        else if (os.contains("MAC"))
            return Paths.get(System.getProperty("user.home"), "Library", "Application Support").toString();
        else
            return Paths.get(System.getProperty("user.home"), ".local", "share").toString();
    }

    public static String md5(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        messageDigest.update(s.getBytes(), 0, s.length());
        return Utilities.bytesToHexString(messageDigest.digest());
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes)
            stringBuilder.append(String.format("%02x", b));
        return stringBuilder.toString();
    }
}
