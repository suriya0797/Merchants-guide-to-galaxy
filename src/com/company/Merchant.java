package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Merchant {
    ArrayList<String> pattern=new ArrayList<String >();


    int type;
    HashMap<String, String> constants=new HashMap<>();
    HashMap<String, String> computedConstants=new HashMap<>();
    String line;
    ArrayList answers=new ArrayList();
    Romannumerals rm=new Romannumerals();

    Scanner scanner=new Scanner(System.in);

    Merchant(){
        pattern.add("[a-zA-Z]+ is [I|V|X|L|C|D|M]$");
        pattern.add("^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$");
        pattern.add("^how much is (([A-Za-z\\s])+)\\?$");
        pattern.add("^how many [c|C]redits is (([A-Za-z\\s])+)\\?$");
    }

    
    public  static void main(String[] args){
        Merchant merchant =new Merchant();
        merchant.inputQuestions();
    }


    void inputQuestions(){

        Merchant merchant=new Merchant();
        System.out.println("Enter the questions line by line and for result press enter after a empty line:");
        while((merchant.line=scanner.nextLine()).length()>0) {
            merchant.validate(merchant);
        }
        answerDisplay(merchant);
    }


    void findType(Merchant input){
        input.line = input.line.trim();

        boolean matched = false;
        input.type=-1;
        for(int i =0;i<4 && !matched ;i++)
        {
            if( input.line.matches((String) pattern.get(i)) )
            {
                matched = true;
                input.type=i;
            }
        }
    }


    void validate(Merchant merchant)
    {
        findType(merchant);
        Questiontypes qt=new Questiontypes();

        switch(merchant.type)
        {
            case 0 :
                qt.assignmentQuestions(merchant,rm);
                break;

            case 1 :
                qt.creditsQuestion(merchant,rm);
                break;

            case 2 :
                qt.howMuchQuestion(merchant,rm);
                break;

            case 3 :
                qt.howManyCreditsQuestion(merchant,rm);
                break;

            default :
                answers.add("No idea what are you talking about");
                break;
        }
    }


    void answerDisplay(Merchant questions){
        System.out.println("Answers:\n");
        for(int i=0;i<questions.answers.size();i++)
        {
            System.out.println(questions.answers.get(i));
        }
    }
}
