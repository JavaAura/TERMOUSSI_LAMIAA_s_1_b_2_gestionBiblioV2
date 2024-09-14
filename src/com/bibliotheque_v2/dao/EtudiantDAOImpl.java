package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque_v2.db.DbConnection;
import com.bibliotheque_v2.metier.Etudiant;

public class EtudiantDAOImpl implements EtudiantDAO {


    private Connection connection;

    public EtudiantDAOImpl() {
        this.connection = DbConnection.getInstance().getConnection();
    }	
    
    @Override
    public void createEtudiant(Etudiant etudiant) throws SQLException {
    	   String sql = "INSERT INTO etudiant (name, email, type, numero) VALUES (?, ?, ?, ?)";
           try (PreparedStatement stmt = connection.prepareStatement(sql)) {
               stmt.setString(1, etudiant.getName());
               stmt.setString(2, etudiant.getEmail());
               stmt.setString(3, etudiant.getType()); 
               stmt.setString(4, etudiant.getNumero());
               stmt.executeUpdate();
           } catch (SQLException e) {
               System.err.println("Erreur lors de l'insertion de l'étudiant dans la base de données: " + e.getMessage());
               throw e;
           }
    }
    
    @Override
    public void updateEtudiant(int id, String name, String email, String numero) throws SQLException {
    	  String updateQuery = "UPDATE etudiant SET name = ?, email = ?, numero = ? WHERE id = ?";

    	    try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {

    	        stmt.setString(1, name);
    	        stmt.setString(2, email);
    	        stmt.setString(3, numero); 
    	        stmt.setInt(4, id);

    	        int rowsUpdated = stmt.executeUpdate();

    	        if (rowsUpdated > 0) {
    	            System.out.println("L'étudiant a été mis à jour avec succès.");
    	        } else {
    	            System.out.println("Aucun étudiant trouvé avec l'ID spécifié.");
    	        }

    	    } catch (SQLException e) {
    	        System.err.println("Erreur lors de la mise à jour de l'étudiant: " + e.getMessage());
    	        throw e;
    	    }
    }
    
    @Override
    public void deleteEtudiant(int id) throws SQLException {
        String sql = "DELETE FROM etudiant WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    @Override
    public List<Etudiant> getAllEtudiants() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiant";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Etudiant etudiant = new Etudiant(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("type"),
                    rs.getString("numero")
                );
                etudiants.add(etudiant);
            }
        }
        return etudiants;
    }
}
