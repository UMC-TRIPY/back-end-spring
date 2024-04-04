package com.example.tripy.global.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeConverter {

    public static String convertToDisplayTime(LocalDateTime dateTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(dateTime, currentTime);
        long hours = ChronoUnit.HOURS.between(dateTime, currentTime);

        if (minutes < 60) {
            return minutes + "분 전";
        } else if (hours < 24) {
            return hours + "시간 전";
        } else
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    public static String convertDoubleToStringTime(double totalSeconds) {
        int seconds = (int) totalSeconds;
        int hours = (seconds / 3600);
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;

        String hoursString = (hours < 100) ? String.format("%02d", hours) : String.format("%03d", hours);
        String totalTime = String.format("%s:%02d:%02d", hoursString, minutes, remainingSeconds);

        return totalTime;
    }
}