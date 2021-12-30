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
public class GestionAgent {
    
    GestionPersonne gp;
    GestionReservation gr;
    GestionVol gv;
    GestionPaiement gpaie;
    
    public GestionAgent(GestionPersonne gp,GestionReservation gr,GestionVol gv,GestionPaiement gpaie){
    this.gp=gp;
    this.gr=gr;
    this.gv=gv;
    this.gpaie=gpaie;
    
    }
    
    public void menuAgent(){
       System.out.println("1-Afficher les clients et les passagers d'un numero de réservation donnée");
        System.out.println("2-Nombre de reservation pour un vol par classe");
        System.out.println("3-Montant des reservations pour un vol");//la somme de toutes les classes dans le choix précédent
        System.out.println("4-Montant des reservations pour une destination");
        System.out.println("5-Créer un vol");
        System.out.println("6-Annuler un vol");//Il faut rechercher les classes associees au vol et rechercher tous les clients et les passagers a avertir
        System.out.println("7-Afficher et Supprimer les réservations dont l'acompte n'a pas été payé et dont le delai est dépassé");//une semaine a partir de la reservation
        System.out.println("8-Afficher et Annuler les réservations lorsque la totalité n’est pas payée et dont le delai est dépassé");//annulation laisse la traçe de la reservation pour que le client peut voir le statut son réservation.
        System.out.println("9-Afficher les clients n'ayant payé qu'un acompte de 30%");
        System.out.println("10-Afficher les clients qui n'ont pas encore payé un acompte de 30%");
        int res=Clavier.lireInt();
        switch (res){
            case 1:afficherCliPas();
            case 2:nbrReservation();
            case 3:montantResV();
            case 4:montantResD();
            case 5:creerVol();
            case 6:supprimerVol();
             
            case 7:supReservationA();
            case 8:annulerReservation();
            case 9:afficherCliA();
            case 10:afficherCliT();
                  
            default:
                System.out.println("Au revoir");
    }
    }
    
    //1-Afficher les clients et les passagers d'un numero de réservation donnée
    public void afficherCliPas(){
        Client c;
        Passager p;
        Reservation r;
        ArrayList<Passager> listP;
        
        System.out.println("Quel est votre numéro de réservation ?");
        int res=Clavier.lireInt();
        r=gr.rechercherReservation(res);
        
        if(r!=null){
            c=r.getClient();
             System.out.println("=====Les coordonnees du client=====");
            System.out.println("Id Client:"+c.getId());
            System.out.println("Nome de Client : "+c.getNom());
            System.out.println("Prénom de Clienht : "+c.getPrenom());
            System.out.println("Adresse mail : "+c.getAdresseMail());
            
            //Rechercher les passagers associes à la réservation:
            listP=r.getListePassagers();
            for(int i=0;i<listP.size();i++){
             System.out.println("===Les coordonnees des passagers===="); 
             System.out.println("Id Passager:"+listP.get(i).getId());
            System.out.println("Nom de Passager  : "+listP.get(i).getNom());
            System.out.println("Prénom de Passager : "+listP.get(i).getPrenom());
            System.out.println("Adresse mail : "+listP.get(i).getAdresseMail()); 
           
            }
        }
      
    }
    /*2-Nombre de reservation pour un vol par classe:
     Saisir un vol =>Saisir une classe=>rechercher les Reservations associe a cette place/vol=>calculer le nombre 
    */
    
      public void nbrReservation(){
          PlaceClasse classe;
           ArrayList<Reservation> listRes;
        System.out.println("Quel est le numéro de vol ?");
          int v=Clavier.lireInt();  
          Vol vol=gv.rechercherVol(v);
          //Saisir la list des classe: (il y a 3 classes correspondantes: 1-économique, 2-affaire, 3-première
          System.out.println("Quelle classe vous voulez saisir?- 1-économique, 2-affaire, 3-première");
           int p=Clavier.lireInt(); 
           classe=gv.rechercherClasse(vol, p);//La classe 
           
           //rechercher les Reservations associe a cette place/vol =>Appeler la methode <afficherResClasse> dans la classe <GestionReservation>
           listRes=gr.afficherResClasse(classe);
           //Calculer le nombre de reservation pour un vol par classe => le nombre de valeurs dans le tableau <listRes>
           
          System.out.println("Le nombre de reservation pour la classe du vol:"+listRes.size());
    }
    //3-Montant des reservations pour un vol par classe=>Saisir un vol =>Saisir une classe=> rechercher les Reservations associe a cette place/vol
        public void montantResV(){
           PlaceClasse classe;
           ArrayList<Reservation> listRes;
           Reservation r;
           double somme=0;
            //Saisir un vol
          System.out.println("Quel est le numéro de vol ?");
          int v=Clavier.lireInt();  
          Vol vol=gv.rechercherVol(v);
          //Saisir la list des classe: (il y a 3 classes correspondantes: 1-économique, 2-affaire, 3-première
          System.out.println("Quelle place vous voulez saisir?- 1-économique, 2-affaire, 3-première");
           int p=Clavier.lireInt(); 
           classe=gv.rechercherClasse(vol, p);//La classe 
           //rechercher les Reservations associe a cette place/vol =>Appeler la methode <afficherResClasse> dans la classe <GestionReservation>
           listRes=gr.afficherResClasse(classe);
           //Passer une boucle pour calculer la somme des montants des reservations
           for(int i=0;i<listRes.size();i++){
               r=listRes.get(i);
               somme=somme+r.getMontantRes();
           }
           System.out.println("Le montant des reservations pour cette classe:"+somme);
    }
    
