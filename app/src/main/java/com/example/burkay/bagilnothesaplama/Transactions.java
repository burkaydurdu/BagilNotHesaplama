package com.example.burkay.bagilnothesaplama;

public class Transactions {
    private static ClassLevel [] levels = {ClassLevel.level8, ClassLevel.level7, ClassLevel.level6,
            ClassLevel.level5, ClassLevel.level4, ClassLevel.level3, ClassLevel.level2, ClassLevel.level1};
    private static LetterNote[] letters = {LetterNote.AA,LetterNote.BA,LetterNote.BB,LetterNote.CB,LetterNote.CC,
            LetterNote.DC,LetterNote.DD,LetterNote.FD,LetterNote.FF};
    public static double T_Note(int midterm_, int final_, double Hsb_average, double Hsb_standart) {
        return ((((double) (midterm_ + final_) / 2) - Hsb_average) / Hsb_standart) * 10 + 50;
    }

    private static int InverseConversion(int midterm, double Hsb_average, double Hsb_standart, double T_note){
        return (int) Math.ceil(-midterm + ( 2 * Hsb_average ) - ( 10 * Hsb_standart ) + ( (T_note * Hsb_standart) / 5 ));
    }

    public static ClassLevel ClassLev(double Hsb_Average) {

        double[] rep = { 80.0, 70.0, 62.5, 57.5, 52.5, 47.5, 42.5}  ;
        for(int count = 0; count < rep.length; count++)
            if(Hsb_Average >= rep[count])
                return levels[count];
        return levels[levels.length-1];
    }

    public static LetterNote Absolute_Assessment(double average_note) {

        int [] rep = {90, 80, 75, 70, 60, 50, 40, 30};
        for(int count = 0; count < rep.length; count++){
            if(average_note >= rep[count])
                return letters[count];
        }
        return LetterNote.FF;
    }

    public static LetterNote LetterNote(ClassLevel level, double T_note) {

            double[] rep = {58.99, 60.99, 62.99, 64.99, 66.99, 68.99, 70.99};
            for (int count = 0; count < rep.length; count++) {
                if (levels[count + 1] == level)
                    return FoundLetter(rep[count], T_note);
            }
            return LetterNote.FF;
    }

    private static LetterNote FoundLetter(double headCount, double T_note) {
        int cycle = 0;
        for (double count = headCount; count > 0.00; count -= 5.00) {
            if (T_note > count)
                return letters[cycle];
            cycle++;
        }
        return LetterNote.FF;
    }

    public static int [] Recommendations(ClassLevel level, int midterm, double Hsb_average, double Hsb_standart){

        switch (level) {
            case level8:
                return Re_funct_Abso(midterm);
            case level7:
                return Re_funct(59, midterm, Hsb_average, Hsb_standart);
            case level6:
                return Re_funct(61, midterm, Hsb_average, Hsb_standart);
            case level5:
                return Re_funct(63, midterm, Hsb_average, Hsb_standart);
            case level4:
                return Re_funct(65, midterm, Hsb_average, Hsb_standart);
            case level3:
                return Re_funct(67, midterm, Hsb_average, Hsb_standart);
            case level2:
                return Re_funct(69, midterm, Hsb_average, Hsb_standart);
            default:
                return Re_funct(71, midterm, Hsb_average, Hsb_standart);
        }
    }

    private static int [] Re_funct(int headCount,int midterm, double Hsb_average, double Hsb_standart){
        int [] arrayObj = new  int[6];
        int cycle = 0;
        for (int count = headCount; count > (headCount-30); count -= 5) {
            arrayObj[cycle] = InverseConversion(midterm, Hsb_average, Hsb_standart, count);
            cycle++;
        }
        return arrayObj;
    }

    private static int [] Re_funct_Abso(int midterm){

        int [] rep = {90, 80, 75, 70, 60, 50};
        for(int count = 0; count < rep.length; count++){
            rep[count] = 2 * rep[count] - midterm;
        }
        return rep;

    }
}