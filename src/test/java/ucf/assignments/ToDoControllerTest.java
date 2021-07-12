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

import static org.junit.jupiter.api.Assertions.*;

class ToDoControllerTest {
    @Test
    void newList() {
        // Initialize DataOps singleton
        DataOps data = DataOps.getInstance();
        data.addItem(new ListItem("Test", "2021-07-09"));
        ToDoController controller = new ToDoController();
        // Test if the new list is created in the DataOps singleton by newList
        controller.newList();
        data.addItem(new ListItem("TEST", "2021-07-09"));
        assertEquals("TEST", data.getItems().get(0).description);
    }

    @Test
    void loadLists() {
        // Initialize singleton
        ToDoController controller = new ToDoController();
        File testFile = new File("test.json");
        try {
            FileWriter writer = new FileWriter(testFile);
            writer.write("{\"title\":\"\",\"items\":[{\"description\":\"Test\",\"date\":\"2021-07-10\",\"complete\":false}]}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.loadLists(testFile);
        DataOps data = DataOps.getInstance();
        // Test if loadLists correctly loads a list from a test file
        assertEquals("Test",data.getItems().get(0).description);
    }

    @Test
    void saveLists() {
        // Initialize singleton
        ToDoController controller = new ToDoController();
        DataOps data = DataOps.getInstance();
        // Add test data
        data.addItem(new ListItem("Test 1", "2021-07-01"));
        // Save to file then test if file is there
        File testFile = new File("test.json");
        controller.saveList(testFile);
        assertTrue(testFile.exists());
    }

    @Test
    public void dateFormat(){
        assertTrue(ToDoController.verifyDate("2021-07-11"));
        assertTrue(ToDoController.verifyDate("1970-01-01"));
        assertFalse(ToDoController.verifyDate("1970-01-1"));
        assertFalse(ToDoController.verifyDate("1970-1-01"));
        assertFalse(ToDoController.verifyDate("1970-1-010101"));
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