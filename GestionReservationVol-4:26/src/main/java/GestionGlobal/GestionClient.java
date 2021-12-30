/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionGlobal;

import GestionDomaine.GestionPaiement;
import GestionDomaine.GestionPersonne;
import GestionDomaine.GestionReservation;
import GestionDomaine.GestionVol;
import Modele.Client;
import Modele.Paiement;
import Modele.Passager;
import Modele.PlaceClasse;
import Modele.Reservation;
import Modele.Vol;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author echo
 */
public class GestionClient {
    
    Client connect;
    ArrayList<Reservation> listResCli; //tous les reservations qui est cree par ce client <connect>.
    GestionPersonne gp;
    GestionReservation gr;
    GestionPaiement gpaie;
    GestionVol gv;
    //on n'a pas besoin d'initialiser les attributs
    
    public GestionClient(Client c,GestionPersonne gp,GestionReservation gr,GestionPaiement gpaie,GestionVol gv){
    connect=c;
    this.gp=gp;
    this.gr=gr;
     this.gpaie=gpaie;
     this.gv=gv;
     listResCli=new ArrayList<Reservation>();
}
    
    public void menuClient(){
         System.out.println("1-Afficher des vols à partir d'une destination et d'une date"); 
        System.out.println("2-Créer une reservation");
        System.out.println("3-Associer un passager à une reservation");
        System.out.println("4-Ajouter un vol à une reservation");
        System.out.println("5-Enlever un vol d'une reservation");
        System.out.println("6-Créer un paiment");
        System.out.println("7-Afficher une réservation");
        System.out.println("8-Supprimer une réservation ");
         System.out.println("9-rechercher une réservation");
        int res=Clavier.lireInt();
        switch (res){
            case 1:afficherVols();
            case 2:creerReservation();
            case 3:assocPassagers();
            case 4:ajoutVol();    
            case 5:enleverVol();
            case 6:creerPaiement();
            case 7:afficherReservation();
            case 8:supReservation();
            case 9:rechercherRes();
     
            default:
                System.out.println("Au revoir");
    }
    }
    // trouver les vols correspondants dans le tableau selon les conditions/les infos (ville depart, et la date est dans l'intervalle)
    public void afficherVols(){
       Vol v;
        PlaceClasse p;
        System.out.println(" ====Rechercher des vols d'une periode====");
        System.out.println(" Indique une date de debut:");
        System.out.println(" Annee");
        int ad=Clavier.lireInt();
        System.out.println(" Mois");
        int md=Clavier.lireInt();
        System.out.println(" Jours");
        int jd=Clavier.lireInt();
        Date date1=new Date(ad-1900,md-1,jd);
        System.out.println(" Indique une date de fin:");
        System.out.println(" Annee");
        int af=Clavier.lireInt();
        System.out.println(" Mois");
        int mf=Clavier.lireInt();
        System.out.println(" Jours");
        int jf=Clavier.lireInt();
        Date date2=new Date(af-1900,mf-1,jf);
        
        System.out.println(" La destination de votre prochaine voyage:");
        String d=Clavier.lireString();
        ArrayList<Vol> vols=gv.afficherVols(date1, date2, d);
        
        //Afficher la table de vol:
        for(int i=0;i<vols.size();i++){
            v=vols.get(i);
             System.out.println("Numéro de Vol:"+v.getNumVol());
            System.out.println("Ville de Depart:"+v.getVilleDepart());
            System.out.println("Ville d'arrivé:"+v.getVilleArrivee());
            System.out.println("Date de depart:"+v.getDateDepart().toString());
            System.out.println("Date d'arrivé:"+v.getDateArrivee().toString());
         
               //Sauf erreur de notre part, il y a 3 Classes qui se trouvent pour un vol.
                ArrayList<PlaceClasse> listP=gv.rechercherClasses(v);
                   for(int m=0;m<listP.size();m++){
                   
                        System.out.println("La classe de siège"+listP.get(m).getClassVol());
                        System.out.println("le prix de class "+listP.get(m).getPrix());
                        System.out.println("le nombre de place"+listP.get(m).getNbPlace());
                    }
        }
    }
    
