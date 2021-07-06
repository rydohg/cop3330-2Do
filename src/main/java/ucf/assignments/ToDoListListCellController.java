package ucf.assignments;

public class ToDoListListCellController {
    public void markComplete(){
        // Set boolean to true in this ListItem
    }
    public void editDetails(String date, String description){
        // Set this ListItem's details accordingly in the singleton
    }
    public void deleteItem(){
        // Delete this list item in the singleton
    }
    public void markCompleteOnClick(){
        // call markComplete
        // Update complete button's text to completed
    }
    public void editDetailsOnClick(){
        // Display TextInputDialog to ask for new details
        // call editDetails
        // Update item in list view
    }
    public void deleteItemOnClick(){
        // call deleteItem
        // Remove item from list view
    }
}
