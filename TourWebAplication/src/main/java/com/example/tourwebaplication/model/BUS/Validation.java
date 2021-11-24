package com.example.tourwebaplication.model.BUS;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {
    public static void notNullOrEmpty(StringBuilder message, String... strings) {
        String title = "";
        String text = "";
        for(int i = 0; i < strings.length; i++){
            if(i % 2 == 0){
                title = strings[i];
            }else {
                text = strings[i];
                if(text == null || text.equals("")){
                    message.append(title + ": " + "không được trống\n");
                }
            }
        }
    }

    public static void positiveNumbers(StringBuilder message, String... strings){
        String title = "";
        String text = "";
        for(int i = 0; i < strings.length; i++){
            if(i % 2 == 0){
                title = strings[i];
            }else {
                text = strings[i];
                if(!text.matches("^[0-9]+$")){
                    message.append(title + ": " + "phải là số nguyên\n");
                }
            }
        }
    }

    public static boolean isDate(StringBuilder message, String... strings) {
        String title = "";
        String text = "";
        boolean isDate = true;
        for(int i = 0; i < strings.length; i++){
            if(i % 2 == 0){
                title = strings[i];
            }else {
                text = strings[i];
                if(text == null || text.equals("")){
                    message.append(title + ": " + "không được trống\n");
                    isDate = false;
                    continue;
                }
                try {
                    new SimpleDateFormat("yyyy-MM-dd").parse(text);
                } catch (ParseException e) {
                    e.printStackTrace();
                    message.append(title + ": " + "không phải định dạng ngày tháng\n");
                    isDate = false;
                }
            }
        }
        return isDate;
    }

    public static void afterOrEquals(StringBuilder message, String title1, String date1, String title2, String date2) {
        int result = date1.compareTo(date2);
        if(result < 0){
            message.append(title1 + " phải sau hoặc bằng " + title2 + "\n");
        }
    }

    public static void beforeOrEquals(StringBuilder message, String title1, String date1, String title2, String date2) {
        int result = date1.compareTo(date2);
        if(result > 0){
            message.append(title1 + " phải trước hoặc bằng " + title2 + "\n");
        }
    }
}
