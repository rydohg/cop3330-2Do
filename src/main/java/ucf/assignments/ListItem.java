/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Doherty
 */
package ucf.assignments;

public class ListItem {
    String description;
    String date;

    boolean complete;

    public ListItem(String description, String date) {
        this.description = description;
        this.date = date;
        this.complete = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setComplete(boolean isComplete){ complete = isComplete; }
}
