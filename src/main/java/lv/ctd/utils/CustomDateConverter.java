package lv.ctd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public  class CustomDateConverter {

    private static final String PATTERN = "dd-MM-yyyy";
    private static SimpleDateFormat format = new SimpleDateFormat(PATTERN);


    public static String getDateInString(Date date) {
        return format.format(date);
    }
}
