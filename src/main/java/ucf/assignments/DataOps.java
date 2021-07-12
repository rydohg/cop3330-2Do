package ucf.assignments;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class DataOps {
    private static DataOps instance = null;
    private ToDoList toDoList = null;

    private DataOps() {
        // Initialize needed variables
        toDoList = new ToDoList();
/*        toDoList.addItem(new ListItem("idk", "2021-07-09"));
        toDoList.addItem(new ListItem("idek", "2021-07-09"));
        toDoList.addItem(new ListItem("123", "2021-07-09"));*/
    }

    public static DataOps getInstance(){
        // Normal singleton setup
        if (instance == null){
            instance = new DataOps();
        }
        return instance;
    }

    public void readDataFile(File file){
        if (file != null && file.exists()){
            try {
                Gson gson = new Gson();
                toDoList = gson.fromJson(new FileReader(file), ToDoList.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeDataToFile(File file){
        // Take the toDoLists array list which can be modified by the UI between file saves and write it to a JSON
        // file using Gson
        Gson gson = new Gson();
        String json = gson.toJson(toDoList);
        if (file != null){
            FileWriter writer;
            try {
                file.createNewFile();
                writer = new FileWriter(file);
                writer.write(json);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addItem(ListItem item) {
        toDoList.addItem(item);
    }

    public void nameToDoList(String name){
        toDoList.title = name;
    }

    public ArrayList<ListItem> getItems() {
        return toDoList.items;
    }
}
