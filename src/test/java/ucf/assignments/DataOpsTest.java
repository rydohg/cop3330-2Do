/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Ryan Doherty
 */
package ucf.assignments;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DataOpsTest {
    @Test
    public void readDataFile() {
        File testFile = new File("test.json");
        try {
            FileWriter writer = new FileWriter(testFile);
            writer.write("{\"title\":\"\",\"items\":[{\"description\":\"Test\",\"date\":\"2021-07-10\",\"complete\":false}]}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOps data = DataOps.getInstance();
        data.readDataFile(testFile);
        assertEquals("Test", data.getItems().get(0).description);
        assertEquals("2021-07-10", data.getItems().get(0).date);
    }

    @Test
    public void writeDataToFile() {
        DataOps data = DataOps.getInstance();
        File testFile = new File("test.json");
        data.addItem(new ListItem("test item", "2021-07-06"));
        data.addItem(new ListItem("test item 1 ", "2012-10-06"));
        data.addItem(new ListItem("test item 2 ", "2021-12-06"));
        data.writeDataToFile(testFile);
        assertTrue(testFile.exists());
    }

    @Test
    public void parseWrittenFile(){
        DataOps data = DataOps.getInstance();
        File testFile = new File("test.json");
        // Add test data to singleton
        ArrayList<ListItem> testItems = new ArrayList<>();
        testItems.add(new ListItem("test item", "2021-07-06"));
        testItems.add(new ListItem("test item 1 ", "2012-10-06"));
        testItems.add(new ListItem("test item 2 ", "2021-12-06"));
        data.getItems().addAll(testItems);
        // Write test data to file
        data.writeDataToFile(testFile);
        // Clear data
        data.getItems().clear();
        // Read data
        data.readDataFile(testFile);
        // Compare data in parsed and original arraylists
        for (int i = 0; i < testItems.size(); i++) {
            assertEquals(data.getItems().get(i).description, testItems.get(i).description);
            assertEquals(data.getItems().get(i).date, testItems.get(i).date);
            assertEquals(data.getItems().get(i).complete, testItems.get(i).complete);
        }
    }

    @AfterEach
    public void cleanUp(){
        DataOps data = DataOps.getInstance();
        data.getItems().clear();
        File testFile = new File("test.json");
        if (testFile.exists()){
            testFile.delete();
        }
    }
}