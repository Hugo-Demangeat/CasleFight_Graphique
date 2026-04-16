package com.btssio66.hdemangeat.caslefight_graphique;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.btssio66.hdemangeat.caslefight_graphique.model.DatabaseConnection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Vérification de la connexion à la base de données
        try (Connection conn = DatabaseConnection.getInstance().getConnection()) {
            System.out.println("Connexion à la base de données réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
            System.err.println("L'application continuera sans sauvegarde des statistiques.");
            // Ne pas quitter, continuer sans DB
        }

        scene = new Scene(loadFXML("primary"), 1000, 700);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    protected static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}