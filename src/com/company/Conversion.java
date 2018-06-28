package com.company;

public class Conversion {

    String romanToArabic(Romannumerals roman)
    {
        int decimal = 0;
        int lastNumber = 0;
        if(roman.validateRomanNumber(roman.currentRomanNumber))
        {
            for(int i=roman.currentRomanNumber.length()-1;i>=0;i--)
            {
                char ch = roman.currentRomanNumber.charAt(i);
                decimal = calculateRoman(roman.romanNumerals.get(String.valueOf(ch)), lastNumber, decimal);
                lastNumber = roman.romanNumerals.get(String.valueOf(ch));
            }
        }
        if(!roman.validateRomanNumber(roman.currentRomanNumber)) {
            decimal=0;
        }
        return decimal+"";
    }


    int calculateRoman(int TotalDecimal, int LastRomanLetter, int LastDecimal){

        if (LastRomanLetter > TotalDecimal) {
            return LastDecimal - TotalDecimal;
        }
        else {
            return LastDecimal + TotalDecimal;
        }
    }
}