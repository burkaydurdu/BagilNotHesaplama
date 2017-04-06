package com.example.burkay.bagilnothesaplama;

public class Transactions {

    public static float T_Note(int midterm_, int final_, float Hsb_average, float Hsb_standart) {
        return ((((midterm_ + final_) / 2) - Hsb_average) / Hsb_standart);
    }

    public static ClassLevel ClassLev(float Hsb_average) {

        if (Hsb_average <= 100 && Hsb_average >= 80)
            return ClassLevel.level8;
        else if (Hsb_average < 80 && Hsb_average >= 70)
            return ClassLevel.level7;
        else if (Hsb_average < 70 && Hsb_average >= 62.5)
            return ClassLevel.level6;
        else if (Hsb_average < 62.5 && Hsb_average >= 57.5)
            return ClassLevel.level5;
        else if (Hsb_average < 57.5 && Hsb_average >= 52.5)
            return ClassLevel.level4;
        else if (Hsb_average < 52.5 && Hsb_average >= 47.5)
            return ClassLevel.level3;
        else if (Hsb_average < 47.5 && Hsb_average >= 42.5)
            return ClassLevel.level2;
        else
            return ClassLevel.level1;
    }
    private static LetterNote Absolute_assessment(float T_note) {

        if (T_note <= 100 && T_note >= 90)
            return LetterNote.AA;
        else if (T_note < 90 && T_note >= 80)
            return LetterNote.BA;
        else if (T_note < 80 && T_note >= 75)
            return LetterNote.BB;
        else if (T_note < 75 && T_note >= 70)
            return LetterNote.CB;
        else if (T_note < 70 && T_note >= 60)
            return LetterNote.CC;
        else if (T_note < 60 && T_note >= 50)
            return LetterNote.DC;
        else if (T_note < 50 && T_note >= 40)
            return LetterNote.DD;
        else if (T_note < 40 && T_note >= 30)
            return LetterNote.FD;
        else
            return LetterNote.FF;
    }
    public static LetterNote LetterNot_30B(ClassLevel level, float T_note) {

        if (level == ClassLevel.level8)
            return Absolute_assessment(T_note);
        else if (level == ClassLevel.level7)
            return ClassLev7(T_note);
        else if (level == ClassLevel.level6)
            return ClassLev6(T_note);
        else if (level == ClassLevel.level5)
            return ClassLev5(T_note);
        else if (level == ClassLevel.level4)
            return ClassLev4(T_note);
        else if (level == ClassLevel.level3)
            return ClassLev3(T_note);
        else if (level == ClassLevel.level2)
            return ClassLev2(T_note);
        else
            return ClassLev1(T_note);
    }
    private static LetterNote ClassLev7(float T_note) {

        if (T_note >= 59)
            return LetterNote.AA;
        else if (T_note < 59 && T_note >= 54)
            return LetterNote.BA;
        else if (T_note < 54 && T_note >= 49)
            return LetterNote.BB;
        else if (T_note < 49 && T_note >= 44)
            return LetterNote.CB;
        else if (T_note < 44 && T_note >= 39)
            return LetterNote.CC;
        else if (T_note < 39 && T_note >= 34)
            return LetterNote.DC;
        else if (T_note < 34 && T_note >= 29)
            return LetterNote.DD;
        else if (T_note < 29 && T_note >= 24)
            return LetterNote.FD;
        else
            return LetterNote.FF;
    }
    private static LetterNote ClassLev6(float T_note) {

        if (T_note >= 61)
            return LetterNote.AA;
        else if (T_note < 61 && T_note >= 56)
            return LetterNote.BA;
        else if (T_note < 56 && T_note >= 51)
            return LetterNote.BB;
        else if (T_note < 51 && T_note >= 46)
            return LetterNote.CB;
        else if (T_note < 46 && T_note >= 41)
            return LetterNote.CC;
        else if (T_note < 41 && T_note >= 36)
            return LetterNote.DC;
        else if (T_note < 36 && T_note >= 31)
            return LetterNote.DD;
        else if (T_note < 31 && T_note >= 26)
            return LetterNote.FD;
        else
            return LetterNote.FF;
    }
    private static LetterNote ClassLev5(float T_note) {

        if (T_note >= 63)
            return LetterNote.AA;
        else if (T_note < 63 && T_note >= 58)
            return LetterNote.BA;
        else if (T_note < 58 && T_note >= 53)
            return LetterNote.BB;
        else if (T_note < 53 && T_note >= 48)
            return LetterNote.CB;
        else if (T_note < 48 && T_note >= 43)
            return LetterNote.CC;
        else if (T_note < 43 && T_note >= 38)
            return LetterNote.DC;
        else if (T_note < 38 && T_note >= 33)
            return LetterNote.DD;
        else if (T_note < 33 && T_note >= 28)
            return LetterNote.FD;
        else
            return LetterNote.FF;
    }
    private static LetterNote ClassLev4(float T_note) {

        if (T_note >= 65)
            return LetterNote.AA;
        else if (T_note < 65 && T_note >= 60)
            return LetterNote.BA;
        else if (T_note < 60 && T_note >= 55)
            return LetterNote.BB;
        else if (T_note < 55 && T_note >= 50)
            return LetterNote.CB;
        else if (T_note < 50 && T_note >= 45)
            return LetterNote.CC;
        else if (T_note < 45 && T_note >= 40)
            return LetterNote.DC;
        else if (T_note < 40 && T_note >= 35)
            return LetterNote.DD;
        else if (T_note < 35 && T_note >= 30)
            return LetterNote.FD;
        else
            return LetterNote.FF;
    }
    private static LetterNote ClassLev3(float T_note) {

        if (T_note >= 67)
            return LetterNote.AA;
        else if (T_note < 67 && T_note >= 62)
            return LetterNote.BA;
        else if (T_note < 62 && T_note >= 57)
            return LetterNote.BB;
        else if (T_note < 57 && T_note >= 52)
            return LetterNote.CB;
        else if (T_note < 52 && T_note >= 47)
            return LetterNote.CC;
        else if (T_note < 47 && T_note >= 42)
            return LetterNote.DC;
        else if (T_note < 42 && T_note >= 37)
            return LetterNote.DD;
        else if (T_note < 37 && T_note >= 32)
            return LetterNote.FD;
        else
            return LetterNote.FF;
    }
    private static LetterNote ClassLev2(float T_note) {

        if (T_note >= 69)
            return LetterNote.AA;
        else if (T_note < 69 && T_note >= 64)
            return LetterNote.BA;
        else if (T_note < 64 && T_note >= 59)
            return LetterNote.BB;
        else if (T_note < 59 && T_note >= 54)
            return LetterNote.CB;
        else if (T_note < 54 && T_note >= 49)
            return LetterNote.CC;
        else if (T_note < 49 && T_note >= 44)
            return LetterNote.DC;
        else if (T_note < 44 && T_note >= 39)
            return LetterNote.DD;
        else if (T_note < 39 && T_note >= 34)
            return LetterNote.FD;
        else
            return LetterNote.FF;
    }
    private static LetterNote ClassLev1(float T_note) {

        if (T_note >= 71)
            return LetterNote.AA;
        else if (T_note < 71 && T_note >= 66)
            return LetterNote.BA;
        else if (T_note < 66 && T_note >= 61)
            return LetterNote.BB;
        else if (T_note < 61 && T_note >= 56)
            return LetterNote.CB;
        else if (T_note < 56 && T_note >= 51)
            return LetterNote.CC;
        else if (T_note < 51 && T_note >= 46)
            return LetterNote.DC;
        else if (T_note < 46 && T_note >= 41)
            return LetterNote.DD;
        else if (T_note < 41 && T_note >= 36)
            return LetterNote.FD;
        else
            return LetterNote.FF;
    }
}
