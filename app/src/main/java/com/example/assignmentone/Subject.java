package com.example.assignmentone;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public class Subject {
    String name;
    Integer level;
    ArrayList<Duty> duties = new ArrayList<>();

    public Subject() {}
    public Subject(String name, Integer level, Duty ... duties) {
        this.name = name;
        this.level = level;
        for (Duty duty : duties) {
            this.duties.add(duty);
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name;}
    public Integer getLevel() {return level;}
    public void setLevel(Integer level) {this.level = level;}
    public ArrayList<Duty> getDuties() {return duties;}

    @NonNull
    @Override
    public String toString() {
        return "Subject{" + "name='" + name + '\'' + ", level=" + level + '}';
    }
}