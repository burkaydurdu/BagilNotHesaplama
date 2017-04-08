package com.example.burkay.bagilnothesaplama;


public enum LetterNote {
    AA("AA"),
    BA("BA"),
    BB("BB"),
    CB("CB"),
    CC("CC"),
    DC("DC"),
    DD("DD"),
    FD("FD"),
    FF("FF");
    private String description;

    LetterNote(String description){
        this.description = description;
    }
    public String Description(){
        return this.description;
    }
}
