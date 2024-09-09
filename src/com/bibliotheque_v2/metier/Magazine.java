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
