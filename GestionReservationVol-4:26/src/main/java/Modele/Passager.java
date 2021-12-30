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
public class Passager extends Personne {
   private String numPassport;

    public Passager(String n, String p, String np, String a, String num){
       super(n,p,np,a);
      numPassport=num;
    }
    public Passager(String n){
        numPassport=n;
    }

    public String getNumPassport() {
        return numPassport;
    }

    public void setNumPassport(String numPassport) {
        this.numPassport = numPassport;
    } 
}
