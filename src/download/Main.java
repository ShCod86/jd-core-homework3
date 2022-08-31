package download;

import static fileManager.FileManager.*;

public class Main {
    public static void main(String[] args) {
        openZip(FILE_DIRECTORY + "\\savegames\\saves.zip", FILE_DIRECTORY + "\\savegames");
        openProgress(FILE_DIRECTORY + "\\savegames\\save0.dat");
        write("Download");
    }

}
