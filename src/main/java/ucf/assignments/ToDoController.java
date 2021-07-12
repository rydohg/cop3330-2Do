package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
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
    boolean filtered = false;

    public void initialize(){
        toDoListView.setCellFactory(lv -> new ToDoListListCell(toDoListView));
    }

    public void refreshListView(){
        refreshListView(false, false);
    }

    public void refreshListView(boolean onlyComplete, boolean onlyIncomplete){
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
        DataOps data = DataOps.getInstance();
        data.addItem(new ListItem(description, date));
    }

    public void newListOnClick(){
        // Call newList()
        newList();
        // Update list selector list view
        refreshListView();
    }

    public void helpDialog(){
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

    public void newItemOnClick(){
        String description = simpleDialog("New Item", "Please enter the description", "");
        String date = "";
        boolean invalidDate = true;
        while (invalidDate){
            date = simpleDialog("New Item", "Please enter the date in the form YYYY-MM-DD", "2021-07-12");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false);
            try {
                if (date.length() != 10){ throw new Exception(); }
                format.parse(date);
                invalidDate = false;
            } catch (Exception e) {
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

    public void onlyCompleteOnClick(){
        if (!filtered){
            refreshListView(true, false);
        } else {
            refreshListView();
        }
        filtered = !filtered;
    }

    public void onlyIncompleteOnClick(){
        if (!filtered){
            refreshListView(false, true);
        } else {
            refreshListView();
        }
        filtered = !filtered;    }

    public static String simpleDialog(String title, String prompt, String defaultString){
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
