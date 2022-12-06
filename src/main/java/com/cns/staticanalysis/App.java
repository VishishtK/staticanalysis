package com.cns.staticanalysis;

public class App {
    public static void main(String[] args) {

        if (args.length != 2 || args[0].equals("") || args[1].equals("")) {
            System.out
                    .println("Usage: java -jar target/staticanalyser-1.0-SNAPSHOT.jar <Path To SDK> <Language of SDK>");
            return;
        }

        TargetSDK sdk = new TargetSDK(args[0], "." + args[1]);
        sdk.Analyse();
        sdk.GetResult();
        sdk.Output();

        // TargetSDK sdk2 = new
        // TargetSDK("/Users/vishy/Desktop/CNS-Project-SDK/OneDrive-SDK-java", ".java");
        // sdk2.Analyse();
        // sdk2.GetResult();
        // sdk2.Output();

        // TargetSDK sdk3 = new
        // TargetSDK("/Users/vishy/Desktop/CNS-Project-SDK/dropbox-sdk-go-unofficial",
        // ".go");
        // sdk3.Analyse();
        // sdk3.GetResult();
        // sdk3.Output();

    }
}
