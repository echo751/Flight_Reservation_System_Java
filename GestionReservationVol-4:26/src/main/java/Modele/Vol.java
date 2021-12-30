/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author echo
 */
public class Vol {
    private int numVol;
    private String villeDepart;
    private String villeArrivee;
    private Date dateDepart;
    private Date dateArrivee;
    private boolean statutVol; //pour mentionner s'il est annule
    private String nomCompagnie;
    //private ArrayList<PlaceClasse> classeVol; //il concerne les infos sur la classe: Nom de classe, prix, nbrPlace

    public Vol(){

    }
    public Vol(int n, String vd, String va, Date dd, Date da,boolean s, String nc){
        numVol=n;
        villeDepart=vd;
        villeArrivee=va;
        dateDepart=dd;
        dateArrivee=da;
        statutVol=s;
        nomCompagnie=nc;

    }

    public int getNumVol() throws Error {
        return numVol;
    }

    public void setNumVol(int numVol) {
        this.numVol = numVol;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public boolean isStatutVol() {
        return statutVol;
    }

    public void setStatutVol(boolean statutVol) {
        this.statutVol = statutVol;
    }

    public void supVol(){
        setStatutVol(false);
    }
    
    public String getNomCompagnie(){
        return nomCompagnie;
    }

    public void setNomCompagnie(String nomCompagnie) {
        this.nomCompagnie = nomCompagnie;
    }
    
    

    /*
    public ArrayList<PlaceClasse> getClasseVol() {
        return classeVol;
    }

    public void setClasseVol(ArrayList<PlaceClasse> classeVol) {
        this.classeVol = classeVol;
    }
*/
    
    
}
