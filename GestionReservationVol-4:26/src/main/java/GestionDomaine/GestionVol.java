/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDomaine;

import Modele.PlaceClasse;
import Modele.Reservation;
import Modele.Vol;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author echo
 */
public class GestionVol {
     private ArrayList<Vol> listVols;
    private ArrayList<PlaceClasse> listClasse;

    public GestionVol(){
        listVols=new ArrayList<>();
        listClasse=new ArrayList<>();
    }

    public ArrayList<Vol> getListVols() {
        return listVols;
    }

    public void setListVols(ArrayList<Vol> listVols) {
        this.listVols = listVols;
    }

    public ArrayList<PlaceClasse> getListClasse() {
        return listClasse;
    }

    public void setListClasse(ArrayList<PlaceClasse> listClasse) {
        this.listClasse = listClasse;
    }

 
   //1-Creer un vol
    public Vol creerVol(int n, String vd, String va, Date dd, Date da,boolean s, String nc){

        Vol vol=new Vol(n,vd,va,dd,da,s,nc);
        if(!listVols.contains(vol)){
           listVols.add(vol);
        
        }
        

        return vol;
    }
    
    /*2-ajouter les informations sur classe pour un vol =>Ce processus doit être effectué 3 fois pour définir chaque classe.
    String classe=>1-La Première classe;2-La classe affaire ;3-La class économique
    nbrPlace =>Nombre de Place

    */
    public void ajouterInfoPlace(Vol r,int classe, int nbr,double prix) {
  
        PlaceClasse p = new PlaceClasse(nbr, prix,r,classe);
            listClasse.add(p);
       
    }

    //Rechercher le vol d'numero de vol donné
    public Vol rechercherVol(int n){
        Vol recherche=null;
        
        if(!listVols.isEmpty()){
            for(int i=0;i<listVols.size();i++){
                if(listVols.get(i).getNumVol()==n){
                    recherche=listVols.get(i);
                }
            }
        }
        return recherche;
    }
    
//Saisir une Classe dans le vol saisi
    public PlaceClasse rechercherClasse(Vol vol,int classe){
        PlaceClasse recherche=null;
        
        for(int i=0;i<listClasse.size();i++){
            //Vérifier le numéro de vol et la classe de vol
            if((listClasse.get(i).getVol()==vol)&&(listClasse.get(i).getClassVol()==classe)){
                recherche=listClasse.get(i);
            }
        }
        return recherche;
    }
    
    
    
         //rechercher la classe d'un numero de vol donne
        public ArrayList<PlaceClasse> rechercherClasses(Vol vol){
        ArrayList<PlaceClasse> recherche=null;
        PlaceClasse p=null;
        
        for(int i=0;i<listClasse.size();i++){
            //Vérifier le numéro de vol et la classe de vol
            if((listClasse.get(i).getVol()==vol)){
                p=listClasse.get(i);
                recherche.add(p);
            }
        }
        return recherche;
    }
        
          //rechercher la classe d'un numero de vol donne
        public ArrayList<PlaceClasse> rechercherListP(int vol){
        ArrayList<PlaceClasse> recherche=null;
        PlaceClasse p=null;
        
        for(int i=0;i<listClasse.size();i++){
            //Vérifier le numéro de vol et la classe de vol
            if((listClasse.get(i).getVol().getNumVol()==vol)){
                p=listClasse.get(i);
                recherche.add(p);
            }
        }
        return recherche;
    }  

    /*Afficher des vols à partir d'une destination et d'une date:
    Indique une date de debut:dateD
    Indique une date de fin: dateF
    La destination de votre prochaine voyage:String d
    
    */
    
    public ArrayList<Vol> afficherVols(Date dateD,Date dateF,String d){
        Vol vol;
        PlaceClasse p;
       ArrayList<Vol> vols = null;
        // trouver les vols correspondants dans le tableau selon les conditions(ville depart, et la date est dans l'intervalle)
       
        for(int i=0;i<listVols.size();i++){
            vol=listVols.get(i);
            if((vol.getVilleArrivee().equalsIgnoreCase(d))&&(vol.getDateDepart().compareTo(dateD)>=0)&&(vol.getDateDepart().compareTo(dateF)<=0)){
             vols.add(vol);

            }
            
        
    }
        return vols;
    }
    
    //Rechercher les vols d'une destination donnee
    public ArrayList<Vol> afficherVolsDes(String d){
        Vol vol;
        PlaceClasse p;
       ArrayList<Vol> vols = null;
        // trouver les vols correspondants
       
        for(int i=0;i<listVols.size();i++){
            vol=listVols.get(i);
            if(vol.getVilleArrivee().equalsIgnoreCase(d)){
             vols.add(vol);

            }
            
        
    }
        return vols;
    }
    
//le prix renouvelé:double prix
    public void modifierPrix(Vol v,int classe,double prix){
      
        PlaceClasse p;
        p=rechercherClasse(v,classe);
        p.setPrix(prix);
    }

    //Saisir le vol à supprimer -> Statue de vol devient False -> nombre de place=0
    public boolean supprimerVol(Vol vol){
        boolean bo=false;
        
        PlaceClasse p;
        vol.setStatutVol(false);

       //modifier le prix(=0) et le nombre de place(=0)
        for(int i=0;i<listClasse.size();i++){
            if(listClasse.get(i).getVol()==vol){
                p=listClasse.get(i);
                p.setNbPlace(0);
                bo=true;
            }
        }
        
return bo;
    }
   
}
