package com.example;

import java.util.ArrayList;
import java.util.List;

public class Dataframe {


    
    // Constructor
    public Dataframe() {
        this.columnLabels = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    private List<String> columnLabels;
    private List<List<Object>> data;

    // Constructor with column labels
    public Dataframe(List<String> columnLabels) {
        this.columnLabels = columnLabels;
        this.data = new ArrayList<>();
    }

    public List<String> getColumnLabels() {
        return columnLabels;
    }

    public void setColumnLabels(List<String> columnLabels) {
        this.columnLabels = columnLabels;
    }

    // Add a row to the Dataframe
    public void addRow(List<Object> row) {
        if (row.size() != columnLabels.size()) {
            throw new IllegalArgumentException("Row size must match the number of columns");
        }
        data.add(row);
    }

    // Get a value by row index and column label
    public Object getValue(int rowIndex, String columnLabel) {
        int columnIndex = columnLabels.indexOf(columnLabel);
        if (columnIndex == -1) {
            throw new IllegalArgumentException("Column label not found");
        }
        return data.get(rowIndex).get(columnIndex);
    }

    // Get the number of rows
    public int getRowCount() {
        return data.size();
    }

    // Get the number of columns
    public int getColumnCount() {
        return columnLabels.size();
    }

    public List<Object> getRow(int rowIndex) {
        return data.get(rowIndex);
    }

    public List<Object> getColumn(int columnIndex) {
        List<Object> column = new ArrayList<>();
        for (List<Object> row : data) {
            column.add(row.get(columnIndex));
        }
        return column;
    }

    public void removeRow(int rowIndex) {
        data.remove(rowIndex);

    }

}