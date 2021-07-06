package ucf.assignments;

import java.util.ArrayList;

public class DataOps {
    private static DataOps instance = null;
    private String currentPath = null;
    private ArrayList<ToDoList> toDoLists = null;

    private DataOps() {
        // Initialize needed variables
        toDoLists = new ArrayList<>();
    }

    public static DataOps getInstance(){
        // Normal singleton setup
        if (instance == null){
            instance = new DataOps();
        }
        return instance;
    }

    public ArrayList<ToDoList> readDataFile(String path){
        // if path == currentPath then return the current array list
        // otherwise use Gson to read a ToDoList array into the toDoLists ArrayList
        // set currentPath to path
        // I'm using a singleton to modify the data between saves instead of constantly saving/loading
        // by doing this i can conveniently skip reading from disk a lot when updating list views
        return toDoLists;
    }

    public void writeDataToFile(String path){
        // Take the toDoLists array list which can be modified by the UI between file saves and write it to a JSON
        // file using Gson
    }
}
