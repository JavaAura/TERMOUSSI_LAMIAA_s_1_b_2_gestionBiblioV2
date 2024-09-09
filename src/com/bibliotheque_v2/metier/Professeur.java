package com.bibliotheque_v2.metier;

public class Professeur extends Utilisateur {
	
	  private String departement;

	    public Professeur(String name, String email, String departement) {
	        super(name, email, "professeur");
	        this.departement = departement;
	    }

	    public String getDepartement() {
	        return departement;
	    }

	    public void setDepartement(String departement) {
	        this.departement = departement;
	    }
	    
	    @Override
	    public void emprunterDocument(Document document) {
	     
	    }

	    @Override
	    public void retournerDocument(Document document) {
	      
	    }

	    @Override
	    public void reserverDocument(Document document) {
	        
	    }

	    @Override
	    public void annulerReservation(Document document) {
	       
	    }

	    @Override
	    public boolean peutEmprunter(Document document) {
	        return true;
	    }

	    @Override
	    public boolean peutReserver(Document document) {
	        return true;
	    }
}
