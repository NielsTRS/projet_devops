package com.example;

import java.util.List;

public class ViewDataFrame {
    private Dataframe dataframe;

    public ViewDataFrame(Dataframe dataframe) {
        this.dataframe = dataframe;
    }

    public StringBuilder labelsDataFrame(int index, int size) {

        StringBuilder lab = new StringBuilder();

        lab.append(" ".repeat(index));
        List<String> labels = this.dataframe.getColumnLabels();
        for (String label : labels) {
            lab.append(label);
            lab.append(" ");
            lab.append(" ".repeat(size - label.length()));
        }
        return lab;
    }

    public StringBuilder bodyDataFrame(int index, int size, int begin, int end) {
        if (begin < 0 || end > this.dataframe.getRowCount() || begin >= end) {
            throw new IllegalArgumentException("Invalid row range");
        }

        StringBuilder body = new StringBuilder();
        for (int i = begin; i < end; i++) {
            List<Object> row = this.dataframe.getRow(i);
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

    public void allDataframe() {
        int size = 20;
        int index = 5;

        // View of the labels
        System.out.println(labelsDataFrame(index, size));

        // View of the body of the DataFrame
        System.out.print(bodyDataFrame(index, size, 0, this.dataframe.getRowCount()));
    }

    public void firstRowDataframe(int nbRows) {
        int size = 20;
        int index = 5;

        // View of the labels
        System.out.println(labelsDataFrame(index, size));

        // View of the body of the DataFrame
        System.out.print(bodyDataFrame(index, size, 0, nbRows));
    }

    public void lastRowDataframe(int nbRows) {
        int size = 20;
        int index = 5;

        // View of the labels
        System.out.println(labelsDataFrame(index, size));

        // View of the body of the DataFrame
        System.out.print(bodyDataFrame(index, size, nbRows, this.dataframe.getRowCount()));
    }

}
