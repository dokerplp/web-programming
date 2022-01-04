package com.example.wildflyee.util;


public class Converter {
    public static Float StringToFloat(String num) {
        try {
            return Float.parseFloat(num);
        } catch (Exception e) {
            return null;
        }
    }
}
