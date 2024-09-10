package com.bibliotheque_v2.dao;

import com.bibliotheque_v2.metier.Document;
import com.bibliotheque_v2.metier.Livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class LivreDao implements DocumentDAO {
    
    private Connection connection;

    /*public LivreDao(Connection connection) {
        this.connection = connection;
    }*/

    @Override
    public void createDocument(Document document) {
        if (!(document instanceof Livre)) {
            throw new IllegalArgumentException("Document must be of type Livre.");
        }
        
        Livre livre = (Livre) document;

        String sql = "INSERT INTO livre (titre, auteur, date_pub, nbr_pages, type, bibliotheque_id, borrower_id, reserver_id, isbn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteur());
            statement.setObject(3, livre.getDatePub()); 
            statement.setInt(4, livre.getNbrPages());
            statement.setString(5, livre.getType());
            statement.setInt(6,1);
            statement.setObject(7, livre.getBorrowerId());
            statement.setObject(8, livre.getReserverId());
            statement.setString(9, livre.getIsbn());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    
    @Override
    public void updateDocument(Document document) {
        if (!(document instanceof Livre)) {
            throw new IllegalArgumentException("Document must be of type Livre.");
        }

        Livre livre = (Livre) document;

        String sql = "UPDATE livre SET titre = ?, auteur = ?, date_pub = ?, nbr_pages = ?, type = ?, bibliotheque_id = ?, borrower_id = ?, reserver_id = ?, isbn = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteur());
            statement.setObject(3, livre.getDatePub());
            statement.setInt(4, livre.getNbrPages());
            statement.setString(5, livre.getType());
            statement.setInt(6, 1);
            statement.setObject(7, livre.getBorrowerId());
            statement.setObject(8, livre.getReserverId());
            statement.setString(9, livre.getIsbn());
            statement.setInt(10, livre.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteDocument(int documentId) {
        String sql = "DELETE FROM livre WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, documentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Document> findAll() throws SQLException {
        List<Document> livres = new ArrayList<>();
        String sql = "SELECT * FROM livre";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Livre livre = new Livre(
                    resultSet.getString("titre"),
                    resultSet.getString("auteur"),
                    resultSet.getObject("date_pub", LocalDate.class),
                    resultSet.getInt("nbr_pages"),
                    resultSet.getString("type"),
                    resultSet.getInt("bibliotheque_id"),
                    resultSet.getObject("borrower_id", Integer.class),
                    resultSet.getObject("reserver_id", Integer.class),
                    resultSet.getString("isbn")
                );
                livre.setId(resultSet.getInt("id"));
                livres.add(livre);
            }
        }
        return livres;
    }



}
