module com.btssio66.hdemangeat.caslefight_graphique {
    // Modules JavaFX nécessaires
    requires javafx.controls;
    requires javafx.fxml;

    // Module JDBC pour la connexion à la base de données
    requires java.sql;

    // Permet à FXML d'accéder aux packages
    opens com.btssio66.hdemangeat.caslefight_graphique to javafx.fxml;
    opens com.btssio66.hdemangeat.caslefight_graphique.controller to javafx.fxml;

    // Exports pour pouvoir accéder aux classes depuis l'extérieur du module
    exports com.btssio66.hdemangeat.caslefight_graphique;
    exports com.btssio66.hdemangeat.caslefight_graphique.controller;
}
