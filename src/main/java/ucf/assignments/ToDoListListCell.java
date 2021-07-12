package ucf.assignments;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static ucf.assignments.ToDoController.simpleDialog;


public class ToDoListListCell extends ListCell<ListItem> {
    private FXMLLoader mLoader;
    private ListItem listItem;
    @FXML
    private TextArea description;
    @FXML
    private Label date;
    @FXML
    private CheckBox complete;
    @FXML
    private GridPane gridPane;

    private ListView<ListItem> toDoList;

    public ToDoListListCell(ListView<ListItem> toDoList){
        super();
        this.toDoList = toDoList;
    }

    @Override
    protected void updateItem(ListItem item, boolean empty) {
        super.updateItem(item, empty);
        listItem = item;
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
            setText(null);
            setGraphic(gridPane);
        }
    }

    public void markComplete(){
        listItem.setComplete(!listItem.complete);
    }

    public void deleteItemOnClick(){
        deleteItem();
        setText(null);
        setGraphic(null);
        DataOps data = DataOps.getInstance();
        toDoList.getItems().clear();
        for (ListItem i : data.getItems()) {
            toDoList.getItems().add(i);
        }
    }

    private void deleteItem() {
        DataOps data = DataOps.getInstance();
        data.getItems().remove(listItem);
    }

    public void editDetailsOnClick(){
        String descriptionString = simpleDialog("Edit Details", "Please enter the description", listItem.description);
        String dateString = "";
        boolean invalidDate = true;
        while (invalidDate){
            dateString = simpleDialog("Edit Details", "Please enter the date in the form YYYY-MM-DD", listItem.date);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false);
            try {
                if (dateString.length() != 10){ throw new Exception(); }
                format.parse(dateString);
                invalidDate = false;
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Date");
                alert.setContentText("Please enter a valid date in YYYY-MM-DD format");
                alert.showAndWait();
            }
        }
        editDetails(descriptionString, dateString);
        description.setText(descriptionString);
        date.setText(dateString);
    }

    public void editDetails(String description, String date){
        listItem.setDescription(description);
        listItem.setDate(date);
    }
}
