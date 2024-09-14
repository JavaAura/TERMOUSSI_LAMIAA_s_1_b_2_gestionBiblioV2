package com.bibliotheque_v2.metier;

public class Bibliotheque {

	  	private int id;
	    private String nom;
	    
	    public Bibliotheque(int id, String nom) {
	        this.nom = nom;
	    }

	    public int getId() {
	        return id;
	    }
	    
	    public String getNom() {
	        return nom;
	    }
}
