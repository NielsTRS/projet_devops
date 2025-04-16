package com.example;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {

        // Créer un DataFrame avec des données fictives
        System.out.println("Demo de DataFrame");

        List<String> columnLabels = Arrays.asList("Name", "Age", "Profession");

        Dataframe dataframe = new Dataframe(columnLabels);

        Map<String, Object> row = new HashMap<>();
        Map<String, Object> row2 = new HashMap<>();
        Map<String, Object> row3 = new HashMap<>();

        row.put("Name", "Alice");
        row.put("Age", 25);
        row.put("Profession", "Engineer");

        row2.put("Name", "Briséis");
        row2.put("Age", 30);
        row2.put("Profession", "Doctor");

        row3.put("Name", "Bob");
        row3.put("Age", 70);
        row3.put("Profession", "Informatician");

        dataframe.addRow(row);
        dataframe.addRow(row2);
        dataframe.addRow(row3);

        demo(dataframe);

        // Créer un DataFrame avec des données fictives à partir d'un fichier CSV
        System.out.println("\nDemo de DataFrame à partir d'un fichier CSV\n");

        URL resource = Demo.class.getClassLoader().getResource("demo.csv");

        // Pass the absolute path to the constructor
        String csvFilePath = null;
        try {
            csvFilePath = Paths.get(resource.toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Dataframe dataframeCSV = new Dataframe(csvFilePath);
        demo(dataframeCSV);

    }

    private static void demo(Dataframe dataframe) {
        // Afficher le DataFrame
        ViewDataFrame view = new ViewDataFrame(dataframe);
        view.allDataframe();

        // Calculer des statistiques sur une colonne
        StatsDataframe statsDataframe = new StatsDataframe(dataframe);

        double meanAge = statsDataframe.mean("Age");
        double minAge = statsDataframe.min("Age");
        double maxAge = statsDataframe.max("Age");
        double medianAge = statsDataframe.median("Age");

        System.out.println("\nLa moyenne de la colonne 'Age' est : " + meanAge);
        System.out.println("La valeur minimale de la colonne 'Age' est : " + minAge);
        System.out.println("La valeur maximale de la colonne 'Age' est : " + maxAge);
        System.out.println("La médiane de la colonne 'Age' est : " + medianAge);

        // Selection de dataframe
        System.out.println("\nSélection de colonnes spécifiques :");

        SelectDataframe selected = new SelectDataframe(dataframe, Arrays.asList("Name", "Profession"));
        ViewDataFrame view2 = new ViewDataFrame(selected);
        view2.allDataframe();
    }
}