package com.adda.picture_sale_backend.utils;

import java.util.Random;

public class Ultis {

    public static String generateRandomCode(int length) {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);  // Generates a random digit between 0 and 9
            codeBuilder.append(digit);
        }

        return codeBuilder.toString();
    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder stringbuilder = new StringBuilder();

        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(letters.length());
            char randomChar = letters.charAt(randomIndex);
            stringbuilder.append(randomChar);
        }

        return stringbuilder.toString();
    }
}
