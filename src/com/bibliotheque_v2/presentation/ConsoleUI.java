package com.bibliotheque_v2.presentation;

import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import com.bibliotheque_v2.dao.MagazineDAO;
import com.bibliotheque_v2.dao.MagazineDAOImpl;
import com.bibliotheque_v2.dao.TheseUniversitaireDAO;
import com.bibliotheque_v2.dao.TheseUniversitaireDAOmpl;
import com.bibliotheque_v2.dao.JournalScientifiqueDAO;
import com.bibliotheque_v2.dao.JournalScientifiqueDAOImpl;
import com.bibliotheque_v2.dao.LivreDAO;
import com.bibliotheque_v2.dao.LivreDAOImpl;
import com.bibliotheque_v2.dao.DocumentDAOImpl;
import com.bibliotheque_v2.dao.UtilisateurDAOImpl;
import com.bibliotheque_v2.dao.EtudiantDAOImpl;
import com.bibliotheque_v2.dao.ProfesseurDAOImpl;
import com.bibliotheque_v2.dao.DocumentDAO;
import com.bibliotheque_v2.dao.UtilisateurDAO;
import com.bibliotheque_v2.dao.ProfesseurDAO;
import com.bibliotheque_v2.dao.EtudiantDAO;
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
	            System.out.print("Entrez votre email: ");
	            String email = scanner.nextLine();

	            UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl(); 

	            String userType = null;
	            try {
	                userType = utilisateurDAO.getUserTypeByEmail(email); 
	            } catch (SQLException e) {
	                System.err.println("Erreur lors de l'accès aux informations utilisateur: " + e.getMessage());
	                continue; 
	            }

	            if (userType == null) {
	                System.out.println("Utilisateur non trouvé.");
	                continue;
	            }

	            System.out.println("~~~~ Menu Principal ~~~~");

	            if (userType.equalsIgnoreCase("professeur") || userType.equalsIgnoreCase("etudiant")) {
	                System.out.println("1.Emprunter un document");
	                System.out.println("2. Retourner un document");
	                System.out.println("3. Reserver un document");
	                System.out.println("4. Annuler reservation d'un document");
	                System.out.println("5. Quitter");
	            } else if (userType.equalsIgnoreCase("admin")) {
	                System.out.println("1. Gestion des documents");
	                System.out.println("2. Gestion des utilisateurs");
	                System.out.println("3. Gestion des emprunts");
	                System.out.println("4. Gestion des réservations");
	                System.out.println("5. Quitter");
	            } else {
	                System.out.println("Type d'utilisateur inconnu.");
	                continue;
	            }

	            System.out.print("Choisissez une option: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (choice) {
	                case 1:
	                    if (userType.equalsIgnoreCase("professeur") || userType.equalsIgnoreCase("etudiant")) {
	                      borrowUI(email);
	                    } else if (userType.equalsIgnoreCase("admin")) {
		                    manageDocuments();	                    }
	                    break;
	                case 2:
	                    if (userType.equalsIgnoreCase("professeur") || userType.equalsIgnoreCase("etudiant")) {
	                    	retournerDocUI(email);
	                    } else if (userType.equalsIgnoreCase("admin")) {
	                    	  manageUsers();
	                    }
	                    break;
	                case 3:
	                    if (userType.equalsIgnoreCase("professeur") || userType.equalsIgnoreCase("etudiant")) {
	                        reservDocUI(email);
	                    } else if (userType.equalsIgnoreCase("admin")) {
	                    	   manageborrows();
	                    }
	                    break;
	                case 4:
	                	  if (userType.equalsIgnoreCase("professeur") || userType.equalsIgnoreCase("etudiant")) {
		                        annulReservDocUI(email);
		                    } else if (userType.equalsIgnoreCase("admin")) {
		                    	 manageReservations();
		                    }
	                    break;
	                case 5:
	                    if (userType.equalsIgnoreCase("admin")) {
	                        running = false;
	                    }
	                    break;
	                default:
	                    System.out.println("Option invalide. Veuillez réessayer.");
	            }
	        }
	    }
	    
	 

		private void annulReservDocUI(String email) {
			 UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl(); 
			    DocumentDAO documentDAO = new DocumentDAOImpl(); 

			 try {
			        int userId = utilisateurDAO.getCurrentUserId(email); 
			        System.out.print("Entrez le titre du document que vous souhaitez annuler reservation: ");
			        String documentTitle = scanner.nextLine();

			       Integer docID= documentDAO.getDocumentIDByTitle(documentTitle);

			        if (docID == null) {
			            System.out.println("Document non trouvé.");
			            return;
			        }
			            documentDAO.updateReserverIdNull(docID,userId);
			            System.out.println("Reservation annulée avec succès.");
			    } catch (SQLException e) {
			        System.err.println("Erreur lors du retour du document: " + e.getMessage());
			    } 
			
		}

		private void reservDocUI(String email) {
			UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
		    DocumentDAO documentDAO = new DocumentDAOImpl();
		    try {
		        int userId = utilisateurDAO.getCurrentUserId(email);
		        System.out.print("Entrez le titre du document que vous souhaitez réserver: ");
		        String documentTitle = scanner.nextLine();

		        Integer docID= documentDAO.getDocumentIDByTitle(documentTitle);

		        if (docID == null) {
		            System.out.println("Document non trouvé.");
		            return;
		        }
		            documentDAO.updateReserverId(docID, userId);
		            System.out.println("Document reservé avec succès.");
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de la réservation du document: " + e.getMessage());
		    }
		}

		private void retournerDocUI(String email) {
			 UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl(); 
			    DocumentDAO documentDAO = new DocumentDAOImpl(); 

			 try {
			        int userId = utilisateurDAO.getCurrentUserId(email); 
			        System.out.print("Entrez le titre du document que vous souhaitez retourner: ");
			        String documentTitle = scanner.nextLine();

			       Integer docID= documentDAO.getDocumentIDByTitle(documentTitle);

			        if (docID == null) {
			            System.out.println("Document non trouvé.");
			            return;
			        }
			            documentDAO.updateBorrowerIdNull(docID,userId);
			            System.out.println("Document retourné avec succès.");
			    } catch (SQLException e) {
			        System.err.println("Erreur lors du retour du document: " + e.getMessage());
			    } 
			
		}

		private void borrowUI(String email) {
			System.out.print("Entrez le titre du document que vous souhaitez emprunter: ");
		    String documentTitle = scanner.nextLine();
	
		    DocumentDAO documentDAO = new DocumentDAOImpl(); 
		    UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl(); 
		    try {
		        int userId = utilisateurDAO.getCurrentUserId(email); 
		        Integer docID = documentDAO.getDocumentIDByTitle(documentTitle);
		        if (docID == null) {
		            System.out.println("Document non trouvé avec le titre spécifié.");
		            return;
		        }
		        documentDAO.updateBorrowerId(docID, userId);
		        System.out.println("Document emprunté avec succès.");
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de l'emprunt du document: " + e.getMessage());
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

	        switch (choice) {
	            case 1:
	                createUser();
	                break;
	            case 2:
	                updateUser();
	                break;
	            case 3:
	                deleteUser();
	                break;
	            case 4:
	                displayAllUsers();
	                break;
	            case 5:
	                // Retourne au menu principal
	                break;
	            default:
	                System.out.println("Option invalide. Veuillez réessayer.");
	        }
	    }
	    
	    private void updateUser() {
	        System.out.println("=== Mettre à jour un Utilisateur ===");

	        System.out.print("Entrez l'ID de l'utilisateur à mettre à jour: ");
	        int userId = scanner.nextInt();
	        scanner.nextLine();  

	        try {
	        	UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
	            String type = utilisateurDAO.getUtilisateurType(userId);

	            if (type == null) {
	                System.out.println("Utilisateur non trouvé.");
	                return;
	            }

	            System.out.print("Entrez le nouveau nom : ");
	            String newName = scanner.nextLine();

	            System.out.print("Entrez le nouvel email: ");
	            String newEmail = scanner.nextLine();

	            switch (type.toLowerCase()) {
	                case "etudiant":
	                    updateEtudiantUI(userId, newName, newEmail);
	                    break;
	                case "professeur":
	                    updateProfesseurUI(userId, newName, newEmail);
	                    break;
	                default:
	                    System.out.println("Type d'utilisateur inconnu: " + type);
	            }
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la mise à jour de l'utilisateur: " + e.getMessage());
	        }
	    }
	    
	    
	    private void updateProfesseurUI(int userId, String newName, String newEmail) {
	    	   try {
	    	        ProfesseurDAO professeurDAO = new ProfesseurDAOImpl();

	    	        System.out.print("Entrez le nouveau departement : ");
	    	        String dep = scanner.nextLine();

	    	        professeurDAO.updateProfesseur(userId, newName, newEmail, dep);
	    	        System.out.println("Professeur mis à jour avec succès.");
	    	    } catch (SQLException e) {
	    	        System.err.println("Erreur lors de la mise à jour du professeur: " + e.getMessage());
	    	    }
			
		}

		private void updateEtudiantUI(int userId, String newName, String newEmail) {
			  try {
			        EtudiantDAO etudiantDAO = new EtudiantDAOImpl();

			        System.out.print("Entrez le nouveau numéro de l'étudiant : ");
			        String nouveauNumero = scanner.nextLine();

			        etudiantDAO.updateEtudiant(userId, newName, newEmail, nouveauNumero);
			        System.out.println("Étudiant mis à jour avec succès.");
			    } catch (SQLException e) {
			        System.err.println("Erreur lors de la mise à jour de l'étudiant: " + e.getMessage());
			    }
			
		}

		private void createUser() {
	    	System.out.println("=== Ajouter un Utilisateur ===");

	        System.out.print("Entrez le nom: ");
	        String name = scanner.nextLine();

	        System.out.print("Entrez l'email: ");
	        String email = scanner.nextLine();

	        System.out.print("Entrez le type (etudiant/professeur): ");
	        String type = scanner.nextLine();

	        switch (type.toLowerCase()) {
	        case "etudiant":
	            createEtudiant(name, email);
	            break;
	        case "professeur":
	            createProfesseur(name, email);
	            break;
	        default:
	            System.out.println("Type d'utilisateur inconnu.");
	        }
	    }
	    
	    private void createProfesseur(String name, String email) {
	    	System.out.print("Entrez le departement de professeur: ");
		    String dep = scanner.nextLine();
		    Professeur prof = new Professeur( name, email,"professeur", dep); 

		    try {
		        ProfesseurDAO professeurDAO = new ProfesseurDAOImpl();
		        professeurDAO.createProfesseur(prof);
		        System.out.println("Professeur créé avec succès.");
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de la création du professeur: " + e.getMessage());
		    }
		}

		private void createEtudiant(String name, String email) {
			 System.out.print("Entrez le numéro de l'étudiant: ");
			    String numero = scanner.nextLine();
			    Etudiant etudiant = new Etudiant( name, email,"etudiant", numero); 

			    try {
			        EtudiantDAO etudiantDAO = new EtudiantDAOImpl();
			        etudiantDAO.createEtudiant(etudiant);
			        System.out.println("Étudiant créé avec succès.");
			    } catch (SQLException e) {
			        System.err.println("Erreur lors de la création de l'étudiant: " + e.getMessage());
			    }
		}

 
	    private void deleteUser() {
	    	 System.out.println("=== Supprimer un Utilisateur ===");

	    	    System.out.print("Entrez l'ID de l'utilisateur à supprimer: ");
	    	    int userId = scanner.nextInt();
	    	    scanner.nextLine();  
	    	    try {
	    	        UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
	    	        String type = utilisateurDAO.getUtilisateurType(userId);

	    	        if (type == null) {
	    	            System.out.println("Utilisateur non trouvé.");
	    	            return;
	    	        }
	    	        switch (type.toLowerCase()) {
	    	            case "etudiant":
	    	                deleteEtudiant(userId);
	    	                break;
	    	            case "professeur":
	    	                deleteProfesseur(userId);
	    	                break;
	    	            default:
	    	                System.out.println("Type d'utilisateur inconnu: " + type);
	    	        }
	    	    } catch (SQLException e) {
	    	        System.err.println("Erreur lors de la suppression de l'utilisateur: " + e.getMessage());
	    	    }
	    }
 
	    private void deleteProfesseur(int userId) {
	    	try {
	            ProfesseurDAO professeurDAO = new ProfesseurDAOImpl();
	            professeurDAO.deleteProfesseur(userId);

	            System.out.println("Le professeur a été supprimé avec succès.");
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la suppression du professeur: " + e.getMessage());
	        }
			
		}

		private void deleteEtudiant(int userId) {
			  try {
			        EtudiantDAO etudiantDAO = new EtudiantDAOImpl();
			        etudiantDAO.deleteEtudiant(userId);
		            System.out.println("L'etudiant a été supprimé avec succès.");

			    } catch (SQLException e) {
			        System.err.println("Erreur lors de la suppression de l'étudiant : " + e.getMessage());
			    }
		}

		private void displayAllUsers() {
	    	System.out.println("=== Afficher tous les utilisateurs ===");
	    	displayAllEtudiantsUI();
	    	displayAllProfesseursUI();
	    }
	    
	    private void displayAllProfesseursUI() {
	    	 try {
		            ProfesseurDAO profDAO = new ProfesseurDAOImpl();
		            List<Professeur> profs = profDAO.getAllProfesseurs();
		            System.out.println("--- Professeurs ---");
		            for (Professeur prof : profs) {
		                System.out.println(prof);  
		            }
		        } catch (SQLException e) {
		            System.err.println("Erreur lors de l'affichage des professeurs: " + e.getMessage());
		        }
		}

		private void displayAllEtudiantsUI() {
			 try {
		            EtudiantDAO etudDAO = new EtudiantDAOImpl();
		            List<Etudiant> etudiants = etudDAO.getAllEtudiants();
		            System.out.println("--- Etudiants ---");
		            for (Etudiant etud : etudiants) {
		                System.out.println(etud);  
		            }
		        } catch (SQLException e) {
		            System.err.println("Erreur lors de l'affichage des etudiants: " + e.getMessage());
		        }
		}

		private void manageDocuments() {
	        System.out.println("~~~~ Gestion des Documents ~~~~");
	        System.out.println("1. Ajouter un document");
	        System.out.println("2. Mettre à jour un document");
	        System.out.println("3. Supprimer un document");
	        System.out.println("4. Afficher tous les documents");
	        System.out.println("5.Rechercher un document");
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
	        	displayAllDocuments();
	            break;
	        case 5:
	           searchDocument();
	            break;
	        default:
	            System.out.println("Option invalide. Veuillez réessayer.");
	        }
	   }
		
	    private void searchDocument() {
	    	   System.out.println("=== Rechercher un Document ===");

	    	    System.out.print("Entrez l'ID du document à rechercher: ");
	    	    int documentId = scanner.nextInt();
	    	    scanner.nextLine();  

	    	    try {
	    	        DocumentDAO documentDAO = new DocumentDAOImpl();
	    	        String documentType = documentDAO.getDocumentType(documentId);

	    	        switch (documentType.toLowerCase()) {
	    	            case "livre":
	    	                searchLivreByIdUI(documentId);
	    	                break;
	    	            case "magazine":
	    	                searchMagazineByIdUI(documentId);
	    	                break;
	    	            case "theseuniversitaire":
	    	                searchTheseUniversitaireByIdUI(documentId);
	    	                break;
	    	            case "journalscientifique":
	    	                searchJournalScientifiqueByIdUI(documentId);
	    	                break;
	    	            default:
	    	                System.out.println("Type de document inconnu: " + documentType);
	    	        }
	    	    } catch (SQLException e) {
	    	        System.err.println("Erreur lors de la recherche du document: " + e.getMessage());
	    	    }
			
		}

		private void searchJournalScientifiqueByIdUI(int documentId) {
			  JournalScientifiqueDAO journalScientifiqueDAO = new JournalScientifiqueDAOImpl();
			    try {
			        JournalScientifique journalScientifique = journalScientifiqueDAO.getJournalScientifiqueById(documentId);
			        if (journalScientifique != null) {
			            System.out.println("Journal Scientifique trouvé:");
			            System.out.println(journalScientifique);
			        } else {
			            System.out.println("Journal Scientifique non trouvé avec l'ID spécifié.");
			        }
			    } catch (SQLException e) {
			        System.err.println("Erreur lors de la recherche du journal scientifique: " + e.getMessage());
			    }
			
		}

		private void searchTheseUniversitaireByIdUI(int documentId) {
			TheseUniversitaireDAO theseUniversitaireDAO = new TheseUniversitaireDAOmpl();
		    try {
		        TheseUniversitaire theseUniversitaire = theseUniversitaireDAO.getTheseUniversitaireById(documentId);
		        if (theseUniversitaire != null) {
		            System.out.println("Thèse Universitaire trouvée:");
		            System.out.println(theseUniversitaire);
		        } else {
		            System.out.println("Thèse Universitaire non trouvée avec l'ID spécifié.");
		        }
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de la recherche de la thèse universitaire: " + e.getMessage());
		    }
			
		}

		private void searchMagazineByIdUI(int documentId) {
			  MagazineDAO magazineDAO = new MagazineDAOImpl();
			    try {
			        Magazine magazine = magazineDAO.getMagazineById(documentId);
			        if (magazine != null) {
			            System.out.println("Magazine trouvé:");
			            System.out.println(magazine);
			        } else {
			            System.out.println("Magazine non trouvé avec l'ID spécifié.");
			        }
			    } catch (SQLException e) {
			        System.err.println("Erreur lors de la recherche du magazine: " + e.getMessage());
			    }
		}

		private void searchLivreByIdUI(int documentId) {
			LivreDAO livreDAO = new LivreDAOImpl();
		    try {
		        Livre livre = livreDAO.getLivreById(documentId);
		        if (livre != null) {
		            System.out.println("Livre trouvé:");
		            System.out.println(livre);
		        } else {
		            System.out.println("Livre non trouvé avec l'ID spécifié.");
		        }
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de la recherche du livre: " + e.getMessage());
		    }
			
		}

		private void updateDocument() {
	        System.out.println("=== Mettre à Jour un Document ===");

	        System.out.print("Entrez l'ID du document à mettre à jour: ");
	        int documentId = scanner.nextInt();
	        scanner.nextLine();  

	        try {
	            DocumentDAO documentDAO = new DocumentDAOImpl();
	            String type = documentDAO.getDocumentType(documentId);
	            
	            System.out.print("Entrez le nouveau titre: ");
	            String nouveauTitre = scanner.nextLine();

	            System.out.print("Entrez le nouvel auteur: ");
	            String nouvelAuteur = scanner.nextLine();

	            System.out.print("Entrez la nouvelle date de publication (format: yyyy-mm-dd): ");
	            String newDatePubStr = scanner.nextLine();
	            LocalDate nouvelleDatePub = LocalDate.parse(newDatePubStr);

	            System.out.print("Entrez le nouveau nombre de pages: ");
	            int nouveauNbrPages = scanner.nextInt();
	            
	            scanner.nextLine(); 
	            switch (type.toLowerCase()) {
	                case "livre":
	                    updateLivreUI(documentId,nouveauTitre,nouvelAuteur,nouvelleDatePub,nouveauNbrPages);
	                    break;
	                case "magazine":
	                    updateMagazineUI(documentId,nouveauTitre,nouvelAuteur,nouvelleDatePub,nouveauNbrPages);
	                    break;
	                case "theseuniversitaire":
	                    updateTheseUniversitaireUI(documentId,nouveauTitre,nouvelAuteur,nouvelleDatePub,nouveauNbrPages);
	                    break;
	                case "journalscientifique":
	                    updateJournalScientifiqueUI(documentId,nouveauTitre,nouvelAuteur,nouvelleDatePub,nouveauNbrPages);
	                    break;
	                default:
	                    System.out.println("Type de document inconnu: " + type);
	            }
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la mise à jour du document: " + e.getMessage());
	        }
	    }
	    
	    private void deleteDocument() {
	    	 System.out.println("=== Supprimer un Document ===");

	    	    System.out.print("Entrez l'ID du document à supprimer: ");
	    	    int documentId = scanner.nextInt();
	    	    scanner.nextLine();  

	    	    try {
	    	    	 DocumentDAO documentDAO = new DocumentDAOImpl();
	    	         String type = documentDAO.getDocumentType(documentId);
	    	        
	    	         switch (type.toLowerCase()) {
	    	         case "livre":
	    	        	 deleteLivreUI(documentId);
	    	        	 break;
	    	         case "magazine":
	    	        	 deleteMagazineUI(documentId);
	    	        	 break;
	    	         case "theseuniversitaire":
	    	        	 deleteTheseUniversitaireUI(documentId);
	    	        	 break;
	    	         case "journalscientifique":
	    	        	 deleteJournalScientifiqueUI(documentId);
	    	        	 break;
	    	         }
	    	    } catch (SQLException e) {
	    	        System.err.println("Erreur lors de la suppression du document: " + e.getMessage());
	    	    }
	    }
	    
	    private void updateLivreUI(int documentId, String nouveauTitre, String nouvelAuteur, LocalDate nouvelleDatePub, int nouveauNbrPages) {
	    	try {
	            LivreDAO livreDAO = new LivreDAOImpl();

	            System.out.print("Entrez le nouvel ISBN: ");
	            String nouvelIsbn = scanner.nextLine();

	            livreDAO.updateLivre(documentId, nouveauTitre, nouvelAuteur, nouvelleDatePub, nouveauNbrPages, nouvelIsbn);
	            System.out.println("Livre mis à jour avec succès.");
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la mise à jour du livre: " + e.getMessage());
	        }
	    }
	    
	    private void updateMagazineUI(int documentId, String nouveauTitre, String nouvelAuteur, LocalDate nouvelleDatePub, int nouveauNbrPages) {
	    	try {
	            MagazineDAO magazineDAO = new MagazineDAOImpl();

	            System.out.print("Entrez le nouveau numéro du magazine: ");
	            String nouveauNumero = scanner.nextLine();

	            magazineDAO.updateMagazine(documentId, nouveauTitre, nouvelAuteur, nouvelleDatePub, nouveauNbrPages, nouveauNumero);
	            System.out.println("Magazine mis à jour avec succès.");
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la mise à jour du magazine: " + e.getMessage());
	        }
	    }
   
   		private void updateTheseUniversitaireUI(int documentId, String nouveauTitre, String nouvelAuteur, LocalDate nouvelleDatePub, int nouveauNbrPages) {
   			try {
   				TheseUniversitaireDAO theseDAO = new TheseUniversitaireDAOmpl();

   				System.out.print("Entrez la nouvelle université: ");
   				String nouvelleUniversite = scanner.nextLine();

   				System.out.print("Entrez le nouveau domaine: ");
   				String nouveauDomaine = scanner.nextLine();

   				theseDAO.updateTheseUniversitaire(documentId, nouveauTitre, nouvelAuteur, nouvelleDatePub, nouveauNbrPages, nouvelleUniversite, nouveauDomaine);
   				System.out.println("Thèse universitaire mise à jour avec succès.");
   			} catch (SQLException e) {
   				System.err.println("Erreur lors de la mise à jour de la thèse universitaire: " + e.getMessage());
   			} 	
   		}
   
   		private void updateJournalScientifiqueUI(int documentId, String nouveauTitre, String nouvelAuteur, LocalDate nouvelleDatePub, int nouveauNbrPages) {
   			try {
   		        JournalScientifiqueDAO journalDAO = new JournalScientifiqueDAOImpl();

   		        System.out.print("Entrez le nouveau domaine de recherche: ");
   		        String nouveauDomaineRecherche = scanner.nextLine();

   		        journalDAO.updateJournalScientifique(documentId, nouveauTitre, nouvelAuteur, nouvelleDatePub, nouveauNbrPages, nouveauDomaineRecherche);
   		        System.out.println("Journal scientifique mis à jour avec succès.");
   		    } catch (SQLException e) {
   		        System.err.println("Erreur lors de la mise à jour du journal scientifique: " + e.getMessage());
   		    } 
   		}
	    
	    private void deleteJournalScientifiqueUI(int documentId) {
	    	try {
	            JournalScientifiqueDAO theseDAO = new JournalScientifiqueDAOImpl();
	            theseDAO.deleteJournalScientifique(documentId);
	            System.out.println("Journal scientifique supprimé avec succès.");
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la suppression du Journal scientifique : " + e.getMessage());
	        }
	    }
	    
	    private void deleteTheseUniversitaireUI(int documentId) {
	    	try {
	            TheseUniversitaireDAO theseDAO = new TheseUniversitaireDAOmpl();
	            theseDAO.deleteTheseUniversitaire(documentId);
	            System.out.println("These universitaire supprimé avec succès.");
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la suppression du these universitaire: " + e.getMessage());
	        }
	    }
	    
	    private void deleteLivreUI(int documentId) {
	    	try {
	            LivreDAO livreDAO = new LivreDAOImpl();
	            livreDAO.deleteLivre(documentId);
	            System.out.println("Livre supprimé avec succès.");
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la suppression du livre: " + e.getMessage());
	        }
	    }
	    
	    private void deleteMagazineUI(int documentId) {
	    	try {
	            MagazineDAO magazineDAO = new MagazineDAOImpl();
	            magazineDAO.deleteMagazine(documentId);
	            System.out.println("Magazine supprimé avec succès.");
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la suppression du magazine: " + e.getMessage());
	        }
	    }
	    
	    private void displayAllDocuments() {
	    	System.out.println("=== Afficher tous les Documents ===");
	        
	        displayAllLivres();
	        displayAllMagazines();
	        displayAllThesesUniversitaires();
	        displayAllJournauxScientifiques();
	    }
	    
	    private void displayAllLivres() {
	        try {
	            LivreDAO livreDAO = new LivreDAOImpl();
	            List<Livre> livres = livreDAO.getAllLivres();
	            System.out.println("--- Livres ---");
	            for (Livre livre : livres) {
	                System.out.println(livre);  
	            }
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de l'affichage des livres: " + e.getMessage());
	        }
	    }
	    
	    private void displayAllMagazines() {
	        try {
	            MagazineDAO magazineDAO = new MagazineDAOImpl();
	            List<Magazine> magazines = magazineDAO.getAllMagazines();
	            System.out.println("--- Magazines ---");
	            for (Magazine magazine : magazines) {
	                System.out.println(magazine);  
	            }
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de l'affichage des magazines: " + e.getMessage());
	        }
	    }

	    private void displayAllThesesUniversitaires() {
	        try {
	            TheseUniversitaireDAO theseDAO = new TheseUniversitaireDAOmpl();
	            List<TheseUniversitaire> theses = theseDAO.getAllThesesUniversitaires();
	            System.out.println("--- Thèses Universitaires ---");
	            for (TheseUniversitaire these : theses) {
	                System.out.println(these); 
	            }
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de l'affichage des thèses universitaires: " + e.getMessage());
	        }
	    }

	    private void displayAllJournauxScientifiques() {
	        try {
	            JournalScientifiqueDAO journalDAO = new JournalScientifiqueDAOImpl();
	            List<JournalScientifique> journaux = journalDAO.getAllJournauxScientifiques();
	            System.out.println("--- Journaux Scientifiques ---");
	            for (JournalScientifique journal : journaux) {
	                System.out.println(journal); 
	            }
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de l'affichage des journaux scientifiques: " + e.getMessage());
	        }
	    }
	    
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

	        switch (type.toLowerCase()) {
	            case "livre":
	            	createLivreUI(titre, auteur, datePub, nbrPages);
	                break;
	            case "magazine":
	            	createMagazineUI(titre, auteur, datePub, nbrPages);
	            	break;
	            case "journalscientifique":
	            	createJournalScientifiqueUI(titre, auteur, datePub, nbrPages);
	            	break;
	            case "theseuniversitaire":
	            	createTheseUniversitairUI(titre, auteur, datePub, nbrPages);
	            	break;
	            	
	            default:
	                System.out.println("Type de document invalide.");
	                return;
	        }
	    }
	    
	    private void createTheseUniversitairUI(String titre,String auteur, LocalDate datePub,int nbrPages) {
	    	System.out.println("=== Ajouter une These Universitaire ===");
	    	System.out.print("Universite : ");
	    	String uni = scanner.nextLine();
	    	System.out.print("Domaine : ");
	    	String dom = scanner.nextLine();
	    	TheseUniversitaire these = new TheseUniversitaire(titre, auteur, datePub, nbrPages, "theseuniversitaire", 1,null,null,uni,dom);
	     	 try {
	     		TheseUniversitaireDAO theseDAO= new TheseUniversitaireDAOmpl();
	     		theseDAO.createTheseUniversitaire(these);
				  System.out.println("these universitaire ajouté avec succès.");
			  } catch ( SQLException e) {
	              System.err.println("Erreur lors de l'ajout du these universitaire: " + e.getMessage());
	          }
	    }
	    
	    private void createJournalScientifiqueUI(String titre,String auteur, LocalDate datePub,int nbrPages) {
	    	System.out.println("=== Ajouter un journal scientifique ===");
	    	System.out.print("Domaine recherche : ");
	    	String dom_rech = scanner.nextLine();
	    	JournalScientifique journal=new JournalScientifique(titre, auteur, datePub, nbrPages, "journalscientifique", 1,null,null,dom_rech);
	    	 try {
				  JournalScientifiqueDAO journalDAO= new JournalScientifiqueDAOImpl();
				  journalDAO.createJournalScientifique(journal);
				  System.out.println("journal scientifique ajouté avec succès.");
			  } catch ( SQLException e) {
	              System.err.println("Erreur lors de l'ajout du journal scientifique: " + e.getMessage());
	          }
	    }
	  private void createMagazineUI(String titre,String auteur, LocalDate datePub,int nbrPages) {
		  System.out.println("=== Ajouter un magazine ===");
		  System.out.print("Numero: ");
		  String num = scanner.nextLine();
		  Magazine magazine=new Magazine(titre, auteur, datePub, nbrPages, "magazine", 1,null,null,num);
		  try {
			  MagazineDAO magazineDAO= new MagazineDAOImpl();
			  magazineDAO.createMagazine(magazine);
			  System.out.println("Magazine ajouté avec succès.");
		  } catch ( SQLException e) {
              System.err.println("Erreur lors de l'ajout du magazine: " + e.getMessage());
          }
	  }
	  
	  private void createLivreUI(String titre,String auteur, LocalDate datePub,int nbrPages) {
		  System.out.println("=== Ajouter un Livre ===");
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
