package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectDataframeTest {

    private List<String> getDefaultColumnLabels() {
        return Arrays.asList("Name", "Age", "Profession");
    }

    private Dataframe createDefaultDataframe() {
        List<String> columnLabels = getDefaultColumnLabels();
        Dataframe dataframe = new Dataframe(columnLabels);

        Map<String, Object> row1 = new HashMap<>();
        row1.put("Name", "Alice");
        row1.put("Age", 26);
        row1.put("Profession", "Engineer");
        dataframe.addRow(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("Name", "Bob");
        row2.put("Age", 30);
        row2.put("Profession", "Doctor");
        dataframe.addRow(row2);

        return dataframe;
    }

    @Test
    public void testSelectRowsRowCount() {
        Dataframe dataframe = createDefaultDataframe();
        SelectDataframe selected = new SelectDataframe(dataframe, 0, 1);

        assertEquals("The number of rows with bounds",2, selected.getRowCount());
    }

    @Test
    public void testSelectRowsContent() {
        Dataframe dataframe = createDefaultDataframe();
        SelectDataframe selected = new SelectDataframe(dataframe, 0, 1);

        assertEquals("Content of the first row with bounds", Arrays.asList("Alice", 26, "Engineer"), selected.getRow(0));
        assertEquals("Content of the second row with bounds", Arrays.asList("Bob", 30, "Doctor"), selected.getRow(1));
    }

    @Test
    public void testSelectSingleRow() {
        Dataframe dataframe = createDefaultDataframe();
        SelectDataframe selected = new SelectDataframe(dataframe, 0, 0);

        // Vérifie que seule la ligne 0 est incluse
        assertEquals("The number of selected rows",1, selected.getRowCount());
        assertEquals("Content of the first and only row",Arrays.asList("Alice", 26, "Engineer"), selected.getRow(0));
    }

    @Test
    public void testSelectColumnsRowCount() {
        Dataframe dataframe = createDefaultDataframe();
        SelectDataframe selected = new SelectDataframe(dataframe, Arrays.asList("Name", "Profession"));
        assertEquals("The number of rows with labels", 2, selected.getRowCount());
    }

    @Test
    public void testSelectColumnsFirstRowContent() {
        Dataframe dataframe = createDefaultDataframe();
        SelectDataframe selected = new SelectDataframe(dataframe, Arrays.asList("Name", "Profession"));
        assertEquals("Content of the first row with labels",
                Arrays.asList("Alice", "Engineer"), selected.getRow(0));
    }

    @Test
    public void testSelectColumnsSecondRowContent() {
        Dataframe dataframe = createDefaultDataframe();
        SelectDataframe selected = new SelectDataframe(dataframe, Arrays.asList("Name", "Profession"));
        assertEquals("Content of the second row with labels",
                Arrays.asList("Bob", "Doctor"), selected.getRow(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRowRange() {
        Dataframe dataframe = createDefaultDataframe();
        new SelectDataframe(dataframe, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidColumnLabels() {
        Dataframe dataframe = createDefaultDataframe();
        new SelectDataframe(dataframe, Arrays.asList("InvalidColumn"));
    }

    @Test
    public void testAdvancedSelectionRowCount() {
        Dataframe dataframe = createDefaultDataframe();
        // Select rows where Age > 25
        SelectDataframe selected = new SelectDataframe(dataframe, row -> (int) row.get("Age") > 25);
        assertEquals("Number of rows where Age > 25", 2, selected.getRowCount());
    }

    @Test
    public void testAdvancedSelectionRowContent() {
        Dataframe dataframe = createDefaultDataframe();
        // Select rows where Age > 25
        SelectDataframe selected = new SelectDataframe(dataframe, row -> (int) row.get("Age") > 25);

        List<List<Object>> expectedRows = Arrays.asList(
                Arrays.asList("Alice", 26, "Engineer"),
                Arrays.asList("Bob", 30, "Doctor")
        );

        for (int i = 0; i < expectedRows.size(); i++) {
            assertEquals("Content of row " + i + " where Age > 25", expectedRows.get(i), selected.getRow(i));
        }
    }
}