    /*
    
    */
      public void creerReservation(){
          Reservation r=null;
          Vol vol;
          PlaceClasse classe;
          int v;//vol
          int c;//classe
       //Saisir le nombre de place
        System.out.println("Le nombre de passager:");
        int nbrPassager=Clavier.lireInt();
        
        
      //saisir un vol avec une classes correspondante:
          System.out.println("Saisir un vol:");
          v=Clavier.lireInt(); //Numero de vol
          vol=gv.rechercherVol(v); // Trouver le vol d'un numero donne
          
          //Si nous ne trouvons pas cet vol, il faut redemander le numero de vol
           while (vol == null) {
            System.out.println("Désolé,le vol n'existe pas, veuillez vous saisir un autre vol");
              System.out.println("Saisir un vol:");
              v=Clavier.lireInt(); //Numero de vol
              vol=gv.rechercherVol(v); // Trouver le vol d'un numero donn
        }
           
          //Ensuite, on saisit la classe et verifie s'il y a des places disponibles.
          System.out.println("Saisir une classe :1-économique, 2-affaire, 3-première"); 
          c=Clavier.lireInt();
          classe=gv.rechercherClasse(vol, c); // Touver les informations sur la classe
          //verifie s'il y a des places disponibles
         while (classe==null||classe.getNbPlace()<nbrPassager){  
            System.out.println("Désolé,Il n'y a plus de place disponible,veuillez vous saisir une autre clasee");
            System.out.println("Saisir une classe :1-économique, 2-affaire, 3-première"); 
            c=Clavier.lireInt();
          classe=gv.rechercherClasse(vol, c); 
        }
         //Calculer le montant de reservation: le prix de classe*nbrPassager
        double montant=classe.getPrix()*nbrPassager;
        //La creation d'une reservation
        Reservation re=gr.creerReservation(connect, vol, classe, nbrPassager,montant);
     
       if(re!=null){
           listResCli.add(r);
           System.out.println("La réservation a été crée.");
       }
       else{
            System.out.println("La réservation n'a pas été crée car il deja existe");
       }
     
    }
      public Reservation rechercherRes(){
         Reservation recherche=null;
          System.out.println("Le numero de reservation");
        int num=Clavier.lireInt();
         
    for(int i=0;i<listResCli.size();i++){
      if(listResCli.get(i).getNumReservation()==num){
        recherche=listResCli.get(i);
      }
    }
    return recherche;  
      }
     
      //comme nous savons le nombre de passager, il faut passer une boucle pour associer les passagers à une reservation( ajouter les passagers dans la classe RESERVATION=>ARRAYLIST<Passager>)
      public void assocPassagers(){
         Reservation res;
         Passager passager;
         boolean bo;
        
        res =rechercherRes(); //rechercher cette reservation d'un numero donne
        if(res!=null){
             //le nombre de passagers=> passer une boucle 
         for(int i=1;i<res.getNbrPassager();i++){
            //Completer les infos sur un passager:
        System.out.println("Nom de personne:");
        String n = Clavier.lireString();
        System.out.println("Prénom de personne:");
        String p = Clavier.lireString();
        System.out.println("Numéro de portable:");
        String np = Clavier.lireString();
        System.out.println("L'adresse Mail:");
        String am = Clavier.lireString();
        System.out.println("Numéro passeport:");
        String num = Clavier.lireString();
        passager=gp.creerPassager(n, p, np, am, num);
        bo=gr.assocPassager(res, passager);
        if(bo==true){
            System.out.println("L'ajout d'un passager est effectué");
        }
        else{
            System.out.println("Le passager existe deja");
        }
        }   
        }
       
        
    
    }
      //Jouter un vol dans la réservation=: Rechercher une Reservation=> Rechercher un vol =>associer le vol a la Reservation
        public void ajoutVol(){
            Reservation res; 
            Vol vol;
            PlaceClasse classe;
         //Rechercher une Reservation d'un numero donne
           res =rechercherRes();
        
         //saisir un vol 
          System.out.println("Saisir un numero de vol:");
          int v=Clavier.lireInt(); //Numero de vol
          vol=gv.rechercherVol(v); // Trouver le vol d'un numero donne
          
           //Si nous ne trouvons pas cet vol, il faut redemander le numero de vol
           while (vol == null) {
            System.out.println("Désolé,le vol n'existe pas, veuillez vous saisir un autre vol");
              System.out.println("Saisir un numero de vol:");
              v=Clavier.lireInt(); //Numero de vol
              vol=gv.rechercherVol(v); // Trouver le vol d'un numero donn
        }
          
          //Ensuite, on saisit la classe et verifie s'il y a des places disponibles.
          System.out.println("Saisir une classe :1-économique, 2-affaire, 3-première"); 
          int c=Clavier.lireInt();
          classe=gv.rechercherClasse(vol, c); // Touver les informations sur la classe
          //verifie s'il y a des places disponibles
         while (classe==null||classe.getNbPlace()<res.getNbrPassager()){  
            System.out.println("Désolé,Il n'y a plus de place disponible,veuillez vous saisir une autre clasee");
            System.out.println("Saisir une classe :1-économique, 2-affaire, 3-première"); 
            c=Clavier.lireInt();
          classe=gv.rechercherClasse(vol, c); 
        }
          
          boolean bo=gr.ajoutVol(vol, classe, res);
        if(bo==true){
            System.out.println("L'ajout d'un vol est effectué");
        }
        
        
    }
        //enlever un vol dans la réservation=: Rechercher une Reservation=> Rechercher un vol dans la classe Reservation=>associer le vol a la Reservation
          public void enleverVol(){
          Reservation res; 
            Vol vol;
            PlaceClasse classe;
         //Rechercher une Reservation
        res =rechercherRes();
        //saisir un vol avec la classe corresponsante
        System.out.println("Saisir un numero de vol:");
          int v=Clavier.lireInt(); //Numero de vol
          vol=gv.rechercherVol(v); // Trouver le vol d'un numero donne
          System.out.println("Verifier la classe de vol:"); 
          int c=Clavier.lireInt();
          classe=gv.rechercherClasse(vol, c); // Touver les informations sur la classe
          boolean bo=gr.enleverVol(res, classe, vol);
           if(bo==true){
            System.out.println("La supression d'un vol est effectué");
        }
    }
          //Rechercher une reservation =>Afficher le montant a payer =>creer une reservation
            public boolean creerPaiement(){
                boolean p;
               Reservation res; 
                double montantReste;//Le montant que le client doit payer( soit 30% de la totalite, soit 70%)
         //Rechercher une Reservation
           res =rechercherRes();  
          //Afficher le montant payé par le client
          double montantPaye=gpaie.calculerMontant(res);
          if(montantPaye==0){ //c'est le premier paiement(30% de montant) pour valider la reservation
              montantReste=res.getMontantRes()*0.3;
             System.out.println("Le montant que vous devez payer:"+montantReste);  
          }
          else{ // c'est le deuxieme paiement
             montantReste=res.getMontantRes()-montantPaye;
          System.out.println("Vous avez déjà payé"+montantPaye+",Il reste"+montantReste); 
          }
          
          //Creer un paiement:
          p=gpaie.creerPaiement(montantReste,res);
           return p;
    }
            
