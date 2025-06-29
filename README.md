# 📊 Industrial Incident Analyzer - Apache Spark SQL

Ce projet utilise **Apache Spark SQL avec Java** pour analyser les incidents industriels à partir de données CSV. L'application traite et analyse les incidents par service et identifie les années avec le plus grand nombre d'incidents.

---

## 🎯 Objectifs du Projet

### Analyse des Incidents Industriels

L'application Spark développée permet d'analyser un fichier CSV `incidents.csv` contenant les incidents d'une entreprise industrielle pour :

1. **Calculer et afficher le nombre d'incidents par service**
2. **Identifier les deux années avec le plus grand nombre d'incidents**

### Structure des Données
```csv
Id,titre,description,service,date
1,Panne serveur principal,Arrêt inattendu du serveur,IT,2023-03-15
2,Fuite canalisation,Rupture de canalisation,Maintenance,2022-06-20
3,Erreur transaction SAP,Échec de traitement,IT,2023-08-09
...
```

---


## 📈 Résultats Attendus

### 1. Analyse par Service
```
=== INCIDENTS BY SERVICE ===
+------------+---------------+
|     service|total_incidents|
+------------+---------------+
|          IT|              8|
| Maintenance|              5|
|    Sécurité|              2|
+------------+---------------+
```

### 2. Années avec Plus d'Incidents
```
=== TOP YEARS WITH MOST INCIDENTS ===
+-------------+---------------+
|incident_year|total_incidents|
+-------------+---------------+
|         2023|              7|
|         2022|              5|
+-------------+---------------+
```