   /*4-Montant des reservations pour une destination:
        Saisir une destination=> Rechercher les vols d'une destination donnee(METHODE <afficherVolsDes> dans la classe <GestionVol>)
        => Rechercher les resservations associees aux vols (METHODE <afficherResVols> dans la classe <GestionReservation>)
        */
        
    public void montantResD(){
        ArrayList<Vol> listVols;
        ArrayList<Reservation> listRes;
        double somme=0;
        double montant;
        System.out.println("La destination:");
          String d=Clavier.lireString();
          
          listVols=gv.afficherVolsDes(d); //Appeler la METHODE <afficherVolsDes> dans la classe <GestionVol> =>Rechercher les vols d'une destination donnee
          listRes=gr.afficherResVols(listVols);//Appeler la METHODE <afficherResVols> dans la classe <GestionReservation> => Rechercher les resservations associees aux vols
          //Calculer la somme des montant des reservations:
          for(int i=0;i<listRes.size();i++){
               montant=listRes.get(i).getMontantRes();
               somme=somme+montant;
              
          }
          
         System.out.println("Le montant des reservations pour la destination que vous avez saisi:"+somme); 
    }

    //Créer un vol
        public void creerVol(){
        System.out.println("Numéro de Vol:");
        int n=Clavier.lireInt();
        System.out.println("Ville de Depart:");
        String vd=Clavier.lireString();
        System.out.println("Ville d'arrivé:");
        String va=Clavier.lireString();
        System.out.println("Année de départ:");
        int ad=Clavier.lireInt();
        System.out.println("Mois de départ:");
        int md=Clavier.lireInt();
        System.out.println("Jour de départ:");
        int jd=Clavier.lireInt();
        Date dateDepart=new Date(ad-1900,md-1,jd);
        System.out.println("Année d'arrivé:");
        int aa=Clavier.lireInt();
        System.out.println("Mois d'arrivé:");
        int ma=Clavier.lireInt();
        System.out.println("Jour d'arrivé:");
        int ja=Clavier.lireInt();
        Date dateArrive=new Date(aa-1900,ma-1,ja);
        System.out.println("Statu d'arrivé:");
        boolean s=Clavier.lireBoolean();
        Vol vol=gv.creerVol(n, vd, va, dateDepart, dateArrive, s, va);
        if(vol!=null){
                 System.out.println("Le vol a été crée.");
       }
       else{
            System.out.println("Le vol n'a pas été crée car il deja existe");
       }
   
    }
    
        /*7-Supprimer un vol: Saisir le vol supprimer =>modifier le statueVol de TURE a FALSE =>Rechercher les classes associees au vol =>modifier le prix et le nombre de place 
          => Afficher tous les Reservations => supprimer le vol dans les reservations 
         =>Avertir les clients et les Passagers
        */
        public void supprimerVol(){
            Vol vol;
            System.out.println("====Saisir le numero de vol à supprimer====");
            int v=Clavier.lireInt();
           //Appeler la methode <rechercherVol> dans la classe <GestionVol> pour Rechercher le vol d'numero de vol donné
            vol=gv.rechercherVol(v);
           //Appeler la methode <supprimerVol> dans la classe <GestionVol>  modifier le statueVol de TURE a FALSE et modifier les informations sur les classes
           boolean bo=gv.supprimerVol(vol);
            if(bo==true){
           System.out.println("La supression a été effectué.");
           System.out.println("====À la suite de la supression,il faut avertir les passagers et les clients suivantes:====");
           afficherCliPas(); 
       }
            
        }
        
