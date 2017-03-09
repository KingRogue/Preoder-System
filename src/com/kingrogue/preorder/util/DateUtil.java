package com.kingrogue.preorder.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);


    public static String format(LocalDate date){
        if(date==null){
            return null;
        }
        return DATE_FORMATTER.format(date);
    }



}
