package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class DataframeTest {

    @Test
    public void testCreateDataframe() {
        Dataframe dataframe = new Dataframe();
        assertNotNull("Check the initialisation of the dataframe.", dataframe);
    }

    @Test
    public void testCreateDataframeCSV() throws Exception {
        List<String> columnLabels = Arrays.asList("age", "nom", "prenom");

        InputStream csvInputStream = getClass().getClassLoader().getResourceAsStream("DataframeTest.csv");

        if (csvInputStream == null) {
            System.out.println("CSV file not found!");
            return;
        }

        Dataframe dataframe = new Dataframe(csvInputStream);

        assertNotNull("Check the initialization of the dataframe.", dataframe);
        assertEquals("Check the initialization of columns of the dataframe.", columnLabels,
                dataframe.getColumnLabels());
    }

    @Test
    public void testCreateDataframeColumns() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        assertNotNull("Check the initialisation of the dataframe.", dataframe);
        assertEquals("Check the initialisation of columns of the dataframe.", columnLabels,
                dataframe.getColumnLabels());
    }

    @Test
    public void testGetColumnCount() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        assertEquals("Check the number of columns.", 3, dataframe.getColumnCount());
    }

    @Test
    public void testSetColumnLabels() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe();
        dataframe.setColumnLabels(columnLabels);
        assertEquals("Check the addition of columns in the dataframe.", columnLabels, dataframe.getColumnLabels());
    }

    @Test
    public void testAddRow() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");
        dataframe.addRow(row);

        assertEquals("Check the number of rows.", 1, dataframe.getRowCount());
        assertEquals("Check the added row.", Arrays.asList("Alice", 25, "Engineer"), dataframe.getRow(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void InvalidAddRowTest() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        dataframe.addRow(row);
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
        assertEquals("Check the number of rows.", 1, dataframe.getRowCount());
        assertEquals("Check that the deleted line has been properly deleted.", Arrays.asList("Bob", 30, "Doctor"),
                dataframe.getRow(0));
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
        assertEquals("Check the number of rows.", 1, dataframe.getRowCount());
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

        assertEquals("Check the added Name.", "Alice", dataframe.getValue(0, "Name"));
        assertEquals("Check the added Age.", 25, dataframe.getValue(0, "Age"));
        assertEquals("Check the added Profession.", "Engineer", dataframe.getValue(0, "Profession"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void InvalidColumnNameTest() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");
        dataframe.addRow(row);
        dataframe.getValue(0, "InvalidColumn");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TooBigRowIndexTest() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");
        dataframe.addRow(row);
        dataframe.getValue(1, "Name");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void NegativeRowIndexTest() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row = new HashMap<>();
        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");
        dataframe.addRow(row);
        dataframe.getValue(-1, "Name");
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

        List<Object> col = dataframe.getColumn(0);
        assertEquals("Check the first column.", Arrays.asList("Alice", "Bob"), col);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void NegativeIndexGetColumnTest() {
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

        List<Object> col = dataframe.getColumn(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TooMuchIndexGetColumnTest() {
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

        List<Object> col = dataframe.getColumn(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void NegativeIndexGetRowTest() {
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

        List<Object> row = dataframe.getRow(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TooMuchIndexGetRowTest() {
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

        List<Object> row = dataframe.getRow(5);
    }
}