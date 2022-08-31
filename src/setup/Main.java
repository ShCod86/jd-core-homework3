package setup;

import java.util.Arrays;
import java.util.List;

import static fileManager.FileManager.*;


public class Main {
    private static List<String> pathList = Arrays.asList("src\\main", "src\\test",
            "res\\drawables", "res\\icons", "res\\vectors",
            "savegames", "temp");
    private static List<String> fileList = Arrays.asList("temp\\temp.txt", "src\\main\\Main.java", "src\\main\\Utils.java");

    public static void main(String[] args) {
        for (String folder : pathList)
            newFolder(folder);
        for (String file : fileList)
            newFile(file);
        write("Setup Game");
    }
}
