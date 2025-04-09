package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectDataframeTest {

    @Test
    public void testSelectRows() {
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

        SelectDataframe selected = new SelectDataframe(dataframe, 0, 1);

        assertEquals(1, selected.getRowCount());
        assertEquals(Arrays.asList("Alice", 25, "Engineer"), selected.getRow(0));
    }

    @Test
    public void testSelectColumns() {
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

        SelectDataframe selected = new SelectDataframe(dataframe, Arrays.asList("Name", "Profession"));

        assertEquals(2, selected.getRowCount());
        assertEquals(Arrays.asList("Alice", "Engineer"), selected.getRow(0));
        assertEquals(Arrays.asList("Bob", "Doctor"), selected.getRow(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRowRange() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);

        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");
        dataframe.addRow(row);

        new SelectDataframe(dataframe, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidColumnLabels() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);

        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");
        dataframe.addRow(row);

        new SelectDataframe(dataframe, Arrays.asList("InvalidColumn"));
    }
}