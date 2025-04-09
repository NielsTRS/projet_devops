package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ViewDataFrameTest {

    @Test
    public void testLabels() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row1 = new HashMap<>();
        row1.put("Name", "Athéna");
        row1.put("Age", 23);
        row1.put("Profession", "Engineer");
        dataframe.addRow(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("Name", "Briséis");
        row2.put("Age", 30);
        row2.put("Profession", "Doctor");
        dataframe.addRow(row2);

        ViewDataFrame view = new ViewDataFrame(dataframe);
        StringBuilder labels = new StringBuilder();
        labels.append("     Name                 Age                  Profession           ");

        assertEquals(0,labels.compareTo(view.labelsDataFrame(dataframe, 5, 20)));
    }

    @Test
    public void testBodyAll() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row1 = new HashMap<>();
        row1.put("Name", "Athéna");
        row1.put("Age", 23);
        row1.put("Profession", "Engineer");
        dataframe.addRow(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("Name", "Briséis");
        row2.put("Age", 30);
        row2.put("Profession", "Doctor");
        dataframe.addRow(row2);

        ViewDataFrame view = new ViewDataFrame(dataframe);
        StringBuilder body = new StringBuilder();
        body.append("0    Athéna               23                   Engineer             \n1    Briséis              30                   Doctor               \n");

        //assertEquals(body,view.bodyDataFrame(dataframe, 5, 20, 1, dataframe.getRowCount()));
        assertEquals(0,body.compareTo(view.bodyDataFrame(dataframe, 5, 20, 0, dataframe.getRowCount())));
    }

    @Test
    public void testBodyFirst() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row1 = new HashMap<>();
        row1.put("Name", "Athéna");
        row1.put("Age", 23);
        row1.put("Profession", "Engineer");
        dataframe.addRow(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("Name", "Briséis");
        row2.put("Age", 30);
        row2.put("Profession", "Doctor");
        dataframe.addRow(row2);

        ViewDataFrame view = new ViewDataFrame(dataframe);
        StringBuilder body = new StringBuilder();
        body.append("0    Athéna               23                   Engineer             \n");

        //assertEquals(body,view.bodyDataFrame(dataframe, 5, 20, 1, dataframe.getRowCount()));
        assertEquals(0,body.compareTo(view.bodyDataFrame(dataframe, 5, 20, 0, 1)));
    }

    @Test
    public void testBodyLast() {
        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");
        Dataframe dataframe = new Dataframe(columnLabels);
        Map<String, Object> row1 = new HashMap<>();
        row1.put("Name", "Athéna");
        row1.put("Age", 23);
        row1.put("Profession", "Engineer");
        dataframe.addRow(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("Name", "Briséis");
        row2.put("Age", 30);
        row2.put("Profession", "Doctor");
        dataframe.addRow(row2);

        ViewDataFrame view = new ViewDataFrame(dataframe);
        StringBuilder body = new StringBuilder();
        body.append("1    Briséis              30                   Doctor               \n");

        assertEquals(0,body.compareTo(view.bodyDataFrame(dataframe, 5, 20, 1, dataframe.getRowCount())));
    }

}
