/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author echo
 */

public class PlaceClasse {
    private int nbPlace; //Le nombre de place disponible
    private double prix;
    private Vol vol;
    private int classeVol; // Cet attribut va nous permettre de définir quelle classe (1-économique, 2-affaire, 3-première)

    public PlaceClasse(){

    }
    public PlaceClasse(int nbr, double p,Vol v, int c){
        nbPlace=nbr;
        prix=p;
        vol=v;
        classeVol=c;

    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getNbPlace() throws Error{
        if(nbPlace<0) throw new Error("Le nombre de place doît être positive");
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }


    public void setClassVol(int classVol) {
        this.classeVol = classVol;
    }

    public int getClassVol() {
        return classeVol;
    }
}
