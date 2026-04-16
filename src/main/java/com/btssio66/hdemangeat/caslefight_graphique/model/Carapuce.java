package com.btssio66.hdemangeat.caslefight_graphique.model;

public class Carapuce extends Personnage {

    public Carapuce(String nom) { super(nom); }

    @Override
    public int frapper() {
        int forceDuCoup = calculerDegats();
        System.out.println(this.nom + " attaque avec pistolet à eau et provoque " + forceDuCoup + " dégâts.");
        return forceDuCoup;
    }

    @Override
    public String sePresenter() {
        return "Carapuce !!! Cara ! Cara !";
    }
}
