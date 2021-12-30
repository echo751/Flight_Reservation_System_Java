/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionDomaine;

import Modele.Client;
import Modele.Passager;
import Modele.Personne;
import java.util.ArrayList;

/**
 *
 * @author echo
 */
public class GestionPersonne {
    
    private ArrayList<Client> listeClient;
    private ArrayList<Passager> listePassager;
   
  public GestionPersonne(){
    listeClient=new ArrayList<>();
    listePassager=new ArrayList<>();
  }

  public ArrayList<Client> getListeClient() {
    return listeClient;
  }

  public void setListeClient(ArrayList<Client> listeClient) {
    this.listeClient = listeClient;
  }
  
   public ArrayList<Passager> getListePassager() {
    return listePassager;
  }

  public void setListePassager(ArrayList<Passager> listePassager) {
    this.listePassager = listePassager;
  }



//1-Créer un Client: Nom de personne, Prénom de personne,Numéro de portable,L'adresse Mail,Login, Mot de pass
  public Client creerClient(String n, String p,String np,String am,String lc,String mdp) {
    
    Client client =new Client(n,p,np,am,lc,mdp);
    listeClient.add(client);
   
    return client;

  }
  //1-Créer un Passager:
  public Passager creerPassager(String n,String p,String np,String am,String num){
    
    Passager passager=new Passager(n,p,np,am,num);
    listePassager.add(passager);
   
    return passager;
  }


//Rechercher un Client d'un login donné et d'un mdp 
  public Client saisirClient(String login,String mdp) {
    Client recherche = null;
   
    for (int i = 0; i < listeClient.size(); i++) {
      
      if (listeClient.get(i).getLogin().equalsIgnoreCase(login)&&listeClient.get(i).getMdpClient().equals(mdp)) { // Si nous trouvons login de client
        recherche =listeClient.get(i);
      }

    }

    return recherche;
  }
  
//Rechercher un Passager du numero de passport donnee
  public Passager saisirPassager(String numP) {
    Passager recherche = null;
  
    for (int i = 0; i < listePassager.size(); i++) {
 
      if (listePassager.get(i).getNumPassport().equals(numP)) {
        recherche =listePassager.get(i);
      }

    }
    return recherche;
  }


/*6-Modifier le mot de passe pour un client:
  Le mot de passe actuel:mdp1
  Nouveau mot de passe:mdp2
  SI retourne False: Le mot de passe actuel est incorrect
  */
  
  public boolean modiferMdp(Client c,String mdp1,String mdp2){
   boolean bo=false;
    if (c.getMdpClient().equals(mdp1)) {
      c.modifierMdp(mdp2);
      bo=true;
    } 

  return bo;
}
}

