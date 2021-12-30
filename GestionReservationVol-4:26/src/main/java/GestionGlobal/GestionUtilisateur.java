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

/**
 *
 * @author echo
 */
public class GestionUtilisateur {
    //initialiser les classes dans le pakage GestionDomaines
    private GestionPersonne gp;
    private GestionReservation gr;
     private GestionVol gv;
     private GestionPaiement gpaie;
     
    
    public GestionUtilisateur(){
      gp=new GestionPersonne();
      gr=new GestionReservation();
      gv=new GestionVol();
      gpaie=new GestionPaiement();
    }
    //Menu Principal: 
    public void menu(){
         GestionAgent ga;
         GestionClient gc;
         Client c;
         System.out.println("Qui etes-vous?");
         System.out.println("1-Client");
         System.out.println("2-Agent");
         int res=Clavier.lireInt();
         switch(res){
             //connexion au compte => acceder au menuClient
             case 1:
                 c=connexion();
                 if(c!=null){
                      System.out.println("Vous etes connecté");
                     
                   
                 }
                 else{
                   c=creation();
     
                 }
                  gc=new GestionClient(c,gp,gr,gpaie,gv);
                      //MenuClient
                    gc.menuClient();
                 menu();
                   break;
             case 2:
                 ga=new GestionAgent(gp,gr,gv,gpaie);
                 ga.menuAgent();
                 menu();
                   break;
            default:
                System.out.println("Au revoir");
         }
         
         
         
    }
    
    public Client connexion(){
        Client c=null;
           System.out.println("Saisir le login:");
           String l=Clavier.lireString();
              System.out.println("Saisir le mot de passe:");
                   String mdp=Clavier.lireString();
                   c=gp.saisirClient(l,mdp);
      return c;
    }
    //String n, String p,String np,String am,String lc,String mdp
    public Client creation(){
        Client c=null;
         System.out.println("Nom de personne:");
        String n = Clavier.lireString();
        System.out.println("Prénom de personne:");
        String p = Clavier.lireString();
        System.out.println("Numéro de portable:");
        String np = Clavier.lireString();
        System.out.println("L'adresse Mail:");
        String am = Clavier.lireString();
        System.out.println("Saisir le login:");
        String l=Clavier.lireString();
        System.out.println("Saisir le mot de passe:");
        String mdp=Clavier.lireString();
        c=gp.creerClient(n, p, np, am, l, mdp);
        
        return c;
    }
    
   
}
