package by.zhigarev.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String format(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

}
