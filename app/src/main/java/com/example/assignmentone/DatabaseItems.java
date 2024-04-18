package com.example.assignmentone;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseItems {
    private static List<Subject> subjects = new ArrayList<Subject>();

    public DatabaseItems() {
        if (subjects.isEmpty()) {
            subjects.add(new Subject("Arabic", 11, new Duty("test", new Date())));
            subjects.add(new Subject("English", 11));
            subjects.add(new Subject("Mathematics", 11));
            subjects.add(new Subject("Physics", 11));
            subjects.add(new Subject("Chemistry", 11));
        }
    }
    public static List<Subject> search(String subjectName) {
        List<Subject> result = new ArrayList<>();
        for (Subject m : subjects) {
            if (m.getName().equals(subjectName)) {
                result.add(m);
            }
        }
        return result;
    }
    public static String[] getSubjects() {
        //assume we are reading data from database
        String[] subjects = {"Arabic", "English", "Mathematics", "Physics", "Chemistry"};
        return subjects;
    }
}