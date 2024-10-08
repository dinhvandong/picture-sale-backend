package com.adda.picture_sale_backend.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static String formatDate(Long dateLong) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(String.valueOf(dateLong));
            return outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String convertLongToYYYYMM(long dateValue) {
        LocalDate date = LocalDate.parse(String.valueOf(dateValue), DateTimeFormatter.BASIC_ISO_DATE);
        return date.format(DateTimeFormatter.ofPattern("yyyyMM"));
    }
    public static List<String> getDatesBetween(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        List<String> dates = new ArrayList<>();

        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

        while (!startLocalDate.isAfter(endLocalDate)) {
            dates.add(startLocalDate.format(formatter));
            startLocalDate = startLocalDate.plusDays(1);
        }

        return dates;
    }

    public static long getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = currentDate.format(formatter);
        return Long.parseLong(formattedDate);
    }
}
