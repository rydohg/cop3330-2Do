package ucf.assignments;

public class ToDoController {
    public void newList(String title){
        // Add to the ArrayList<ToDoList> in the DataOps singleton
    }

    public void loadLists(String path){
        // Load lists from a path into the DataOps singleton using readDataFile(path)
    }

    public void saveLists(String path){
        // Save the current ArrayList<ToDoList> in the DataOps singleton to a file
        // using DataOps.writeToFile(path)
    }

    public void newListOnClick(){
        // Show TextInputDialog to ask for new ToDoList name
        // Call newList(title)
        // Update list selector list view
    }

    public void loadListOnClick(){
        // Show TextInputDialog to ask for list path
        // Call loadLists(path)
        // Update list selector list view
        // Either empty the to do list list view or load the first to do list in the file automatically
    }

    public void saveListsOnClick(){
        // Show TextInputDialog to ask for list path
        // Call saveLists(path)
    }

    public void listSelectorOnClick(){
        // Get the name of the selected list
        // Update the to do list list view to the new list
    }
}
