package com.btssio66.hdemangeat.caslefight_graphique.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StatistiquePersonnage {

    private final StringProperty nom;
    private final IntegerProperty victoires;
    private final IntegerProperty defaites;
    private final IntegerProperty totalPointsVie;

    public StatistiquePersonnage(String nom, int victoires, int defaites, int totalPointsVie) {
        this.nom = new SimpleStringProperty(nom);
        this.victoires = new SimpleIntegerProperty(victoires);
        this.defaites = new SimpleIntegerProperty(defaites);
        this.totalPointsVie = new SimpleIntegerProperty(totalPointsVie);
    }

    // Getters pour les valeurs
    public String getNom() { return nom.get(); }
    public int getVictoires() { return victoires.get(); }
    public int getDefaites() { return defaites.get(); }
    public int getTotalPointsVie() { return totalPointsVie.get(); }

    // Getters pour les Property (JavaFX)
    public StringProperty nomProperty() { return nom; }
    public IntegerProperty victoiresProperty() { return victoires; }
    public IntegerProperty defaitesProperty() { return defaites; }
    public IntegerProperty totalPointsVieProperty() { return totalPointsVie; }
}
