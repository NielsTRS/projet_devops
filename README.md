# DataFrame - Projet DevOps

Build | Status
-- | --
JUnit | [![CI](https://github.com/NielsTRS/projet_devops/actions/workflows/maven.yml/badge.svg)](https://github.com/NielsTRS/projet_devops/actions/workflows/build.yml)
Test coverage | [![codecov](https://codecov.io/gh/NielsTRS/projet_devops/graph/badge.svg?token=U8NJXINQAQ)](https://codecov.io/gh/NielsTRS/projet_devops)

## GitHub Pages 
Le site associé au projet : https://nielstrs.github.io/projet_devops/

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
  - Permet de lancer des tests unitaires

- Jacoco :
  - Générer un rapport de couverture de code

- Codecov :
    - Interprete les résultats du test de couverture de Jacoco
    - Récupération du badge de la couverture de code sur le README du dépot GitHub
  
- Docker : 
    - Création d'une image docker contenant toutes les dépendances nécessaires ainsi qu'une démo lors du lancement

## Workflow git
Au début du projet, nous avons adopté la stratégie de créer une branche main pour la version stable du code.
Pour le développement, chaque fonctionnalité disposait de sa propre branche featureX/main, à laquelle étaient rattachées des sous-branches individuelles pour chaque membre du groupe, par exemple :
- main
- feature1/main
- feature1/Prénom

...
- featureN/main
- featureN/Prénom

Cette organisation permettait à chacun de travailler en parallèle sur une fonctionnalité spécifique sans interférer avec le travail des autres.
Une fois la base du code mise en place, nous avons fait évoluer notre stratégie : chaque fonctionnalité est désormais développée dans une unique branche featureX, sans sous-branche par personne. 
Cela réduit la complexité du suivi des modifications.

La branche principale (main) est protégée en écriture, il faut donc faire des PR (Pull Request) afin de merge les nouvelles fonctionnalités à la branche principale.

Pour lancer les tests automatiquement, nous utilisons les GitHub Actions : 
- build : permet vérifier que les tests unitaires passent
- deploy : permet de faire du déploiement continue via GitHub Packages
- coverage : permet de faire les tests de coverages Jacoco que Codecov récupèrent
- docker : permet de faire du déploiement continue via une image Docker et de publier dans GitHub Packages

Le workflow "build" est lancé à chaque commit, peu importe la branche.
Les autres se lancent lors d'une Pull Request et à nouveau quand les modifications sont apportées dans la branche principale (main).

Ainsi, si tous les tests lors d'une PR passent, le déploiement se fait automatiquement.
Nous avons donc 2 déloiements, un via Docker, et sous forme de package maven.
Au début du projet, nous avions décidé de la faire quand un tag était créé manuellement dans main.

PS : pour les tests de coverage, il faut avoir un coverage d'au moins 80%

## Docker
Pour le déploiement continu utilisant Docker, nous avons un workflow GitHub qui permet de publier la nouvelle image docker dans GitLab Packages à condition que tous les tests unitaires + coverages passent et que le pourcentage de coverage est supérieur à 80%.

Pour ce faire, nous avons un fichier Dockerfile sur une base de java17-jdk alpine, dans lequel nous copions notre projet.

La commande "mvn install" permet de faire l'installation des dépendances ainsi que de relancer les tests unitaires + coverages.
Si tous les tests sont valides, l'image docker est ensuite créée et publiée.

Pour utiliser notre image, il faut faire la commande suivante : 
```sh
docker pull ghcr.io/nielstrs/projet_devops:main
```

Pour tester notre projet, nous avons une petite démo qui montre les possibilités de notre librairie DataFrame.
```sh
docker run ghcr.io/nielstrs/projet_devops:main
```

## Feedback
Ce projet nous a vraiment permis de mieux comprendre l’intérêt des outils vus en cours. Le fait de devoir les utiliser dans un vrai projet à plusieurs (on était 4 dans notre cas) nous a montré comment ils s’intègrent concrètement dans un workflow de développement.

On a pu voir en pratique comment mettre en place l’intégration continue, gérer les tests, la couverture de code, et automatiser certaines tâches. L’ajout d’outils externes comme Codecov a été un vrai plus pour suivre la qualité du code, surtout combiné avec JaCoCo pour la génération des rapports de couverture.