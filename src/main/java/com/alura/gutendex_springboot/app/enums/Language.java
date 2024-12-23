package com.alura.gutendex_springboot.app.enums;

public enum Language {
    ENGLISH("en"),
    SPANISH("es"),
    PORTUGUESE("por"),
    FRENCH("fre");

    public final String lang;
    Language(String label){
        this.lang = label;
    }

    public static Language fromString(String lang) {
        for (Language value:Language.values()){
            if(value.lang.equalsIgnoreCase(lang)){
                return value;
            }
        }

        throw new IllegalArgumentException("Genre: " + lang + " not found.");
    }
}
