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
