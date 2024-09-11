package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque_v2.db.DbConnection;
import com.bibliotheque_v2.metier.JournalScientifique;

public class JournalScientifiqueDAOImpl implements JournalScientifiqueDAO {

	 private Connection connection;

	   public JournalScientifiqueDAOImpl() {
	        this.connection = DbConnection.getInstance().getConnection();
	    }
	   
	   @Override
	    public void createJournalScientifique(JournalScientifique journal) throws SQLException {
	    	 String sql = "INSERT INTO journal_scientifique (titre, auteur, datePub, nbrPages, type, bibliotheque_id, borrower_id, reserver_id, domaine_recherche) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, journal.getTitre());
	            stmt.setString(2, journal.getAuteur());
	            stmt.setDate(3, java.sql.Date.valueOf(journal.getDatePub()));
	            stmt.setInt(4, journal.getNbrPages());
	            stmt.setString(5, journal.getType());
	            stmt.setInt(6, journal.getBibliothequeId());
	            stmt.setObject(7, journal.getBorrowerId(), java.sql.Types.INTEGER); // Allow nulls
	            stmt.setObject(8, journal.getReserverId(), java.sql.Types.INTEGER);  
	            stmt.setString(9, journal.getDomaineRecherche());
	            stmt.executeUpdate();
	        }catch (SQLException e) {
	            System.err.println("Error inserting JournalScientifique into database: " + e.getMessage());
	            throw e;
	        }
	    }

	    @Override
	    public void updateJournalScientifique(JournalScientifique journal) throws SQLException {
	        //  update logic
	    }

	    @Override
	    public void deleteJournalScientifique(int journalId) throws SQLException {
	        //  delete logic
	    }

	    @Override
	    public JournalScientifique findJournalScientifiqueById(int journalId) throws SQLException {
	        //  find logic
	        return null;
	    }

	    @Override
	    public List<JournalScientifique> findAllJournalScientifiques() throws SQLException {
	        List<JournalScientifique> journals = new ArrayList<>();
	        String sql = "SELECT * FROM journal_scientifique";
	        try (PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	            	JournalScientifique journal = new JournalScientifique(
	                    rs.getString("titre"),
	                    rs.getString("auteur"),
	                    rs.getDate("datepub").toLocalDate(),
	                    rs.getInt("nbrpages"),
	                    rs.getString("type"),
	                    rs.getInt("bibliotheque_id"),
	                    null, // borrowerId
	                    null, // reserverId
	                    rs.getString("domaine_recherche")
	                );
	            	journals.add(journal);
	            }
	        }
	        return journals;
	    }
}
