package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class DataframeTest {

    @Test
    public void testAddRow() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        List<Object> row = Arrays.asList("Alice", 25, "Engineer");
        dataframe.addRow(row);

        assertEquals(1, dataframe.getRowCount());
        assertEquals(row, dataframe.getRow(0));
    }

    @Test
    public void testGetColumn() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        dataframe.addRow(Arrays.asList("Alice", 25, "Engineer"));
        dataframe.addRow(Arrays.asList("Bob", 30, "Doctor"));

        List<Object> expectedColumn = Arrays.asList("Alice", "Bob");
        assertEquals(expectedColumn, dataframe.getColumn(0));
    }

    @Test
    public void testRemoveRow() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        dataframe.addRow(Arrays.asList("Alice", 25, "Engineer"));
        dataframe.addRow(Arrays.asList("Bob", 30, "Doctor"));

        dataframe.removeRow(0);
        assertEquals(1, dataframe.getRowCount());
        assertEquals(Arrays.asList("Bob", 30, "Doctor"), dataframe.getRow(0));
    }

    @Test
    public void testGetRowCount() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        assertEquals(0, dataframe.getRowCount());

        dataframe.addRow(Arrays.asList("Alice", 25, "Engineer"));
        assertEquals(1, dataframe.getRowCount());
    }

    @Test
    public void testGetValue() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        dataframe.addRow(Arrays.asList("Alice", 25, "Engineer"));

        assertEquals("Alice", dataframe.getValue(0, "Name"));
        assertEquals(25, dataframe.getValue(0, "Age"));
        assertEquals("Engineer", dataframe.getValue(0, "Profession"));
    }

    @Test
    public void testColumnLabels() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);

        assertEquals(columnLabels, dataframe.getColumnLabels());
    }
}