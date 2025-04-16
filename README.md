# DataFrame - Projet DevOps

Build | Status
-- | --
JUnit | [![CI](https://github.com/NielsTRS/projet_devops/actions/workflows/maven.yml/badge.svg)](https://github.com/NielsTRS/projet_devops/actions/workflows/build.yml)
Test coverage | [![codecov](https://codecov.io/gh/NielsTRS/projet_devops/graph/badge.svg?token=U8NJXINQAQ)](https://codecov.io/gh/NielsTRS/projet_devops)

## Commandes
```bash
mvn -B package
```

## Fonctionnalités
4 classes Java :
- DataFrame :
  -  2 constructeurs : Création d'un dataframe avec et sans labels
  - Récupérer les labels
  - Ajouter les labels
  - Récupérer une valeur
  - Récupérer le nombre de lignes
  - Récupérer le nombre de colonnes
  - Récupérer une ligne
  - Récupérer une colonne
  - Supprimer une ligne
  - Ajouter une ligne
  
- ViewDataframe :
    - 1 constructeur : Récupérer un dataframe
    - Récupérer les labels sous forme de chaîne de caractères
    - Récupérer le corps du dataframe sous forme de chaîne de caractères
    - Afficher tout le dataframe
    - Afficher les i premières lignes du dataframe
    - Afficher les i dernières lignes du dataframe
  
- SelectDataframe :
    - 3 constructeurs : 
        - Création d'un sous-dataframe à partir d'un dataframe existant par plage d'indices de lignes
        - Création d'un sous-dataframe à partir d'un dataframe existant par sous-ensemble de labels
        - Création d'un sous-dataframe à partir d'un dataframe existant filtré par prédicat
    - Ajout de lignes depuis un dataframe existant selon une plage d’indices
    - Ajout de lignes depuis un dataframe existant selon un sous-ensemble de labels
  
- StatsDataframe :
    - 1 constructeur : Récupérer un dataframe
    - Calculer la moyenne d'une colonne
    - Calculer la médiane d'une colonne
    - Calculer le minimum d'une colonne
    - Calculer le maximum d'une colonne
  
## Outils utilisés
- Git : 
  - Permet de gérer différentes versions du code, sur plusieurs branches
  - Gestion des conflits

- Maven :
  - Créer la structure de base du projet
  - Ajouter des plugins
  
- JUnit :
  - Créer des tests unitaires

- Jacoco :
  - Générer un rapport de couverture de code

- Codecov :
    - Intégration de Jacoco avec Github
    - Afficher la couverture de code sur le dépôt Github
  
- Dépôt Github :

## Workflow git
## Docker
## Feedback



