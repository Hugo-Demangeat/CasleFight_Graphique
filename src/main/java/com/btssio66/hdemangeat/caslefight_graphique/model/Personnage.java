package com.btssio66.hdemangeat.caslefight_graphique.model;

import java.util.Random;

public abstract class Personnage {

    protected String nom;
    protected int vie;

    public Personnage(String nom) {
        this.nom = nom;
        this.vie = 100;
    }

    public String getNom() { return nom; }

    public int getVie() { return vie; }

    public void setVie(int vie) { this.vie = vie; }

    protected int calculerDegats() {
        Random rand = new Random();
        return rand.nextInt(30); // dégâts aléatoires 0-29
    }

    // frappe sans argument
    public abstract int frapper();

    public abstract void sePresenter();
}
