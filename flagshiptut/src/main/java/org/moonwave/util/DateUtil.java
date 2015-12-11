package org.moonwave.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static java.sql.Timestamp getSqlTimestamp() {
        return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public static java.sql.Timestamp dateToSqlTimestamp(java.util.Date date) {
        return new java.sql.Timestamp(date.getTime());
    }

    public static java.util.Date sqlTimestampToDate(java.sql.Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());
        return date;
    }

}
