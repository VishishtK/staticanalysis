package com.cns.staticanalysis;

public abstract class AnalyseFile {

    long totalCriticalVars;
    long totalCriticalFields;
    long totalCriticalMethods;

    long totalSafeCriticalVars;
    long totalSafeCriticalFields;
    long totalSafeCriticalMethods;

    public abstract void Analyse();
}
