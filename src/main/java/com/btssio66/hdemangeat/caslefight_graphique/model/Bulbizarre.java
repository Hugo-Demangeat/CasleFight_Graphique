package com.btssio66.hdemangeat.caslefight_graphique.model;

public class Bulbizarre extends Personnage {

    public Bulbizarre(String nom) { super(nom); }

    @Override
    public int frapper() {
        int forceDuCoup = calculerDegats();
        System.out.println(this.nom + " attaque avec fouet liane et provoque " + forceDuCoup + " dégâts.");
        return forceDuCoup;
    }

    @Override
    public String sePresenter() {
        return "Bulbizarre !!! Bulbi ! Bulbi !";
    }
}
