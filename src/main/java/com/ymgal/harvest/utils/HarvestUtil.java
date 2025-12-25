package com.ymgal.harvest.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HarvestUtil {

    public static LocalDate validDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateStr, formatter); // 解析成功，格式正确
        } catch (DateTimeException e) {
            return null; // 解析失败，格式错误
        } catch (Exception e) {
            return null;
        }
    }
}
