package com.ymgal.helper;

import java.time.LocalDate;

public class DateTimeHelper {

    public static LocalDate DateFormatConvert(String dateString) {
        if (dateString.matches("\\d{1,4}-\\d{1,2}-\\d{1,2}")) {
            return LocalDate.parse(dateString);
        } else if (dateString.matches("\\d{1,4}-\\d{1,2}")) {
            return LocalDate.parse(dateString + "-01");
        }
        return null;

    }
}
