package com.cns.staticanalysis;

public abstract class AnalyseFile {

    long totalCriticalVars = 0;
    long totalCriticalFields = 0;
    long totalCriticalMethods = 0;

    long totalSafeCriticalVars = 0;
    long totalSafeCriticalFields = 0;
    long totalSafeCriticalMethods = 0;

    public abstract void Analyse();
}
