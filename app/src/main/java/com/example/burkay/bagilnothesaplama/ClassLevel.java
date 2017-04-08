package com.example.burkay.bagilnothesaplama;


public enum ClassLevel {

    level8("Ustun Basari"),
    level7("Mukemmel"),
    level6("Cok iyi"),
    level5("Iyi"),
    level4("Ortalama ustu"),
    level3("Orta"),
    level2("Zayif"),
    level1("Kotu");

    private String description;

    ClassLevel(String description){
        this.description = description;
    }
    public String Description(){
        return this.description;
    }
}
