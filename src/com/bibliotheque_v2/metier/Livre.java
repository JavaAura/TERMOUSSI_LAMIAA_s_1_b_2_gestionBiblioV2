package com.bibliotheque_v2.metier;

import java.time.LocalDate;

public class Livre extends Document{
	
	 private String isbn;
	   
	    public Livre(String titre, String auteur, LocalDate datePub, int nbrPages, String type, int bibliothequeId, Integer borrowerId, Integer reserverId, String isbn) {
	        super(titre, auteur, datePub, nbrPages, type, bibliothequeId, borrowerId, reserverId);
	        this.isbn = isbn;
	    }
	    public String getIsbn() {
	        return isbn;
	    }

	    public void setIsbn(String isbn) {
	        this.isbn = isbn;
	    }
	    
	    @Override
	    public String toString() {
	        StringBuffer sb = new StringBuffer();
	        sb.append("Livre [Titre=").append(getTitre())
	          .append(", Auteur=").append(getAuteur())
	          .append(", Date de publication=").append(getDatePub())
	          .append(", Nombre de pages=").append(getNbrPages())
	          .append(", ISBN=").append(getIsbn())
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
