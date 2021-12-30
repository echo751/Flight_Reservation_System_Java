/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDomaine;


import Modele.Paiement;
import Modele.Reservation;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author echo
 */
public class GestionPaiement {
       private ArrayList<Paiement> listePaiement;
       
       public GestionPaiement(){
           listePaiement = new ArrayList<>();
       }
       
    public ArrayList<Paiement> getListePaiement() {
    return listePaiement;
  }
  
    public void setListePaiement(ArrayList<Paiement> listePaiement) {
    this.listePaiement = listePaiement;
  }
    /*1-Effectuer un paiement pour une Reservation: 
       
    */
   public boolean creerPaiement(double montant,Reservation r){
    boolean bo=false;
      Paiement p=new Paiement(montant,r);
      if(!listePaiement.contains(p)){ //Verifier s'il'existe pas dans la table de Paiement
          listePaiement.add(p);
          bo=true;
      }
     return bo;
}
   //2-calculer le montant deja payé par le client pour valider le paiement
    public double calculerMontant(Reservation r){
      Paiement p;
     
      double somme=0;
      //
      for(int i=0;i<listePaiement.size();i++){
        if(listePaiement.get(i).getReservation()==r){
          p=listePaiement.get(i);
          somme=somme+p.getMontantPaiement();  
        }
      }
      return somme;
    }
    
    //calculer le montant à payer 
    public double calculerMonReste(Reservation r){
    double montantPaye=calculerMontant(r);
    double montantReste=r.getMontantRes()-montantPaye;
      return montantReste;
    }
    
    
    /* 1-Afficher le nombre de paiement pour relancer les clients/Supprimer les réservations/valider le reservation
    le nombre de paiement=1: le premier paiement permet de valider la réservation
    le nombre de paiement=2: le deuxième paiement permet de finaliser le règlement et la réservation
    */
    public int afficherNbr(Reservation r){
      int nbrPaiement=0;

      for(int i=0;i<listePaiement.size();i++){
        if(listePaiement.get(i).getReservation()==r){
          nbrPaiement++;
        }
      }
      return nbrPaiement;
    }
    
  
   
     //Verifier si accompte(30%) n'a pas été payé dans le delais( une semaine apres la reservation)
    
    public boolean verifierAcompte(Reservation r){
        boolean bo=true;
        double montantPaye=calculerMontant(r); //le montant payé =0(C'est a dire, l'acompte de reservation n'a pas été payé)
         //Il faut tenir compte du montant payé, ainsi que le delai
        Date dateJ=new Date(); //Date de jour
        Date dateRes=r.getDateReservation();
        long duree=(dateJ.getTime()-dateRes.getTime())/(24*60*60*1000); //Calculer la duree entre la date de Reservation et la date du jour
        if(montantPaye==0&&duree<7){
            bo=false; //FALSE=> accompte(30%) n'a pas été payé => cette reservation doit etre supprime
        }
        return bo;
    }
    
    //Verifier si totalité(100%) a été payé dans le delais( une semaine avant le depart)
    public boolean verifierTotalite(Reservation r){
          boolean bo=true;
          double montantPaye=calculerMontant(r); //Si le totalité a été payé, le montant payé doit etre égale le montant de réservation
          
          //Il faut tenir compte du montant payé, ainsi que le delai
         Date dateJ=new Date(); //Date de jour
        Date dateDepart=r.getVol().getDateArrivee();//La date de depart
        long duree=(dateDepart.getTime()-dateJ.getTime())/(24*60*60*1000); //Calculer la duree entre la date de Depart et la date du jour
        if(montantPaye==0&&duree<7){
            bo=false; //FALSE=> la totalite(70%) n'a pas été payé => cette reservation doit etre annule(nous ne pouvons pas la supprime, mais nous pouvons modifier le statue de reservation) 
        }
        return bo;
    }
    
  
    
}
