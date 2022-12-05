package com.cns.staticanalysis;

import java.io.File;
import java.util.ArrayList;

public class TargetSDK {

    ArrayList<File> files;
    ArrayList<AnalyseFile> analysedFiles;
    String fileExtension;
    String pathToSDK;

    long totalCriticalVars;
    long totalCriticalFields;
    long totalCriticalMethods;

    long totalSafeCriticalVars;
    long totalSafeCriticalFields;
    long totalSafeCriticalMethods;

    public TargetSDK(String pathToSDK, String fileExtension) {
        this.pathToSDK = pathToSDK;
        this.fileExtension = fileExtension;
        files = new ArrayList<>();

        listFilesForFolder(new File(pathToSDK));
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void Analyse() {
        switch (fileExtension) {
            case ".java":
                for (File file : files) {
                    AnalyseJavaFile analyseFile = new AnalyseJavaFile(file.getPath());
                    analyseFile.Analyse();
                    analysedFiles.add(analyseFile);
                }
                break;
            case ".go":
                for (File file : files) {
                    AnalyseGolangFile analyseFile = new AnalyseGolangFile(file.getPath());
                    analyseFile.Analyse();
                    analysedFiles.add(analyseFile);
                }
                break;
            default:
                System.out.println("Language not supported");

        }

    }

    private void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else if (isSourceFile(fileEntry)) {
                files.add(fileEntry);
                // System.out.println(fileEntry.getPath());
            }
        }
    }

    private boolean isSourceFile(File file) {
        return file.getName().endsWith(fileExtension);
    }

}
