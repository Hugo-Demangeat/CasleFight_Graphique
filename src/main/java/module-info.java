module com.btssio66.hdemangeat.caslefight_graphique {
    // Modules JavaFX nécessaires
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
 
    // Module JDBC (interface standard — le driver MySQL sera chargé via classpath)
    requires java.sql;
 
    // Permet à FXML d'accéder aux packages
    opens com.btssio66.hdemangeat.caslefight_graphique to javafx.fxml;
    opens com.btssio66.hdemangeat.caslefight_graphique.controller to javafx.fxml;
    opens com.btssio66.hdemangeat.caslefight_graphique.model to javafx.fxml, javafx.base;
 
    // Exports
    exports com.btssio66.hdemangeat.caslefight_graphique;
    exports com.btssio66.hdemangeat.caslefight_graphique.controller;
}