package com.example;

import org.junit.Test;
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
    public void testMedianDataframe() {
        Dataframe dataframe = createDefaultDataframe();
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        double medianAge = statsDataframe.median("Age");

        assertEquals("The median of the 'Age' column", 30.0, medianAge, 0.001);
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
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        double maxAge = statsDataframe.max("Age");

        assertEquals("The max of the 'Age' column", 35.0, maxAge, 0.001);
    }
}