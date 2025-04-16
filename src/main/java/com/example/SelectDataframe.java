package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class SelectDataframe extends Dataframe {

    // Build new Dataframe from a subset of rows
    public SelectDataframe(Dataframe dataframe, int startRow, int endRow) {
        super(dataframe.getColumnLabels());

        if (startRow < 0 || endRow >= dataframe.getRowCount() || startRow > endRow) {
            throw new IllegalArgumentException("Invalid row range");
        }
        this.addRowsFromRange(dataframe, startRow, endRow + 1);
    }

    // Build new Dataframe from a subset of columns
    public SelectDataframe(Dataframe dataframe, List<String> columnLabels) {
        super(columnLabels); // Initialize with the specified column labels

        for (String columnLabel : columnLabels) {
            if (!dataframe.getColumnLabels().contains(columnLabel)) {
                throw new IllegalArgumentException("Column label not found: " + columnLabel);
            }
        }
        this.addRowsFromColumns(dataframe, columnLabels);
    }

    // Advanced selection: filter rows based on a condition
    public SelectDataframe(Dataframe dataframe, Predicate<Map<String, Object>> condition) {
        super(dataframe.getColumnLabels());
        for (Map<String, Object> row : dataframe.data) {
            if (condition.test(row)) {
                this.addRow(row);
            }
        }
    }

    private void addRowsFromRange(Dataframe dataframe, int startRow, int endRow) {
        for (int i = startRow; i < endRow; i++) {
            Map<String, Object> row = new HashMap<>();
            for (String column : dataframe.getColumnLabels()) {
                row.put(column, dataframe.getValue(i, column));
            }
            this.addRow(row);
        }
    }

    private void addRowsFromColumns(Dataframe dataframe, List<String> columnLabels) {
        for (int i = 0; i < dataframe.getRowCount(); i++) {
            Map<String, Object> newRow = new HashMap<>();
            for (String column : columnLabels) {
                newRow.put(column, dataframe.getValue(i, column));
            }
            this.addRow(newRow);
        }
    }
}