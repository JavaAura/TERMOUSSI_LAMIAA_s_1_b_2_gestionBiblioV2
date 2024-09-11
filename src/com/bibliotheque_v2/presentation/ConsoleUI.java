package com.bibliotheque_v2.presentation;

import java.util.Scanner;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.bibliotheque_v2.dao.LivreDAO;
import com.bibliotheque_v2.dao.LivreDAOImpl;
import com.bibliotheque_v2.metier.*;

public class ConsoleUI {
	
	 private static final Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        ConsoleUI ui = new ConsoleUI();
	        ui.showMenu();
	    }
	    
	    public void showMenu() {
	        boolean running = true;

	        while (running) {
	            System.out.println("~~~~ Menu Principal ~~~~");
	            System.out.println("1. Gestion des documents");
	            System.out.println("2. Gestion des utilisateurs");
	            System.out.println("3. Gestion des emprunts");
	            System.out.println("4. Gestion des réservations");
	            System.out.println("5. Quitter");
	            System.out.print("Choisissez une option: ");

	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (choice) {
	                case 1:
	                    manageDocuments();
	                    break;
	                case 2:
	                    manageUsers();
	                    break;
	                case 3:
	                    manageborrows();
	                    break;
	                case 4:
	                    manageReservations();
	                    break;
	                case 5:
	                    running = false;
	                    System.out.println("Merci d'avoir utilisé le système de gestion de bibliothèque.");
	                    break;
	                default:
	                    System.out.println("Option invalide. Veuillez réessayer.");
	            }
	        }
	    }
	    
	    private void manageDocuments() {
	        System.out.println("~~~~ Gestion des Documents ~~~~");
	        System.out.println("1. Ajouter un document");
	        System.out.println("2. Mettre à jour un document");
	        System.out.println("3. Supprimer un document");
	        System.out.println("4. Afficher tous les documents");
	        System.out.println("5. Retour au menu principal");
	        System.out.print("Choisissez une option: ");

	        int choice = scanner.nextInt();
	        scanner.nextLine();  

	        switch (choice) {
	        case 1:
	            createDocument();
	            break;
	        case 2:
	            updateDocument();
	            break;
	        case 3:
	            deleteDocument();
	            break;
	        case 4:
	            displayDocuments();
	            break;
	        case 5:
	            // Retourne au menu principal
	            break;
	        default:
	            System.out.println("Option invalide. Veuillez réessayer.");
	        }
	   }
	    private void updateDocument() {}
	    private void deleteDocument() {}
	    private void displayDocuments() {}
	    
	    private void createDocument() {
	        System.out.println("=== Ajouter un Document ===");

	        System.out.print("Type de document (Livre, Magazine, JournalScientifique, TheseUniversitaire): ");
	        String type = scanner.nextLine().trim();  
	        System.out.print("Titre: ");
	        String titre = scanner.nextLine();

	        System.out.print("Auteur: ");
	        String auteur = scanner.nextLine();

	        LocalDate datePub = null;
	        boolean validDate = false;
	        while (!validDate) {
	            System.out.print("Date de publication (YYYY-MM-DD): ");
	            try {
	                datePub = LocalDate.parse(scanner.nextLine());
	                validDate = true;
	            } catch (DateTimeParseException e) {
	                System.out.println("Format de date invalide. Veuillez entrer la date au format YYYY-MM-DD.");
	            }
	        }

	        System.out.print("Nombre de pages: ");
	        int nbrPages = scanner.nextInt();
	        scanner.nextLine();  

	        //Document document;
	        switch (type.toLowerCase()) {
	            case "livre":
	                System.out.print("ISBN: ");
	                String isbn = scanner.nextLine();
	                Livre livre = new Livre(titre, auteur, datePub, nbrPages, "livre", 1,null,null, isbn);
	                try {
	                    LivreDAO livreDAO = new LivreDAOImpl();
	                    livreDAO.createLivre(livre); 
	                    System.out.println("Livre ajouté avec succès.");
	                } catch (SQLException e) {
	                    System.err.println("Erreur lors de l'ajout du livre: " + e.getMessage());
	                }
	                break;

	           
	            default:
	                System.out.println("Type de document invalide.");
	                return;
	        }
	    }

	  
	    private void manageUsers() {
	        System.out.println("~~~~ Gestion des Utilisateurs ~~~~");
	        System.out.println("1. Ajouter un utilisateur");
	        System.out.println("2. Mettre à jour un utilisateur");
	        System.out.println("3. Supprimer un utilisateur");
	        System.out.println("4. Afficher tous les utilisateurs");
	        System.out.println("5. Retour au menu principal");
	        System.out.print("Choisissez une option: ");

	        int choice = scanner.nextInt();
	        scanner.nextLine();  

	        // TODO:  la gestion des utilisateurs
	    }

	    private void manageborrows() {
	        System.out.println("~~~~ Gestion des Emprunts ~~~~");
	        System.out.println("1. Emprunter un document");
	        System.out.println("2. Retourner un document");
	        System.out.println("3. Retour au menu principal");
	        System.out.print("Choisissez une option: ");

	        int choice = scanner.nextInt();
	        scanner.nextLine();  

	        // TODO:  la gestion des emprunts
	    }
	    
	    private void manageReservations() {
	        System.out.println("~~~~ Gestion des Réservations ~~~~");
	        System.out.println("1. Réserver un document");
	        System.out.println("2. Annuler une réservation");
	        System.out.println("3. Retour au menu principal");
	        System.out.print("Choisissez une option: ");

	        int choice = scanner.nextInt();
	        scanner.nextLine();  

	        // TODO:  la gestion des réservations
	    }
}
