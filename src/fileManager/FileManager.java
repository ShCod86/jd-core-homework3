package fileManager;

import save.GameProgress;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileManager {
    public static final String FILE_DIRECTORY = "C:\\Games";
    public static StringBuilder temp = new StringBuilder();

    public static void write(String whatIsRecorded) {
        try (FileWriter writer = new FileWriter(FILE_DIRECTORY + "\\temp\\temp.txt", true)) {
            writer.append("\n=================\n");
            writer.append(whatIsRecorded);
            writer.append("\n=================\n");
            writer.append("\n");
            writer.write(temp.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void newFolder(String name) {
        File folder = new File(FILE_DIRECTORY, name);
        writeLog(temp, folder, folder.mkdirs());
    }

    public static void newFile(String name) {
        File file = new File(FILE_DIRECTORY, name);
        try {
            writeLog(temp, file, file.createNewFile());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeLog(StringBuilder temp, File file, boolean b) {
        temp
                .append(b ? "Good work " : "Error ")
                .append(file.isDirectory() ? "Folder: " : "File: ")
                .append(file.getAbsolutePath())
                .append(b ? " - created" : " - not created")
                .append("\n");
    }

    public static boolean saveGame(String name, GameProgress prog) {
        boolean res = false;
        try (ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(FILE_DIRECTORY + name))) {
            res = true;
            save.writeObject(prog);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static void zipFiles(String zipName, ArrayList<String> fileList) {
        try (ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream(zipName))) {
            for (int i = 0; i < fileList.size(); i++) {
                try (FileInputStream fis = new FileInputStream(fileList.get(i))) {
                    ZipEntry entry = new ZipEntry("save" + i + ".dat");
                    zipFile.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zipFile.write(buffer);
                    zipFile.closeEntry();
                    temp
                            .append("Good work! File: ").append(fileList.get(i))
                            .append(" added to archive ")
                            .append(zipName)
                            .append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        fileDelete(fileList);
    }

    public static void fileDelete(ArrayList<String> files) {
        for (String file : files) {
            File tempFile = new File(file);
            if (tempFile.delete())
                temp.append("Good work! File: ").append(file).append(" deleted\n");
            else
                temp.append("Error File: ").append(file).append(" not found\n");
        }
    }

    public static void fileDelete(String file) {
        File tempFile = new File(file);
        if (tempFile.delete())
            temp.append("Good work! File: ").append(file).append(" deleted\n");
        else
            temp.append("Error File: ").append(file).append(" not found\n");

    }

    public static void openZip(String pathFile, String pathFolder) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                try (FileOutputStream fout = new FileOutputStream(pathFolder + "\\" + name)) {
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }
                    fout.flush();
                    zin.closeEntry();
                    temp
                            .append("Good work! File: ").append(name)
                            .append(" extracted from the archive ")
                            .append(pathFile)
                            .append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        fileDelete(pathFile);
    }

    public static void openProgress(String path) {
        GameProgress gameProgress = null;
        try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(path))) {
            gameProgress = (GameProgress) obj.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        temp
                .append("Good work! File: ").append(path)
                .append(" deserialized")
                .append("\n");
        System.out.println(gameProgress);
    }
}

