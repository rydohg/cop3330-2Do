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
}
