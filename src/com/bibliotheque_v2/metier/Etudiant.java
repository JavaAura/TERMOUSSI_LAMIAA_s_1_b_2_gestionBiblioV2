package com.bibliotheque_v2.metier;

public class Etudiant extends Utilisateur {
	
	  private String numero;

	    public Etudiant( String name, String email,String type, String numero) {
	        super( name, email,type);
	        this.numero = numero;
	    }
	    
	    public String getNumero() {
	        return numero;
	    }

	    public void setNumero(String numero) {
	        this.numero = numero;
	    }
	    
	    @Override
	    public  String toString() {
	    	  StringBuffer sb = new StringBuffer();
	    	    sb.append("Étudiant [Nom=").append(getName())
	    	      .append(", Email=").append(getEmail())
	    	      .append(", Numéro=").append(getNumero())
	    	      .append("]");

	    	    return sb.toString();
	    }

	  
	    @Override
	    public boolean peutEmprunter(Document document) {
	        
	        return !(document instanceof TheseUniversitaire); 
	    }

	    @Override
	    public boolean peutReserver(Document document) {
	        
	        return !(document instanceof TheseUniversitaire); 
	    }
}
