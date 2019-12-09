package com.abbaqus.vivekathreya_reddit.utils;

public class MathUtils {

    public static String formatNumbers(Integer number){
        if (Math.abs(number / 1000000) > 1) {
            return (number / 1000000.0f) + "m";

        } else if (Math.abs(number / 1000) > 1) {
           return (number / 1000.0f) + "k";

        } else {
            return number.toString();

        }
    }

    public static String elapsedTimeInHrs(Integer timeInUtc){
        long elapsedTime = ((System.currentTimeMillis()/1000) - timeInUtc)/3600;

        return elapsedTime + "h";
//        (System.currentTimeMillis() - (d.getData().getCreatedUtc() * 1000))/3600000
    }
}
