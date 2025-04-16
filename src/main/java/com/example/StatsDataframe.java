package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatsDataframe {
    private Dataframe dataframe;

    public StatsDataframe (Dataframe dataframe) {
        this.dataframe = dataframe;
    }

    // Calculate the mean of a column
    public double mean(String columnLabel) {
        double sum = 0;
        int count = 0;

        for (int i = 0; i < dataframe.getRowCount(); i++) {
            Object value = dataframe.getValue(i, columnLabel);
            if (value instanceof Number) {
                sum += ((Number) value).doubleValue();
                count++;
            } else {
                throw new IllegalArgumentException("Column " + columnLabel + " contains non-numeric values.");
            }
        }

        return count == 0 ? 0 : sum / count;
    }

    // Calculate the minimum of a column
    public double min(String columnLabel) {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < dataframe.getRowCount(); i++) {
            Object value = dataframe.getValue(i, columnLabel);
            if (value instanceof Number) {
                double numValue = ((Number) value).doubleValue();
                if (numValue < min) {
                    min = numValue;
                }
            } else {
                throw new IllegalArgumentException("Column " + columnLabel + " contains non-numeric values.");
            }
        }

        return min;
    }

    // Calculate the maximum of a column
    public double max(String columnLabel) {
        double max = Double.MIN_VALUE;

        for (int i = 0; i < dataframe.getRowCount(); i++) {
            Object value = dataframe.getValue(i, columnLabel);
            if (value instanceof Number) {
                double numValue = ((Number) value).doubleValue();
                if (numValue > max) {
                    max = numValue;
                }
            } else {
                throw new IllegalArgumentException("Column " + columnLabel + " contains non-numeric values.");
            }
        }

        return max;
    }

    // Calculate the median of a column
    public double median(String columnLabel) {
        List<Double> values = new ArrayList<>();

        for (int i = 0; i < dataframe.getRowCount(); i++) {
            Object value = dataframe.getValue(i, columnLabel);
            if (value instanceof Number) {
                values.add(((Number) value).doubleValue());
            } else {
                throw new IllegalArgumentException("Column " + columnLabel + " contains non-numeric values.");
            }
        }

        // If the list is empty, return 0
        if (values.isEmpty()) {
            return 0;
        }

        // Sort the values
        Collections.sort(values);

        int size = values.size();
        if (size % 2 == 1) {
            // Odd number of elements: return the middle one
            return values.get(size / 2);
        } else {
            // Even number of elements: return the average of the two middle ones
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        }
    }
}
