package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ViewDataFrameTest {

    private Dataframe createDefaultDataframe() {
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
        return dataframe;
    }


    @Test
    public void testLabels() {
        Dataframe dataframe = createDefaultDataframe();
        ViewDataFrame view = new ViewDataFrame(dataframe);
        StringBuilder labels = new StringBuilder();
        labels.append("     Name                 Age                  Profession           ");

        assertEquals("Check of the labels of the Dataframe.",0,labels.compareTo(view.labelsDataFrame(dataframe, 5, 20)));
    }

    @Test
    public void testBodyAll() {
        Dataframe dataframe = createDefaultDataframe();
        ViewDataFrame view = new ViewDataFrame(dataframe);
        StringBuilder body = new StringBuilder();
        body.append("0    Athéna               23                   Engineer             \n1    Briséis              30                   Doctor               \n");

        //assertEquals(body,view.bodyDataFrame(dataframe, 5, 20, 1, dataframe.getRowCount()));
        assertEquals("Check the body of the Dataframe.",0,body.compareTo(view.bodyDataFrame(dataframe, 5, 20, 0, dataframe.getRowCount())));
    }

    @Test
    public void testBodyFirst() {
        Dataframe dataframe = createDefaultDataframe();

        ViewDataFrame view = new ViewDataFrame(dataframe);
        StringBuilder body = new StringBuilder();
        body.append("0    Athéna               23                   Engineer             \n");

        //assertEquals(body,view.bodyDataFrame(dataframe, 5, 20, 1, dataframe.getRowCount()));
        assertEquals("Check the begin of the Dataframe.",0,body.compareTo(view.bodyDataFrame(dataframe, 5, 20, 0, 1)));
    }

    @Test
    public void testBodyLast() {
        Dataframe dataframe = createDefaultDataframe();
        ViewDataFrame view = new ViewDataFrame(dataframe);
        StringBuilder body = new StringBuilder();
        body.append("1    Briséis              30                   Doctor               \n");

        assertEquals("Check the end of the Dataframe.",0,body.compareTo(view.bodyDataFrame(dataframe, 5, 20, 1, dataframe.getRowCount())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBeginEnd() {
        Dataframe dataframe = createDefaultDataframe();
        ViewDataFrame view = new ViewDataFrame(dataframe);

        view.bodyDataFrame(dataframe, 5, 20, 1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBegin() {
        Dataframe dataframe = createDefaultDataframe();
        ViewDataFrame view = new ViewDataFrame(dataframe);

        view.bodyDataFrame(dataframe, 5, 20, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEnd() {
        Dataframe dataframe = createDefaultDataframe();
        ViewDataFrame view = new ViewDataFrame(dataframe);

        view.bodyDataFrame(dataframe, 5, 20, 1, dataframe.getRowCount()+3);
    }

}
