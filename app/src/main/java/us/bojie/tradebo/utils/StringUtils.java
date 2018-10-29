package us.bojie.tradebo.utils;

public class StringUtils {

    public static String getInstrumentIdFromUrl(String InstrumentUrl) {
        String first = InstrumentUrl.replaceFirst("https://api.robinhood.com/instruments/", "");
        return first.replace("/", "");
    }
}
