package com.etoak.book.commons;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Liu on 2020/1/13.
 */
public class ETDateUtils {
    public static Timestamp string2Timestamp(String value) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(value);
        return  new Timestamp(d.getTime());
    }
}
