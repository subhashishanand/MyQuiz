package com.zersy.myapplication;

public class note {
    private String title;
    private String KEY;

    public note(){
        //empty constructor.
    }

    public note(String title, String KEY){
        this.title = title;
        this.KEY = KEY;
    }

    public String getTitle() {
        return title;
    }

    public String getKEY() {
        return KEY;
    }
}