package ucf.assignments;

import java.util.ArrayList;

public class ToDoList {
    String title;
    ArrayList<ListItem> items;

    public ToDoList(String title, ArrayList<ListItem> items) {
        this.title = title;
        this.items = items;
    }

    public ToDoList(){
        this.title = "";
        this.items = new ArrayList<>();
    }

    public void addItem(ListItem item){
        // Add item to array list
        items.add(item);
    }
}
