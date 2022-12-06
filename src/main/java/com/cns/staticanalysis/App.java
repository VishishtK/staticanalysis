package com.cns.staticanalysis;

public class App {
    public static void main(String[] args) {

        TargetSDK sdk = new TargetSDK(args[0], "."+args[1]);
        sdk.Analyse();
        sdk.GetResult();
        sdk.Output();

        // TargetSDK sdk2 = new TargetSDK("/Users/vishy/Desktop/CNS-Project-SDK/OneDrive-SDK-java", ".java");
        // sdk2.Analyse();
        // sdk2.GetResult();
        // sdk2.Output();

        // TargetSDK sdk3 = new TargetSDK("/Users/vishy/Desktop/CNS-Project-SDK/dropbox-sdk-go-unofficial", ".go");
        // sdk3.Analyse();
        // sdk3.GetResult();
        // sdk3.Output();



    }
}
