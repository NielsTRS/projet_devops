package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dataframe {
    protected List<String> columns;
    protected List<Map<String, Object>> data;

    // Constructor without column labels
    public Dataframe() {
        this.columns = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    // Constructor with column labels
    public Dataframe(List<String> columns) {
        this.columns = columns;
        this.data = new ArrayList<>();
    }

    public List<String> getColumnLabels() {
        return this.columns;
    }

    public void setColumnLabels(List<String> columns) {
        this.columns = columns;
    }

    // Add a row to the Dataframe
    public void addRow(Map<String, Object> row) {
        if (row.keySet().containsAll(this.columns)) {
            this.data.add(row);
        } else {
            throw new IllegalArgumentException("The row doesn't contain all columns of the dataframe");
        }
    }

    // Get a value by row index and column label
    public Object getValue(int rowIndex, String columnLabel) {
        if (rowIndex < 0 || rowIndex >= data.size()) {
            throw new IndexOutOfBoundsException("Invalid row index");
        }
        if (!this.columns.contains(columnLabel)) {
            throw new IllegalArgumentException("Column label not found");
        }
        return data.get(rowIndex).get(columnLabel);
    }

    // Get the number of rows
    public int getRowCount() {
        return data.size();
    }

    // Get the number of columns
    public int getColumnCount() {
        return columns.size();
    }

    public List<Object> getRow(int rowIndex) {
        // return data.get(rowIndex);
        if (rowIndex < 0 || rowIndex >= data.size()) {
            throw new IndexOutOfBoundsException("Invalid row index");
        }

        List<Object> row = new ArrayList<>();
        for (String column : columns) {
            row.add(data.get(rowIndex).get(column));
        }
        return row;
    }

    public List<Object> getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= columns.size()) {
            throw new IndexOutOfBoundsException("Invalid column index");
        }

        List<Object> column = new ArrayList<>();
        for (Map<String, Object> row : data) {
            column.add(row.get(columns.get(columnIndex)));
        }
        return column;
    }

    public void removeRow(int rowIndex) {
        data.remove(rowIndex);

    }

}