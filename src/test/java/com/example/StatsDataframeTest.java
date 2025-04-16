package com.example;

import org.junit.Test;

import javax.xml.crypto.Data;

import static org.junit.Assert.assertEquals;
import java.util.*;

public class StatsDataframeTest {

    private Dataframe createDefaultDataframe() {
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

        Map<String, Object> row3 = new HashMap<>();
        row3.put("Name", "Charlie");
        row3.put("Age", 35);
        row3.put("Profession", "Teacher");
        dataframe.addRow(row3);

        return dataframe;
    }

    @Test
    public void testMeanDataframe() {
        Dataframe dataframe = createDefaultDataframe();
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        double meanAge = statsDataframe.mean("Age");

        assertEquals("The mean of the 'Age' column",30.0, meanAge, 0.001);
    }

    @Test
    public void testMedianWithOddNumberOfRows() {
        Dataframe dataframe = createDefaultDataframe();
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        double medianAge = statsDataframe.median("Age");

        assertEquals("The median of the 'Age' column with an odd number of rows", 30.0, medianAge, 0.001);
    }

    @Test
    public void testMedianWithEvenNumberOfRows() {
        Dataframe dataframe = createDefaultDataframe();
        Map<String, Object> row4 = new HashMap<>();
        row4.put("Name", "David");
        row4.put("Age", 40);
        row4.put("Profession", "Artist");
        dataframe.addRow(row4);

        StatsDataframe statsDataframe = new StatsDataframe(dataframe);
        double medianAge = statsDataframe.median("Age");

        assertEquals("The median of the 'Age' column with an even number of rows", 32.5, medianAge, 0.001);
    }

    @Test
    public void testMinDataframe() {
        Dataframe dataframe = createDefaultDataframe();
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        double minAge = statsDataframe.min("Age");

        assertEquals("The min of the 'Age' column", 25.0, minAge, 0.001);
    }

    @Test
    public void testMaxDataframe() {
        Dataframe dataframe = createDefaultDataframe();
        Map<String, Object> row4 = new HashMap<>();
        row4.put("Name", "David");
        row4.put("Age", 32);
        row4.put("Profession", "Artist");
        dataframe.addRow(row4);

        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        double maxAge = statsDataframe.max("Age");

        assertEquals("The max of the 'Age' column", 35.0, maxAge, 0.001);
    }

    // mean with a non-numeric value
    @Test(expected = IllegalArgumentException.class)
    public void testMeanWithNonNumericValue() {
        Dataframe dataframe = createDefaultDataframe();
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        // Add a non-numeric value to the "Age" column
        Map<String, Object> row4 = new HashMap<>();
        row4.put("Name", "David");
        row4.put("Age", "Thirty");
        row4.put("Profession", "Artist");
        dataframe.addRow(row4);

        statsDataframe.mean("Age");
    }

    // min with a non-numeric value
    @Test(expected = IllegalArgumentException.class)
    public void testMinWithNonNumericValue() {
        Dataframe dataframe = createDefaultDataframe();
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        // Add a non-numeric value to the "Age" column
        Map<String, Object> row4 = new HashMap<>();
        row4.put("Name", "David");
        row4.put("Age", "Thirty");
        row4.put("Profession", "Artist");
        dataframe.addRow(row4);

        statsDataframe.min("Age");
    }

    // max with a non-numeric value
    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithNonNumericValue() {
        Dataframe dataframe = createDefaultDataframe();

        Map<String, Object> row4 = new HashMap<>();
        row4.put("Name", "David");
        row4.put("Age", "Thirty");
        row4.put("Profession", "Artist");
        dataframe.addRow(row4);

        StatsDataframe statsDataframe = new StatsDataframe(dataframe);
        statsDataframe.max("Age");
    }

    // median with a non-numeric value
    @Test(expected = IllegalArgumentException.class)
    public void testMedianWithNonNumericValue() {
        Dataframe dataframe = createDefaultDataframe();
        Map<String, Object> row4 = new HashMap<>();
        row4.put("Name", "David");
        row4.put("Age", "Thirty");
        row4.put("Profession", "Artist");
        dataframe.addRow(row4);

        StatsDataframe statsDataframe = new StatsDataframe(dataframe);
        statsDataframe.median("Age");
    }

    @Test
    public void testMeanWithEmptyColumn() {
        Dataframe dataframe = new Dataframe(Arrays.asList("EmptyColumn"));
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        double mean = statsDataframe.mean("EmptyColumn");
        assertEquals("The mean of an empty column", 0.0, mean, 0.001);
    }

    @Test
    public void testMedianWithEmptyColumn() {
        Dataframe dataframe = new Dataframe(Arrays.asList("EmptyColumn"));
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        double median = statsDataframe.median("EmptyColumn");
        assertEquals("The median of an empty column", 0.0, median, 0.001);
    }

}