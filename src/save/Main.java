package save;

import java.util.ArrayList;

import static fileManager.FileManager.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<GameProgress> saves = new ArrayList<>();
        ArrayList<String> fileList = new ArrayList<>();
        saves.add(new GameProgress(12, 25, 10, 300));
        saves.add(new GameProgress(42, 250, 12, 3000));
        saves.add(new GameProgress(100, 95, 70, 4500));

        for (int i = 0; i < saves.size(); i++) {
            if (saveGame(("\\savegames\\save" + i + ".dat"), saves.get(i))) {
                temp
                        .append("Good work! File: " + FILE_DIRECTORY)
                        .append("\\savegames\\save")
                        .append(i)
                        .append(".dat")
                        .append(" - created\n");
                fileList.add(FILE_DIRECTORY + "\\savegames\\save" + i + ".dat");
            } else
                temp
                        .append("Error File: " + FILE_DIRECTORY)
                        .append("\\savegames\\save")
                        .append(i)
                        .append(".dat")
                        .append(" - not created\n");
        }
        zipFiles((FILE_DIRECTORY + "\\savegames\\saves.zip"), fileList);
        write("Saving & Zipping");
    }
}
