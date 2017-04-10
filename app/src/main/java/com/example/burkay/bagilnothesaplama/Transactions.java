package com.example.burkay.bagilnothesaplama;

public class Transactions {
    private static ClassLevel [] levels = {ClassLevel.level8, ClassLevel.level7, ClassLevel.level6,
            ClassLevel.level5, ClassLevel.level4, ClassLevel.level3, ClassLevel.level2, ClassLevel.level1};

    public static float T_Note(int midterm_, int final_, float Hsb_average, float Hsb_standart) {
        return ((((float) (midterm_ + final_) / 2) - Hsb_average) / Hsb_standart) * 10 + 50;
    }

    private static int InverseConversion(int midterm, float Hsb_average, float Hsb_standart, float T_note){
        return (int) (-midterm + ( 2 * Hsb_average ) - ( 10 * Hsb_standart ) + ( (T_note * Hsb_standart) / 5 ));
    }

    public static ClassLevel ClassLev(float Hsb_average) {

        double[] rep = { 80.0, 70.0, 62.5, 57.5, 52.5, 47.5, 42.5};
        for(int count = 0; count < rep.length; count++)
            if(Hsb_average >= rep[count])
                return levels[count];
        return levels[levels.length-1];
    }

    private static LetterNote Absolute_assessment(float T_note) {

        int [] rep = {90, 80, 75, 70, 60, 50, 40, 30};
        for(int count = 0; count < rep.length; count++){
            if(T_note >= rep[count])
                return Rift(count);
        }
        return LetterNote.FF;
    }

    public static LetterNote LetterNot_30B(ClassLevel level, float T_note, float average_Note) {

        if(level == ClassLevel.level8){
            return Absolute_assessment(average_Note);
        }
        else {
            int[] rep = {59, 61, 63, 65, 67, 69, 71};
            for (int count = 0; count < rep.length; count++) {
                if (levels[count + 1] == level)
                    return FoundLetter(rep[count],T_note);
            }
            return LetterNote.FF;
        }
    }

    private static LetterNote FoundLetter(int headCount, float T_note) {
        int cycle = 0;
        for (int count = headCount; count > 0; count -= 5) {
            if ((int)T_note >= count)
                return Rift(cycle);
            cycle++;
        }
        return LetterNote.FF;
    }

    private static LetterNote Rift(int cycle) {
        switch (cycle) {
            case 0:
                return LetterNote.AA;
            case 1:
                return LetterNote.BA;
            case 2:
                return LetterNote.BB;
            case 3:
                return LetterNote.CB;
            case 4:
                return LetterNote.CC;
            case 5:
                return LetterNote.DC;
            case 6:
                return LetterNote.DD;
            case 7:
                return LetterNote.FD;
            default:
                return LetterNote.FF;
        }
    }

    public static int [] Recommendations(ClassLevel level, int midterm, float Hsb_average, float Hsb_standart){

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

    private static int [] Re_funct(int headCount,int midterm, float Hsb_average, float Hsb_standart){
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