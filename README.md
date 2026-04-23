# CastleFight Graphique

Jeu d'affrontement Pokémon développé en JavaFX avec sauvegarde des statistiques de combats dans une base MySQL.

## Description

Cette application graphique permet à deux joueurs de choisir chacun un Pokémon et de lancer un combat en mode tour par tour. Chaque attaque inflige des dégâts aléatoires et le combat se termine lorsque l'un des Pokémon perd tous ses points de vie.

Une fenêtre de statistiques affiche les victoires, défaites et dégâts totaux infligés par chaque Pokémon.

## Fonctionnalités

### Combat Pokémon
- Choix de deux Pokémon parmi : Salamèche, Carapuce, Bulbizarre, Pikachu
- Combat tour par tour avec un seul bouton `Fight`
- Affichage des dégâts infligés et des points de vie restants
- Effets visuels simples pour les attaques
- Le premier attaquant est choisi aléatoirement

### Statistiques
- Enregistrement des résultats de combat dans une base MySQL
- Suivi des victoires et défaites par personnage
- Comptabilisation des dégâts totaux infligés
- Affichage des statistiques dans une table JavaFX

### Résilience
- Si la base de données n'est pas disponible, l'application démarre quand même
- Dans ce cas, les statistiques ne sont pas sauvegardées

## Prérequis

Avant d'installer et lancer l'application, installez :

- **Java 11**
- **Maven**
- **MySQL** (ou MariaDB)
- **JavaFX 13** (le projet utilise la dépendance Maven, donc aucun SDK JavaFX supplémentaire n'est requis si Maven télécharge les librairies)
- **XAMPP** ou un autre serveur MySQL local recommandé pour la base de données

## Installation

### 1. Cloner le dépôt

```bash
git clone https://github.com/Hugo-Demangeat/CasleFight_Graphique.git
cd CasleFight_Graphique
```

### 2. Importer la base de données MySQL

Ouvrez phpMyAdmin ou MySQL Workbench et importez le fichier `castlefight.sql` fourni.

Le projet utilise la base de données `castlefight` et la table `resultats_combats`.

### 3. Configurer la connexion MySQL

Le code utilise les paramètres suivants dans `DatabaseConnection.java` :

```java
private static final String URL = "jdbc:mysql://localhost:3306/castlefight?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true";
private static final String USER = "root";
private static final String PASSWORD = "";
```

Si votre configuration MySQL est différente, modifiez ces valeurs dans `src/main/java/com/btssio66/hdemangeat/caslefight_graphique/model/DatabaseConnection.java`.

### 4. Installer les dépendances Maven

```bash
mvn clean install
```

## Exécution

### Lancer l'application

Utilisez la commande suivante pour démarrer l'application JavaFX :

```bash
mvn javafx:run
```

### Usage

1. Sélectionnez un Pokémon pour le joueur 1
2. Sélectionnez un Pokémon pour le joueur 2
3. Cliquez sur `Fight` pour lancer le combat
4. Répétez pour voir le déroulement du combat dans la zone de texte
5. Ouvrez la fenêtre de statistiques pour consulter les résultats enregistrés

## Structure du projet

```
src/
├── main/
│   ├── java/
│   │   └── com/btssio66/hdemangeat/caslefight_graphique/
│   │       ├── App.java
│   │       ├── controller/
│   │       │   ├── PrimaryController.java
│   │       │   └── ScoresController.java
│   │       └── model/
│   │           ├── Bulbizarre.java
│   │           ├── Carapuce.java
│   │           ├── CombatDAO.java
│   │           ├── DatabaseConnection.java
│   │           ├── Personnage.java
│   │           ├── Pikachu.java
│   │           ├── Salamèche.java
│   │           └── StatistiquePersonnage.java
│   └── resources/
│       ├── com/btssio66/hdemangeat/caslefight_graphique/
│       │   ├── primary.fxml
│   │       │   └── scores.fxml
│       ├── css/
│       └── images/
```

## Technologies utilisées

- **Java 11**
- **JavaFX 13**
- **Maven**
- **MySQL / MariaDB**
- **FXML** pour l'interface graphique

## Auteur

Hugo Demangeat