        /*7-Supprimer les réservations dont l'acompte n'a pas été payé et dont le delai est dépassé
        On affiche tous les réservations dans la classe <GestionReservation>
        Ensuite On verifie, un par un, si l'acompte des reservations a été payé ou pas ( Appeler la methode <verifierAcompte> dans la classe <GestionPaiement>
       
       
        */
           public void supReservationA(){
               Reservation r;
              boolean bo;
              ArrayList<Reservation> listResSup=null;// la liste des réservations qui doivent etre supprimées
             // Afficher tous les Reservations
              ArrayList<Reservation> listR= gr.getListReservation();
              
             
               System.out.println("====Les numeros des reservations à supprimer===="); 
              //Trouver les reservations dont l'accompte n'est pas regle et dont le delais est depasse
              for(int i=0;i<listR.size();i++){
                r=listR.get(i); 
               //Appeler la methode <verifierAcompte> dans la classe <GestionPaiement> pour verifier l'acompte:
               bo=gpaie.verifierAcompte(r);
               if(bo==false){//accompte(30%) n'a pas été payé dans le delais
                  //Afficher les infos sur les reservations
                   System.out.println("La reservation:"+r.getNumReservation()+"a été supprimé"); 
                    gr.supReservation(r);
                
                }
                
              }
              
    } 
           /*Afficher et Annuler les réservations lorsque la totalité n’est pas payée et dont le delai est dépassé:
            On affiche tous les réservations dans la classe <GestionReservation>
           Ensuite On verifie, un par un, si totalité(100%) a été payé dans le delais( une semaine avant le depart)
           Enfin, on modifie le statut des réservations dont totalité(100%) n'a pas été payé, de TRUE a FALSE
           */
           
       public void annulerReservation(){
             Reservation r;
              boolean bo;
            // Afficher tous les Reservations,si la totalite a été payé ou pas ( Appeler la methode <verifierTotalite> dans la classe <GestionPaiement>
              ArrayList<Reservation> listR= gr.getListReservation();
              System.out.println("====Les numeros des reservations à supprimer===="); 
              //Trouver les reservations dont la totalite(70%) n'est pas regle et dont le delais est depasse
              for(int i=0;i<listR.size();i++){
                   r=listR.get(i); 
                   bo=gpaie.verifierTotalite(r);
                   if(bo==false){//la totalite(70%) n'a pas été payé dans le delais
                  //Afficher les infos sur les reservations
                   System.out.println("La reservation:"+r.getNumReservation()+"a été annulé "); 
                    r.setStatutRes(false);
                
                }
              }
       }
       
       //10-Afficher les clients qui n'ont pas encore payé un acompte de 30%
       
              public void afficherCliA(){
              Reservation r;
              boolean bo;
             double montantPaye;
             // Afficher tous les Reservations
              ArrayList<Reservation> listR= gr.getListReservation();
              
         
               System.out.println("====Les clients n'ayant payé un acompte de 30%===="); 
              //Trouver les reservations dont l'accompte n'est pas regle
              for(int i=0;i<listR.size();i++){
                  r=listR.get(i); 
                  montantPaye=gpaie.calculerMontant(r); 
                  if(montantPaye==0){
                     System.out.println("Le numero de client:"+r.getClient().getId()); 
                        System.out.println("Le nom de client:"+r.getClient().getNom()); 
                           System.out.println("Le prenom de client:"+r.getClient().getPrenom()); 
                              System.out.println("L'adresse mail de client:"+r.getClient().getAdresseMail()); 
                  }
                  
                  
              }
    } 
   //Afficher les clients n'ayant payé qu'un acompte de 30% ( la totalte n'a pas été payé ) 
public void afficherCliT(){
              Reservation r;
              boolean bo;
             double montantPaye;
             // Afficher tous les Reservations
              ArrayList<Reservation> listR= gr.getListReservation();
              
         
               System.out.println("====Les clients n'ayant payé qu'un acompte de 30%===="); 
              //Trouver les reservations dont l'accompte n'est pas regle
              for(int i=0;i<listR.size();i++){
                  r=listR.get(i); 
                  montantPaye=gpaie.calculerMontant(r); 
                  if(montantPaye!=r.getMontantRes()){
                     System.out.println("Le numero de client:"+r.getClient().getId()); 
                        System.out.println("Le nom de client:"+r.getClient().getNom()); 
                           System.out.println("Le prenom de client:"+r.getClient().getPrenom()); 
                              System.out.println("L'adresse mail de client:"+r.getClient().getAdresseMail()); 
                  }
                  
                  
              }
    } 
        
      
       
        
}
