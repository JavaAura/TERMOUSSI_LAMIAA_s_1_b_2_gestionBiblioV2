package com.bibliotheque_v2.metier;

import java.time.LocalDate;

public class Magazine extends Document {
	
	  private String numero;

	    public Magazine(String titre, String auteur, LocalDate datePub, int nbrPages, String type, int bibliothequeId, Integer borrowerId, Integer reserverId, String numero) {
	        super(titre, auteur, datePub, nbrPages, type, bibliothequeId, borrowerId, reserverId);
	        this.numero = numero;
	    }
	    
	    public String getNumero() {
	        return numero;
	    }

	    public void setNumero(String numero) {
	        this.numero = numero;
	    }
	    
	    @Override
	    public String toString() {
	        StringBuffer sb = new StringBuffer();
	        sb.append("Magazine [Titre=").append(getTitre())
	          .append(", Auteur=").append(getAuteur())
	          .append(", Date de publication=").append(getDatePub())
	          .append(", Nombre de pages=").append(getNbrPages())
	          .append(", Numéro=").append(getNumero())
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
