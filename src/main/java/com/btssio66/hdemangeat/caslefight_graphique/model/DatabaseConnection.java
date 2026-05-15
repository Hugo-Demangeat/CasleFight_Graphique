package com.btssio66.hdemangeat.caslefight_graphique.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Connexion directe au serveur MySQL de XAMPP
    //private static final String URL = "jdbc:mysql://localhost:3306/castlefight?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true";
    //private static final String USER = "root"; // ou "hdemangeat" si tu as créé cet utilisateur
    //private static final String PASSWORD = ""; // vide par défaut pour root sous XAMPP
    
    // Connexion directe au serveur MySQL de O2switch
    private static final String URL = "jdbc:mysql://jasmin.o2switch.net:3306/kuda8918_hugofight?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&autoReconnect=true";
    private static final String USER = "kuda8918_hugofightuser";
    private static final String PASSWORD = "!dVE8YIT^e!co*Fc";

    private static DatabaseConnection instance = null;
    private Connection connection = null;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // obligatoire pour certains setups
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL introuvable : " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.err.println("Erreur fermeture connexion : " + e.getMessage());
            }
        }
    }
}
