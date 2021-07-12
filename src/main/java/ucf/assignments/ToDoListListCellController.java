package ucf.assignments;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.IOException;

public class ToDoListListCellController {
    @FXML
    private TextArea description;
    @FXML
    private Label date;
    @FXML
    private GridPane gridPane;

    public ToDoListListCellController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/to_do_list_item.fxml"));
//        loader.setController(this);
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
