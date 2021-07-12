/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Doherty
 */
package ucf.assignments;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static ucf.assignments.ToDoController.simpleDialog;
import static ucf.assignments.ToDoController.verifyDate;


public class ToDoListListCell extends ListCell<ListItem> {
    private FXMLLoader mLoader;
    private ListView<ListItem> toDoList;
    private ListItem listItem;
    @FXML
    private TextArea description;
    @FXML
    private Label date;
    @FXML
    private CheckBox complete;
    @FXML
    private GridPane gridPane;

    public ToDoListListCell(ListView<ListItem> toDoList){
        super();
        // Take in the list view to refresh it on delete
        this.toDoList = toDoList;
    }

    @Override
    protected void updateItem(ListItem item, boolean empty) {
        // This method is called everytime the list updates for each item
        super.updateItem(item, empty);
        listItem = item;
        // Make empty items empty
        if (empty || item == null){
            setText(null);
            setGraphic(null);
        } else {
            if (mLoader == null){
                // Load list item fxml file
                mLoader = new FXMLLoader(getClass().getResource("/to_do_list_item.fxml"));
                // set controller to ToDoListListCellController
                mLoader.setController(this);

                try {
                    mLoader.load();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            // set text and date text fields to appropriate values
            description.setText(item.description);
            date.setText(item.date);
            // Check checkbox if needed
            if (item.complete && !complete.isSelected()){
                complete.fire();
            }
            if (!item.complete && complete.isSelected()){
                complete.fire();
            }
            // Put everything we just done on the screen
            setText(null);
            setGraphic(gridPane);
        }
    }

    public void markComplete(){
        listItem.setComplete(!listItem.complete);
    }

    private void deleteItem() {
        // Delete item from data singleton
        DataOps data = DataOps.getInstance();
        data.getItems().remove(listItem);
    }

    public void editDetails(String description, String date){
        // Edit this items details
        listItem.setDescription(description);
        listItem.setDate(date);
    }

    public void deleteItemOnClick(){
        // Delete data from the data singleton
        deleteItem();
        // Remove it from the list view by refreshing it with the latest data
        DataOps data = DataOps.getInstance();
        toDoList.getItems().clear();
        for (ListItem i : data.getItems()) {
            toDoList.getItems().add(i);
        }
    }

    public void editDetailsOnClick(){
        String descriptionString = simpleDialog("Edit Details", "Please enter the description", listItem.description);
        String dateString = "";
        boolean invalidDate = true;
        // Show dialog to ask for new date and keep asking until we get a valid date
        while (invalidDate){
            dateString = simpleDialog("Edit Details", "Please enter the date in the form YYYY-MM-DD", listItem.date);
            if (verifyDate(dateString)){
                invalidDate = false;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Date");
                alert.setContentText("Please enter a valid date in YYYY-MM-DD format");
                alert.showAndWait();
            }
        }
        // Edit data's version of the item
        editDetails(descriptionString, dateString);
        // Edit the view's version of the item
        description.setText(descriptionString);
        date.setText(dateString);
    }
}
