package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class DataframeTest {

    @Test
    public void testAddRow() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");
        dataframe.addRow(row);

        assertEquals(1, dataframe.getRowCount());
        assertEquals(Arrays.asList("Alice", 25, "Engineer"), dataframe.getRow(0));
    }

    @Test
    public void testGetColumn() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row1 = new HashMap<>();
        row1.put("Name", "Alice");
        row1.put("Age", 25);
        row1.put("Profession", "Engineer");
        dataframe.addRow(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("Name", "Bob");
        row2.put("Age", 30);
        row2.put("Profession", "Doctor");
        dataframe.addRow(row2);

        List<Object> expectedColumn = Arrays.asList("Alice", "Bob");
        assertEquals(expectedColumn, dataframe.getColumn(0));
    }

    @Test
    public void testRemoveRow() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row1 = new HashMap<>();
        row1.put("Name", "Alice");
        row1.put("Age", 25);
        row1.put("Profession", "Engineer");
        dataframe.addRow(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("Name", "Bob");
        row2.put("Age", 30);
        row2.put("Profession", "Doctor");
        dataframe.addRow(row2);

        dataframe.removeRow(0);
        assertEquals(1, dataframe.getRowCount());
        assertEquals(Arrays.asList("Bob", 30, "Doctor"), dataframe.getRow(0));
    }

    @Test
    public void testGetRowCount() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        assertEquals(0, dataframe.getRowCount());

        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");
        dataframe.addRow(row);
        assertEquals(1, dataframe.getRowCount());
    }

    @Test
    public void testGetValue() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");
        dataframe.addRow(row);

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