package com.bibliotheque_v2.metier;

import java.time.LocalDate;

public class JournalScientifique extends Document{

    private String domaineRecherche;

    public JournalScientifique(String titre, String auteur, LocalDate datePub, int nbrPages, String type, int bibliothequeId, Integer borrowerId, Integer reserverId, String domaineRecherche) {
        super(titre, auteur, datePub, nbrPages, type, bibliothequeId, borrowerId, reserverId);
        this.domaineRecherche = domaineRecherche;
    }

    public String getDomaineRecherche() {
        return domaineRecherche;
    }

    public void setDomaineRecherche(String domaineRecherche) {
        this.domaineRecherche = domaineRecherche;
    }
    @Override
    public void emprunter(Utilisateur utilisateur) {
      
    }
    
    @Override
    public void retourner() {
       
    }
    
    @Override
    public void reserver(Utilisateur utilisateur) {
      
    }

    @Override
    public void annulerReservation() {
       
    }

}
