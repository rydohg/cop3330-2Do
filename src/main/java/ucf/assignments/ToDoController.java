/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Doherty
 */
package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class ToDoController {
    @FXML
    private ListView<ListItem> toDoListView;
    private boolean completeFilter = false;
    private boolean incompleteFilter = false;

    public void initialize(){
        // Set the list view to use our custom list cells
        toDoListView.setCellFactory(lv -> new ToDoListListCell(toDoListView));
    }

    public void refreshListView(){
        // Overloaded refreshListView for easier code
        refreshListView(false, false);
    }

    public void refreshListView(boolean onlyComplete, boolean onlyIncomplete){
        // Refreshes view to match the data singleton and can filter by complete or not
        DataOps data = DataOps.getInstance();
        toDoListView.getItems().clear();
        for (ListItem item : data.getItems()) {
            if ((!onlyComplete || item.complete) && (!onlyIncomplete || !item.complete)) {
                toDoListView.getItems().add(item);
            }
        }
    }

    public void newList(){
        DataOps ops = DataOps.getInstance();
        // Remove all items from singleton
        ops.getItems().clear();
    }

    public void loadLists(File file){
        // Load lists from a path into the DataOps singleton using readDataFile(path)
        DataOps data = DataOps.getInstance();
        data.readDataFile(file);
    }

    public void saveList(File file){
        // Save the current ArrayList<ToDoList> in the DataOps singleton to a file
        // using DataOps.writeToFile(path)
        DataOps data = DataOps.getInstance();
        data.writeDataToFile(file);
    }

    private void newItem(String description, String date) {
        // Add new item to data singleton
        DataOps data = DataOps.getInstance();
        data.addItem(new ListItem(description, date));
    }

    public void newListOnClick(){
        // Call newList()
        newList();
        // Update list selector list view
        refreshListView();
    }

    public void newItemOnClick(){
        String description = simpleDialog("New Item", "Please enter the description", "");
        String date = "";
        // Ask for details for a new item then verify the date is valid and keep asking until we get one
        boolean invalidDate = true;
        while (invalidDate){
            date = simpleDialog("New Item", "Please enter the date in the form YYYY-MM-DD", "2021-07-12");
            if(verifyDate(date)){
                invalidDate = false;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Date");
                alert.setContentText("Please enter a valid date in YYYY-MM-DD format");
                alert.showAndWait();
            }
        }
        newItem(description, date);
        refreshListView();
    }

    public void loadListOnClick(){
        // Get file from FileChooser
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Load Location");
        File file = chooser.showOpenDialog(null);
        // Call loadLists(file)
        loadLists(file);
        // Update list view
        refreshListView();
    }

    public void saveListOnClick(){
        // Get file from FileChooser
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose Save Location");
        File file = chooser.showSaveDialog(null);
        // Call saveLists(file)
        saveList(file);
    }

    public static boolean verifyDate(String date){
        // Verify that the date is valid and matches our format
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            if (date.length() != 10){ return false; }
            format.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void onlyCompleteOnClick(){
        // Refresh with filter if checked and without if not
        if (!completeFilter) {
            refreshListView(true, false);
        } else {
            refreshListView();
        }
        completeFilter = !completeFilter;
    }

    public void onlyIncompleteOnClick(){
        // Refresh with filter if checked and without if not
        if (!incompleteFilter) {
            refreshListView(false, true);
        } else {
            refreshListView();
        }
        incompleteFilter = !incompleteFilter;
    }

    public void helpDialog(){
        // Display help dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Dedicated to \"Rey\"");
        alert.setContentText("New List makes a new list (throws unsaved stuff away) and also acts as Clear List.\nLoad List uh loads a list.\n" +
                "Guess what Save List does.\n" +
                "You can edit an item by hitting its ... wait for it ... Edit Details\n" +
                "Delete Item deletes it. The complete checkbox marks an item as complete or incomplete.\n" +
                "The Complete/Incomplete checkboxes on the left filter by complete or incomplete.\n" +
                "Lastly, the help button but it looks like you already found the help button :)");
        alert.show();
    }

    public static String simpleDialog(String title, String prompt, String defaultString){
        // Display a simple text dialog and give the value input into it
        TextInputDialog dialog = new TextInputDialog(defaultString);
        dialog.setTitle(title);
        dialog.setHeaderText(prompt);

        Optional<String> result = dialog.showAndWait();
        String entered = "none.";

        if (result.isPresent()) {
            entered = result.get();
        }
        return entered;
    }
}
