package com.adda.picture_sale_backend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static String convertDateFormat(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = inputFormat.parse(inputDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String outputDate = outputFormat.format(date);

            return outputDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String inputDate = "20240202";
        String outputDate = convertDateFormat(inputDate);
        System.out.println(outputDate); // Output: 2024-02-02
    }
}
