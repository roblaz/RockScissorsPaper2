package com.example.gamerockscissorspaper;

import android.util.Log;

import java.io.Serializable;

public class User implements Serializable {

    private String name;

    private long score;

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString(){
        return  getName() +" " + getScore();
    }
    public String getUser(){
        StringBuilder builder = new StringBuilder(name);
        return builder.toString();

    }
    public User (String line) throws Exception{
        String[] elems = line.split(":");
        Log.e("String line", line + " length: " + elems.length);
        if (elems.length != 2) throw new Exception("Illegal text for person");
        name = elems[0];
        score = Long.valueOf(elems[1].trim());

    }

    public User(String name, long score) {
        this.name = name;
        this.score = score;
    }

}
