package com.cns.staticanalysis;

public class App {
    public static void main(String[] args) {

        // TargetSDK sdk = new
        // TargetSDK("/Users/vishy/Desktop/CNS-Project-SDK/dropbox-sdk-java/", ".java");
        // sdk.Analyse();

        // TargetSDK sdk2 = new
        // TargetSDK("/Users/vishy/Desktop/CNS-Project-SDK/OneDrive-SDK-java/",".java");
        // sdk2.Analyse();

        TargetSDK sdk = new TargetSDK("/Users/vishy/Desktop/CNS-Project-SDK/dropbox-sdk-go-unofficial", ".go");
        System.out.println("Loading SDK: " + sdk.pathToSDK);

        System.out.println("Analysing SDK");
        sdk.Analyse();
        sdk.GetResult();

    }
}
