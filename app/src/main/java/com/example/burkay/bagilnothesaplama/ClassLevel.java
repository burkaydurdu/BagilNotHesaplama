package com.example.burkay.bagilnothesaplama;

public enum ClassLevel {

    level8(R.string.success),
    level7(R.string.perfect),
    level6(R.string.verygood),
    level5(R.string.good),
    level4(R.string.aboveaverage),
    level3(R.string.intermediate),
    level2(R.string.low),
    level1(R.string.bad);

    private int description;
    ClassLevel(int description){
        this.description = description;
    }
    public int Description(){
        return this.description;
    }


}
