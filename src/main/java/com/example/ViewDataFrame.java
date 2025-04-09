package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ViewDataFrame {
    private Dataframe dataframe;

    public ViewDataFrame(Dataframe dataframe) {
        this.dataframe = dataframe;
    }

    public StringBuilder labelsDataFrame(Dataframe dataframe, int index, int size) {
        StringBuilder lab = new StringBuilder();

        lab.append(" ".repeat(index));
        List<String> labels = dataframe.getColumnLabels();
        for (String label : labels) {
            lab.append(label);
            lab.append(" ");
            lab.append(" ".repeat( size - label.length()));
        }
        return lab;
    }

    public StringBuilder bodyDataFrame(Dataframe dataframe, int index, int size, int begin, int end) {
        StringBuilder body = new StringBuilder();
        for (int i = begin; i < end; i++) {
            List<Object> row = dataframe.getRow(i);
            body.append(i);
            body.append(" ");
            body.append(" ".repeat(index - (i + " ").length()));
            for (int j = 0; j < row.size(); j++) {
                Object value = row.get(j);
                body.append(value);
                body.append(" ");
                body.append(" ".repeat(size - value.toString().length()));
            }
            body.append("\n");
        }
        return body;
    }

    public void allDataframe(Dataframe dataframe) {
        int size = 20;
        int index = 5;

        //View of the labels
        System.out.println(labelsDataFrame(dataframe, index, size));

        //View of the body of the DataFrame
        System.out.print(bodyDataFrame(dataframe, index, size, 0, dataframe.getRowCount()));
    }

    public void firstRowDataframe(Dataframe dataframe, int nbRows) {
        int size = 20;
        int index = 5;

        //View of the labels
        System.out.println(labelsDataFrame(dataframe, index, size));

        //View of the body of the DataFrame
        System.out.print(bodyDataFrame(dataframe, index, size, 0, nbRows));
    }

    public void lastRowDataframe(Dataframe dataframe, int nbRows) {
        int size = 20;
        int index = 5;

        //View of the labels
        System.out.println(labelsDataFrame(dataframe, index, size));

        //View of the body of the DataFrame
        System.out.print(bodyDataFrame(dataframe, index, size, nbRows, dataframe.getRowCount()));
    }

/*    public static void main(String[] args) {
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
        view.allDataframe(dataframe);
        System.out.println();
        view.firstRowDataframe(dataframe, 1);
        System.out.println();
        view.lastRowDataframe(dataframe, 1);
        System.out.println();

    }*/
}
