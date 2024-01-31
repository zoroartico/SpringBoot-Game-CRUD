package com.example.springbootproject1.util;

//class created to streamline data validation throughout application
//returns false if argument does not pass basic validation checks for its type
public class Validate {

    public boolean Object(Object object) {
        return object != null;
    }
    public boolean String(String string) {
        return string != null && !string.trim().isEmpty();
    }

    //checks if child of Number is null
    public boolean Number(Number n){
        return n != null;
    }

    //uses generics to validate multiple Number data types without method repetition, eg: int, double, short etc
    public <T extends Number & Comparable<T>> boolean Range(T value, T min, T max){
        if (value == null)
            return false;
        return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
    }
    public <T extends Number & Comparable<T>> boolean Min(T value, T min){
        if (value == null)
            return false;
        return value.compareTo(min) >= 0;
    }
    public <T extends Number & Comparable<T>> boolean Max(T value, T max){
        if (value == null)
            return false;
        return value.compareTo(max) <= 0;
    }
}