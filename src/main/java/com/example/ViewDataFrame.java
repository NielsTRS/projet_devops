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

    public void allDataframe(Dataframe dataframe) {
        int size = 20;
        int index = 5;

        //View of the labels
        for (int k=0;k<index;k++) {
            System.out.print(" ");
        }
        List<String> labels = dataframe.getColumnLabels();
        for (String label : labels) {
            System.out.print(label + " ");
            for (int j=0;j<size-label.length();j++) {
                System.out.print(" ");
            }
        }

        //View of the rest of the DataFrame
        System.out.println();
        for (int i=0;i<dataframe.getRowCount();i++) {
            List<Object> row = dataframe.getRow(i);
            System.out.print(i+ " ");
            for (int k=0;k<index-(i+" ").length();k++) {
                System.out.print(" ");
            }
            for (int j=0;j<row.size();j++) {
                Object value = row.get(j);
                System.out.print(value+" ");
                for (int k=0;k<size-value.toString().length();k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void firstRowDataframe(Dataframe dataframe, int nbRows) {
        int size = 20;
        int index = 5;

        //View of the labels
        for (int k=0;k<index;k++) {
            System.out.print(" ");
        }
        List<String> labels = dataframe.getColumnLabels();
        for (String label : labels) {
            System.out.print(label + " ");
            for (int j=0;j<size-label.length();j++) {
                System.out.print(" ");
            }
        }

        //View of the rest of the DataFrame
        System.out.println();
        for (int i=0;i<nbRows;i++) {
            List<Object> row = dataframe.getRow(i);
            System.out.print(i+" ");
            for (int k=0;k<index-(i+" ").length();k++) {
                System.out.print(" ");
            }
            for (int j=0;j<row.size();j++) {
                Object value = row.get(j);
                System.out.print(value+" ");
                for (int k=0;k<size-value.toString().length();k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void lastRowDataframe(Dataframe dataframe, int nbRows) {
        int size = 20;
        int index = 5;

        //View of the labels
        for (int k=0;k<index;k++) {
            System.out.print(" ");
        }
        List<String> labels = dataframe.getColumnLabels();
        for (String label : labels) {
            System.out.print(label + " ");
            for (int j=0;j<size-label.length();j++) {
                System.out.print(" ");
            }
        }

        //View of the rest of the DataFrame
        System.out.println();
        for (int i=nbRows;i<dataframe.getRowCount();i++) {
            List<Object> row = dataframe.getRow(i);
            System.out.print(i+ " ");
            for (int k=0;k<index-(i+" ").length();k++) {
                System.out.print(" ");
            }
            for (int j=0;j<row.size();j++) {
                Object value = row.get(j);
                System.out.print(value+" ");
                for (int k=0;k<size-value.toString().length();k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
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
        view.firstRowDataframe(dataframe,1);
        System.out.println();
        view.lastRowDataframe(dataframe,1);

    }
}
