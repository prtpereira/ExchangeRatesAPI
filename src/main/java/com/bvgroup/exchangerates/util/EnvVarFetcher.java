package com.bvgroup.exchangerates.util;

public class EnvVarFetcher {

    public static String getEnvStrElseDefault(String variable, String defaultVar) {
        String envVar = getEnvStr(variable);

        if (envVar == null) return defaultVar;
        else return envVar;
    }

    public static String getEnvStr(String variable) {
        return System.getenv(variable);
    }

    public static Integer getEnvIntElseDefault(String variable, Integer defaultVar) {
        String envVar = getEnvStr(variable);

        if (envVar == null) return defaultVar;
        else return Integer.parseInt(envVar);
    }

    public static Integer getEnvInt(String variable) {
        return Integer.parseInt(getEnvStr(variable));
    }
}
