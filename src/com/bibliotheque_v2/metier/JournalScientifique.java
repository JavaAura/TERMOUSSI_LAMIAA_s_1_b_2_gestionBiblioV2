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
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Journal scientifique [Titre=").append(getTitre())
        .append(", Auteur=").append(getAuteur())
        .append(", Date de publication=").append(getDatePub())
        .append(", Nombre de pages=").append(getNbrPages())
          .append(", Domaine de recherche=").append(getDomaineRecherche())
          .append(", Bibliothèque ID=").append(getBibliothequeId())
          .append(", Borrower ID=").append(getBorrowerId() != null ? getBorrowerId() : "N/A")
          .append(", Reserver ID=").append(getReserverId() != null ? getReserverId() : "N/A")
          .append("]");


        return sb.toString();
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
