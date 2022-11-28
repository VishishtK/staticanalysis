package com.cns.staticanalysis;

import java.io.File;
import java.util.ArrayList;

public class TargetSDK {

    ArrayList<File> files;
    String fileExtension;

    public TargetSDK(String pathToSDK, String fileExtension) {
        this.fileExtension = fileExtension;
        files = new ArrayList<>();

        listFilesForFolder(new File(pathToSDK));
    }

    private void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else if (isJava(fileEntry)) {
                files.add(fileEntry);
                // System.out.println(fileEntry.getName());
            }
        }
    }

    private boolean isJava(File file) {
        return file.getName().endsWith(fileExtension);
    }

}
