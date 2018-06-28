package com.company;

import java.util.ArrayList;

public class Questiontypes {
    Conversion convert=new Conversion();
    void assignmentQuestions(Merchant merchant,Romannumerals rm)
    {
        String[] key = merchant.line.trim().split("\\s+");
        merchant.constants.put(key[0], key[2]);
    }
    void creditsQuestion(Merchant merchant,Romannumerals rm)
    {

        String formatted = merchant.line.replaceAll("(is\\s+)|([c|C]redits\\s*)","").trim();
        String[] keys = formatted.split("\\s");
        String toBeComputed = keys[keys.length-2];
        float value = Float.parseFloat(keys[keys.length-1]);

        rm.currentRomanNumber="";

        for(int i=0;i<keys.length-2;i++)
        {
            rm.currentRomanNumber += merchant.constants.get(keys[i]);
        }
        int romanNumber = Integer.parseInt(convert.romanToArabic(rm));
        float credit = (float)(value/romanNumber);


        merchant.computedConstants.put(toBeComputed, credit+"");
    }

    void howMuchQuestion(Merchant merchant,Romannumerals rm)
    {
        String formatted = merchant.line.split("is\\s")[1].trim();
        formatted = formatted.replace("?","").trim();

        String keys[] = formatted.split("\\s");

        rm.currentRomanNumber="";
        String completeResult = null;
        boolean errorOccured = false;

        for(String key : keys)
        {
            String romanValue = merchant.constants.get(key);
            if(romanValue==null)
            {
                completeResult = "No idea what you are talking about";
                errorOccured = true;
                break;
            }
            rm.currentRomanNumber += romanValue;
        }

        if(!errorOccured)
        {
            rm.currentRomanNumber = convert.romanToArabic(rm);
            if(rm.currentRomanNumber=="0"){
                completeResult=rm.currentRomanNumber;
            }
            else {
                completeResult = formatted + " is " + rm.currentRomanNumber;
            }
        }

        merchant.answers.add(completeResult);
    }
    void howManyCreditsQuestion(Merchant merchant,Romannumerals rm) {

        String formatted = merchant.line.split("(\\sis\\s)")[1];

        formatted = formatted.replace("?","").trim();

        String[] keys = formatted.split("\\s");

        boolean found = false;
        rm.currentRomanNumber = "";
        String outputResult = null;
        ArrayList<Float> cvalues = new ArrayList<>();

        for(String key : keys)
        {
            found = false;

            String romanValue = merchant.constants.get(key);
            if(romanValue!=null)
            {
                rm.currentRomanNumber += romanValue;
                found = true;
            }

            String computedValue = merchant.computedConstants.get(key);
            if(!found && computedValue!=null)
            {
                cvalues.add(Float.parseFloat(computedValue));
                found = true;
            }
            if(!found)
            {
                outputResult = "No idea what you are talking about...";
                break;
            }
        }

        if(found)
        {
            float res=1;
            for(int i =0;i<cvalues.size();i++) {
                res *= cvalues.get(i);
            }
            int finalres= (int) res;
            if(rm.currentRomanNumber.length()>0)
                finalres = (int)(Integer.parseInt(convert.romanToArabic(rm))*res);
            if(finalres==0){
                outputResult=finalres+"";
            }
            else {
                outputResult = formatted + " is " + finalres + " Credits";
            }
        }

        merchant.answers.add(outputResult);
    }

}
