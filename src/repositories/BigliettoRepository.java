package repositories;

import configuration.DBConnection;
import dto.BigliettoRequest;

import entity.Biglietto;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BigliettoRepository {
    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Biglietto getById(Integer id) {
        String query = "SELECT * FROM biglietto WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToBiglietto(resultSet);
            }
            else throw new IllegalArgumentException("Biglietto con id " + id + " non presente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Biglietto> getAll() {
        try {
            String query = "SELECT * FROM biglietto";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Biglietto> biglietti = new ArrayList<>();
            while (resultSet.next()) {
                biglietti.add(mapResultSetToBiglietto(resultSet));
            }
            return biglietti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void insertBiglietto(BigliettoRequest request){
        String query = "INSERT INTO biglietto (id_utente,id_posto,id_spettacolo)" +
                "VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,request.idUtente());
            statement.setInt(2,request.idPosto());
            statement.setInt(3,request.idSpettacolo());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateBiglietto(Integer id, BigliettoRequest request) {
        String query = "UPDATE biglietto SET id_utente = ?, id_posto = ?, id_spettacolo = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,request.idUtente());
            statement.setInt(2,request.idPosto());
            statement.setInt(3,request.idSpettacolo());
            statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteById(Integer id) {
        String query = "DELETE FROM biglietto WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Biglietto mapResultSetToBiglietto(ResultSet resultSet) {
        Biglietto biglietto = new Biglietto();
        try {
            biglietto.setId(resultSet.getInt("id"));
            biglietto.setIdUtente(resultSet.getInt("id_utente"));
            biglietto.setIdPosto(resultSet.getInt("id_posto"));
            biglietto.setIdSpettacolo(resultSet.getInt("id_spettacolo"));

            return biglietto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Biglietto> getAllByIdSpettacolo(Integer idSpettacolo) {
        try {
            String query = "SELECT * FROM biglietto WHERE id_spettacolo = " + idSpettacolo;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Biglietto> biglietti = new ArrayList<>();
            while (resultSet.next()) {
                biglietti.add(mapResultSetToBiglietto(resultSet));
            }
            return biglietti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer countBigliettiPerUtenteInSpettacolo(Integer idSpettacolo,Integer idUtente) {
        String query =  "SELECT COUNT(*) AS numeroBiglietti FROM biglietto WHERE id_spettacolo = ?" +
                        " AND id_utente = ? " +
                        "GROUP BY id_utente";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSpettacolo);
            statement.setInt(2, idUtente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("numeroBiglietti");
            }
            else return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





}
