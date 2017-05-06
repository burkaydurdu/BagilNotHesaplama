package com.example.burkay.bagilnothesaplama;


public enum LetterNote {
    AA("aa"),
    BA("ba"),
    BB("bb"),
    CB("cb"),
    CC("cc"),
    DC("DC"),
    DD("DD"),
    FD("FD"),
    FF("ff");
    private String description;

    LetterNote(String description){
        this.description = description;
    }
    public String Description(){
        return this.description;
    }
}
