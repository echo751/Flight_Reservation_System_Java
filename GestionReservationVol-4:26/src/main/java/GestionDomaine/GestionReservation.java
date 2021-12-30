/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDomaine;

import Modele.Client;
import Modele.Passager;
import Modele.PlaceClasse;
import Modele.Reservation;
import Modele.Vol;
import java.util.ArrayList;

/**
 *
 * @author echo
 */
public class GestionReservation {
    
   private ArrayList<Reservation> listReservation;
   //private ArrayList<Paiement> listePaiement;
  
  public GestionReservation() {
    
   //listePaiement = new ArrayList<>();
    listReservation = new ArrayList<>();

  }
  
  public void setListReservation(ArrayList<Reservation> listReservation) {
    this.listReservation = listReservation;
  }
  
  public ArrayList<Reservation> getListReservation() {
    return listReservation;
  }
  
  /*
  public ArrayList<Paiement> getListePaiement() {
    return listePaiement;
  }
  
  public void setListePaiement(ArrayList<Paiement> listePaiement) {
    this.listePaiement = listePaiement;
  }
  */
  


  /*
  1-Creer une réservation
  int n : Nombre de places
  Lors de la création une réservation, il faut tenir compte du nombre de place 
  */
  
  public Reservation creerReservation(Client cl,Vol v, PlaceClasse c,int nbrPlace,double montant) {
   
    
    Reservation reservation=new Reservation(cl,v,c,nbrPlace,montant);
    if(!listReservation.contains(reservation)){
       listReservation.add(reservation);
    //modif ci-dessous: Le nombre de place soustraire le nombre de passager
    int m=c.getNbPlace()-nbrPlace;
    c.setNbPlace(m);
  
    }
 
    return reservation;
  }
  
   /*
  2- Ajouter un Vol dans la réservation:
  s'il retourne FALSE:L'ajout n'est pas effectué
  s'il retourne TRUE:L'ajout est effectué
  */
  public boolean ajoutVol(Vol v,PlaceClasse c,Reservation r){
    boolean bo;
    bo= r.ajoutVol(c,v);
    return bo;
    }
  
  /*
  3-Enlever un Vol dans la réservation:
  s'il retourne FALSE:La suppression n'est pas effectué
  s'il retourne TRUE:La suppression est effectué
  */
   public boolean enleverVol(Reservation r,PlaceClasse c, Vol v){
      boolean bo;
        bo=r.enleverVol(c,v);
      return bo;
      }
   
 /*
  3-associer un passager à une reservation
  s'il retourne FALSE:L'ajout n'est pas effectué
  s'il retourne TRUE:L'ajout est effectué
  */
      public boolean assocPassager(Reservation r,Passager p){
      boolean bo;
        bo=r.ajoutPassager(p);
      
      return bo;
      }
  
   /*
  Rechercher une Reservation d'un Numero de Réservation donné
  */
  public Reservation rechercherReservation(int num){
    Reservation recherche=null;
   
    for(int i=0;i<listReservation.size();i++){
      if(listReservation.get(i).getNumReservation()==num){
        recherche=listReservation.get(i);
      }
    }
    return recherche;
  }
  

public boolean supReservation(Reservation r){
  boolean bo =false;
  if(!(listReservation.contains(r))){
      listReservation.remove(r);
      bo=true;
  }
  return bo;
}

//Supprimer les réservations lorsque l’acompte n’est pas versé dans le package GestionGlobal=>GestionAgent
public boolean supReservations(ArrayList<Reservation> listR){
  boolean bo =false;
  Reservation r;
  for(int i=0;i<listR.size();i++){
      r=listR.get(i);
     listReservation.remove(r);
  }
 bo=true;
  return bo;
}

//Afficher les reservations pour un vol par classe: 
public ArrayList<Reservation> afficherResClasse(PlaceClasse p){
    
 ArrayList<Reservation> listRes=null;
 Reservation r;
 for(int i=0;i<listReservation.size();i++) {
    r= listReservation.get(i);
    if(r.getClasse()==p||r.getListClasse().contains(p)){
        //On verifier les informations sur classe, plutot que vol. puisque VOL est un attribut de la classe <PlaceClasse>)
        listRes.add(r); //Si on le trouver, on l stocke dans le tableau <listRes>
        
    }
 }
     
    return listRes;
}

//Afficher les reservations associees aux vols :
public ArrayList<Reservation> afficherResVols(ArrayList<Vol> vols){
 
 ArrayList<Reservation> listRes=null;
 Reservation r;
 Vol v;
 for(int i=0;i<vols.size();i++){ 
// affectuer tous les valeurs dans le tableau <vols>, ensuite => passer une sous boucle pour trouver les Reservations correspondantes
     v=vols.get(i);
     for(int m=0;i<listReservation.size();i++){
         //trouver les Reservations associes a ce vol (v) 
         if(listReservation.get(i).getVol()==v||listReservation.get(i).getListeVolsRes().contains(v)){
             r=listReservation.get(i);
             listRes.add(r);
         }
     }
 }
 
     
    return listRes;
}






    
}
