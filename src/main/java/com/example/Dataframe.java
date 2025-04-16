package com.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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

    // Constructor with a .csv file as input
    // The first line of the file should contain the column types
    // The second line of the file should contain the column labels
    // The subsequent lines should contain the data
    public Dataframe(InputStream inputStream) {
        this.columns = new ArrayList<>();
        this.data = new ArrayList<>();

        List<String> col_types = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                List<String> values = List.of(line.split(","));

                if (lineNumber == 0) {
                    // First line contains column types
                    col_types = values;
                } else if (lineNumber == 1) {
                    // Second line contains column labels
                    this.setColumnLabels(values);
                } else {
                    // Subsequent lines contain row data
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 0; i < values.size(); i++) {
                        String type = col_types.get(i);
                        Object value;
                        switch (type.toLowerCase()) {
                            case "int":
                                value = Integer.parseInt(values.get(i));
                                break;
                            case "double":
                                value = Double.parseDouble(values.get(i));
                                break;
                            case "boolean":
                                value = Boolean.parseBoolean(values.get(i));
                                break;
                            case "string":
                            default:
                                value = values.get(i);
                                break;
                        }
                        row.put(this.columns.get(i), value);
                    }
                    this.data.add(row);
                }
                lineNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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