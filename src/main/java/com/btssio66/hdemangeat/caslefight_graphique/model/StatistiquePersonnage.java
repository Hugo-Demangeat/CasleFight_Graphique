package com.btssio66.hdemangeat.caslefight_graphique.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StatistiquePersonnage {

    private final StringProperty nom;
    private final IntegerProperty victoires;
    private final IntegerProperty defaites;
    private final DoubleProperty totalDegatsInfliges;
    private final StringProperty ratioVD;

    public StatistiquePersonnage(String nom, int victoires, int defaites, double totalDegatsInfliges, String ratioVD) {
        this.nom = new SimpleStringProperty(nom);
        this.victoires = new SimpleIntegerProperty(victoires);
        this.defaites = new SimpleIntegerProperty(defaites);
        this.totalDegatsInfliges = new SimpleDoubleProperty(totalDegatsInfliges);
        this.ratioVD = new SimpleStringProperty(ratioVD);
    }

    // Getters pour les valeurs
    public String getNom() { return nom.get(); }
    public int getVictoires() { return victoires.get(); }
    public int getDefaites() { return defaites.get(); }
    public double getTotalDegatsInfliges() { return totalDegatsInfliges.get(); }
    public String getRatioVD() { return ratioVD.get(); }

    // Getters pour les Property (JavaFX)
    public StringProperty nomProperty() { return nom; }
    public IntegerProperty victoiresProperty() { return victoires; }
    public IntegerProperty defaitesProperty() { return defaites; }
    public DoubleProperty totalDegatsInfligesProperty() { return totalDegatsInfliges; }
    public StringProperty ratioVDProperty() { return ratioVD; }
}
