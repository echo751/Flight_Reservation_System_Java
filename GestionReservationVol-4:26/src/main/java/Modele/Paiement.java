/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Date;

/**
 *
 * @author echo
 */
public class Paiement {
    private int idPaiement;// automatique et unique
    private Date datePaiement;
    private double montantPaiement;// 30$ -70$
    private Reservation reservation;

    public Paiement(){

    }
    public Paiement(double m,Reservation r){
        idPaiement++;
        datePaiement=new Date();//Date du jour => afficher en temps réel la date de paiement 
        montantPaiement=m;
        reservation=r;
    }


    public Date getDatePaiement() {
        return datePaiement;
    }

    public double getMontantPaiement() {
        return montantPaiement;
    }

    public int getIdPaiement() {
        return idPaiement;
    }


    public void setMontantPaiement(double montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
}