            /*Rechercher une réservation et l’afficher, les informations des différents vols doivent être affichées 
                    ainsi que les informations de la réservation dont le montant à payer et le montant de l’acompte 
                    doivent être affichés*/
               public void afficherReservation(){
                   //Rechercher une réservation
                  Reservation r =rechercherRes();
                  //afficher les informations des différents vols
    System.out.println("====les informations du vol principal====");
    System.out.println("Numero de vol:"+r.getVol().getNumVol());
    System.out.println("Ville de départ:"+r.getVol().getVilleDepart());
    System.out.println("Ville d'arrivée:"+r.getVol().getVilleArrivee());
    System.out.println("Date de départ:"+r.getVol().getDateDepart());
    System.out.println("Date d'arrivée:"+r.getVol().getDateArrivee());
    System.out.println("Statut de Vol:"+r.getVol().isStatutVol());
    
    // Rechercher la liste des vols
    ArrayList<Vol> v=r.getListeVolsRes();
    System.out.println("====les informations des vols====");
     for(int m=0;m<v.size();m++){
        System.out.println("Numero de vol:"+v.get(m).getNumVol());
        System.out.println("Ville de départ:"+v.get(m).getVilleDepart());
        System.out.println("Ville d'arrivée:"+v.get(m).getVilleArrivee());
        System.out.println("Date de départ:"+v.get(m).getDateDepart());
        System.out.println("Date d'arrivée:"+v.get(m).getDateArrivee());
        System.out.println("Statut de Vol:"+v.get(m).isStatutVol());
    }
     
     //le montant à payer 
       System.out.println("====Les informations de paiement ====");
     double montantReste;
     double montantPaye=gpaie.calculerMontant(r);
          if(montantPaye==0){ //c'est le premier paiement(30% de montant) pour valider la reservation
              montantReste=r.getMontantRes()*0.3;
             System.out.println("Le montant que vous devez payer:"+montantReste);  
          }
          else{ // c'est le deuxieme paiement
             montantReste=gpaie.calculerMonReste(r);
          System.out.println("Vous avez déjà payé"+montantPaye+",Il reste"+montantReste); 
          }
    }
               
               //Saisir une reservation => appeler la methode "supReservation" dans la classe "GestionReservation" pour la supprimer
                      public void supReservation(){
                         Reservation r =rechercherRes();
                         if(r!=null){
           System.out.println("vous êtes sur d'annuler cette réservation ? ");
      String res=Clavier.lireString();
           if(res.equalsIgnoreCase("o")){
               boolean bo=gr.supReservation(r);
                 if(bo==true){
            System.out.println("La supression de la reservation est effectué");
        }
           }
           }
        
    }
 
}
