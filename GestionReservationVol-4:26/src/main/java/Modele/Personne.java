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
public class Personne {
   private int id;  // je trouve ca sert à rien 
    private String nom;
    private String prenom;
    private String numPhone;
    private String adresseMail;
    private static int nb;

    public Personne(String nom,String prenom,String numPhone,String adresseMail){
        nb++;
        id=nb;
        this.nom=nom;
        this.prenom=prenom;
        this.numPhone=numPhone;
        this.adresseMail=adresseMail;
        

    }
    public Personne(){

    }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public void setNumPhone(String numPhone) {
        this.numPhone = numPhone;
    }

    public String getNumPhone() {
        return numPhone;
    } 
}
