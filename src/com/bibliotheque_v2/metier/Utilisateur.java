package com.bibliotheque_v2.metier;

public abstract class Utilisateur {
	
	 	private int id;
	    private String name;
	    private String email;
	    private String type;

	    public Utilisateur( String name, String email, String type) {
	        this.name = name;
	        this.email = email;
	        this.type = type;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getType() {
	        return type;
	    }
	    
	    public abstract void emprunterDocument(Document document);

	    public abstract void retournerDocument(Document document);

	    public abstract void reserverDocument(Document document);

	    public abstract void annulerReservation(Document document);
	    
	    public abstract boolean peutEmprunter(Document document);

	    public abstract boolean peutReserver(Document document);


}
