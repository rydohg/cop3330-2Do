package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DataOpsTest {
    @Test
    void readDataFile() {

    }

    @Test
    void writeDataToFile() {
        DataOps dataSingleton = DataOps.getInstance();
        dataSingleton.nameToDoList("testList");
        dataSingleton.addItem(new ListItem("test item", "2021-07-06"));
//        dataSingleton.writeDataToFile();
    }
}