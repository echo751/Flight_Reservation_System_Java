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
public class Reservation {
    private int numReservation; 
    private Client client;
    private int nbrPassager; //* combien des passengers
    private double montantRes;// nbrPassager*prixClasse
    private Date dateReservation;
    private Vol vol;
    private PlaceClasse classe;
    private boolean statutRes;// TRUE:
    private ArrayList<Passager> listePassagers;// on pourra ajouter plusieurs passengers dans le tableaux, ou on peut le laisser vide
    private ArrayList<Vol> listeVolsRes;
    private ArrayList<PlaceClasse> listClasse;
    // un passager correspond à un vol et à une place, une classe réservée doivent etre associé à un vol 

    public Reservation(){

    }
    public Reservation( Client cl,Vol v,PlaceClasse c,int n,double montantRes){
        numReservation++;
        client=cl;
        vol=v;
        classe=c;
        nbrPassager=n; //le client réserve un ou plusieurs vols pour un passager
        this.montantRes = montantRes;
        dateReservation=new Date(); //date de jour
        listePassagers =new ArrayList<>();
        listeVolsRes =new ArrayList<>();
       listClasse =new ArrayList<>();
        listClasse.add(c);
        listeVolsRes.add(v);
        statutRes=true;
    }
    //set(), get()

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public PlaceClasse getClasse() {
        return classe;
    }

    public void setClasse(PlaceClasse classe) {
        this.classe = classe;
    }
   
    public int getNumReservation() {
        return numReservation;
    }

    public void setNumReservation(int numReservation) {
        this.numReservation = numReservation;
    }

    public double getMontantRes() {
        return montantRes;
    }

    public void setMontantRes(double montantRes) {
        this.montantRes = montantRes;

    }


    public Date getDateReservation() {
        return dateReservation;
    }

    public int getNbrPassager() {
        return nbrPassager;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setNbrPassager(int nbrPassager) {
        this.nbrPassager = nbrPassager;
    }

   

    public Client getClient() {
        return client;
    }

    public boolean isStatutRes() {
        return statutRes;
    }

    public void setStatutRes(boolean statutRes) {
        this.statutRes = statutRes;
    }

    

    public ArrayList<Passager> getListePassagers() {
        return listePassagers;
    }

    public ArrayList<PlaceClasse> getListClasse() {
        return listClasse;
    }

    public ArrayList<Vol> getListeVolsRes() {
        return listeVolsRes;
    }

    public void setListClasse(ArrayList<PlaceClasse> listClasse) {
        this.listClasse = listClasse;
    }

    public void setListeVolsRes(ArrayList<Vol> listeVolsRes) {
        this.listeVolsRes = listeVolsRes;
    }

    public void setListePassagers(ArrayList<Passager> listePassagers) {
        this.listePassagers = listePassagers;
    }

   

    //on fait une méthode qui permet d'ajouter un passenger dans le tableau listePassenger
    //Il rerourne boolean pour voir si l'ajout a été éffectué
 public boolean ajoutPassager(Passager p){
        boolean bo=false;
        if(!listePassagers.contains(p)){
            if (listePassagers.size()<nbrPassager){
            listePassagers.add(p);
           // il faut vérifier que le nombre de passagers n'est pas supérieur au nombre de places réservées avant de l'ajouter
            bo=true;}
        }
    return bo;
   }


/*
 ETAPE: Verification du nombre de place => Ajouter un vol => Modifier le nombre de places de la classe et le prix
  Il faut d'abord vérifier s'il reste suffisament de places dans la classe de ce vol (nbplaces de la réservation)
  Dès qu'il y a des places disponibles,Nous ajouter le vol dans la réservation et diminuer le nombre de places de la classe de ce vol du nombe d eplaces contenu dans réservation
 */
    public boolean ajoutVol(PlaceClasse c,Vol v){
    boolean bo=false;
    if(!listeVolsRes.contains(v)){
        //Verification du nombre de place: le nombre de passager doit etre en dessous du nombre de places de la classe 
        if(nbrPassager<c.getNbPlace()){
            listeVolsRes.add(v);
        if (!listClasse.contains(c))
            listClasse.add(c);
        montantRes = montantRes +c.getPrix()*nbrPassager; //le prix de réservation va augmenter
       
        //le nombre de place par classe (pour ce vol) est diminué
        int num=c.getNbPlace()-nbrPassager;

        c.setNbPlace(num);
        }
        
    }
    return bo;
    }
/*
 ETAPE: Supprimer un vol dans la reservation => Modifier le nombre de places de la classe
*/
    public boolean enleverVol(PlaceClasse c, Vol v){
        boolean bo=false;
        if(listeVolsRes.contains(v)){
            listeVolsRes.remove(v);
            listClasse.remove(c);
            montantRes = montantRes -c.getPrix()*nbrPassager;
            //le nombre de place par classe (pour un vol) va augmenter
            int num=c.getNbPlace()+nbrPassager;
            c.setNbPlace(num);
        }
        return bo;
    }

    //Le nombre de place pour les vols va augmenter
    public void supReservation(){
   

        ArrayList<PlaceClasse> p=getListClasse();
        for(int i=0;i<p.size();i++){
            int num= p.get(i).getNbPlace()+nbrPassager;//le nombre de place pour cette classe + le nombre de place réservée
            p.get(i).setNbPlace(num);

        }

    }
    
 

}
