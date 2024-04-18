package com.example.assignmentone;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Duty {
    private String title;
    private Date due;

    public Duty(String title, Date due) {
        this.title = title;
        this.due = due;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getDue() {
        return due;
    }
    public void setDue(Date due) {
        this.due = due;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault());
        String formattedDueDate = (due != null) ? dateFormat.format(due) : "No due date";
        return " " + title + "\n Due: " + formattedDueDate;
    }
}