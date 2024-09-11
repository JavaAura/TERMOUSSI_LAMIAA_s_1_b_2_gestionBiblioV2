package com.bibliotheque_v2.metier;

import java.time.LocalDate;

public abstract class Document {
    private int id;
    private String titre;
    private String auteur;
    private LocalDate datePub;
    private int nbrPages;
    private String type;
    private Integer borrowerId;
    private  int bibliothequeId;
    private Integer reserverId; 

    public Document(String titre, String auteur, LocalDate datePub, int nbrPages, String type,int bibliothequeId ,Integer borrowerId,Integer reserverId) {
        this.titre = titre;
        this.auteur = auteur;
        this.datePub = datePub;
        this.nbrPages = nbrPages;
        this.type = type;
        this.bibliothequeId=  bibliothequeId;
        this.borrowerId = borrowerId; 
        this.reserverId = reserverId;
        
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public LocalDate getDatePub() {
        return datePub;
    }

    public int getNbrPages() {
        return nbrPages;
    }

    public String getType() {
        return type;
    }
    
    public int getBibliothequeId() {
        return bibliothequeId;
    }
    
    public Integer getBorrowerId() {
        return borrowerId;
    }

    public Integer getReserverId() {
        return reserverId;
    }
    // Setters
  

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setDatePub(LocalDate datePub) {
        this.datePub = datePub;
    }

    public void setNbrPages(int nbrPages) {
        this.nbrPages = nbrPages;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public void setBibliothequeId(int bibliothequeId) {
        this.bibliothequeId = bibliothequeId;
    }

    public void setReserverId(Integer reserverId) {
        this.reserverId = reserverId;
    }
    
    public abstract void emprunter(Utilisateur utilisateur);
    public abstract void retourner();
    public abstract void reserver(Utilisateur utilisateur);
    public abstract void annulerReservation();

}