package com.bibliotheque_v2.metier;

import java.time.LocalDate;

public class TheseUniversitaire extends Document{

	  private String universite;
	    private String domaine;


	    public TheseUniversitaire(String titre, String auteur, LocalDate datePub, int nbrPages, String type, int bibliothequeId, Integer borrowerId, Integer reserverId, String universite, String domaine) {
	        super(titre, auteur, datePub, nbrPages, type, bibliothequeId, borrowerId, reserverId);
	        this.universite = universite;
	        this.domaine = domaine;
	    }

	    public String getUniversite() {
	        return universite;
	    }

	    public void setUniversite(String universite) {
	        this.universite = universite;
	    }

	    public String getDomaine() {
	        return domaine;
	    }

	    public void setDomaine(String domaine) {
	        this.domaine = domaine;
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
