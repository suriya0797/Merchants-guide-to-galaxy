package com.company;

import java.util.HashMap;

public class Romannumerals {

    HashMap<String,Integer> romanNumerals=new HashMap<>();
    String currentRomanNumber;
    public Romannumerals()
    {
        romanNumerals.put("I",1);
        romanNumerals.put("V",5);
        romanNumerals.put("X",10);
        romanNumerals.put("L",50);

        romanNumerals.put("C",100);
        romanNumerals.put("D",500);
        romanNumerals.put("M",1000);
    }



    Boolean validateRomanNumber(String roman)
    {
        String romanNumberValidator = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        Boolean result = false;

        if(roman.matches(romanNumberValidator))
        {
            result = true;
        }
        return result;
    }




}
