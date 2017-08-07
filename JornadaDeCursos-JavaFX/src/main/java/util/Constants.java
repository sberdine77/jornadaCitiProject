package util;

import java.nio.file.Paths;

/**
 * Created by pedro on 5/21/17.
 */
public final class Constants {
    public static final String PERSISTENT_DIRECTORY = Paths.get(Utilities.defaultDirectory(), "Jornada de Cursos").toString();

    private Constants() {
        // Avoid class instantiation
    }
}
