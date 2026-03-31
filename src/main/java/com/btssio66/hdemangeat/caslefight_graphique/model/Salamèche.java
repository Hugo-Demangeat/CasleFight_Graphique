package com.btssio66.hdemangeat.caslefight_graphique.model;

public class Salamèche extends Personnage {

    public Salamèche(String nom) { super(nom); }

    @Override
    public int frapper() {
        int forceDuCoup = calculerDegats();
        System.out.println(this.nom + " attaque avec flammèche et provoque " + forceDuCoup + " dégâts.");
        return forceDuCoup;
    }

    @Override
    public void sePresenter() {
        System.out.println("Salamèche !!! Sala ! Sala !");
    }
}
