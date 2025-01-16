package repositories;

import configuration.DBConnection;
import dto.UtenteRequest;
import entity.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteRepository {

    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Utente getById(Integer id) {
        String query = "SELECT * FROM utente WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUtente(resultSet);
            }
            else throw new IllegalArgumentException("Utente con id " + id + " non presente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Utente> getAll() {
        try {
            String query = "SELECT * FROM utente";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Utente> utenti = new ArrayList<>();
            while (resultSet.next()) {
                utenti.add(mapResultSetToUtente(resultSet));
            }
            return utenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insertUtente(UtenteRequest request) {
        String query = "INSERT INTO utente (nome,cognome,email,indirizzo,telefono)" +
                "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,request.nome());
            statement.setString(2,request.cognome());
            statement.setString(3,request.email());
            statement.setString(4,request.indirizzo());
            statement.setString(5,request.telefono());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUtente(Integer id, UtenteRequest request) {
        String query = "UPDATE utente SET nome = ?, cognome = ?, email = ?, indirizzo = ?, telefono = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,request.nome());
            statement.setString(2,request.cognome());
            statement.setString(3,request.email());
            statement.setString(4,request.indirizzo());
            statement.setString(5,request.telefono());
            statement.setInt(6,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteById(Integer id) {
        String query = "DELETE FROM utente WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Utente mapResultSetToUtente(ResultSet resultSet) {
        Utente utente = new Utente();
        try {
            utente.setId(resultSet.getInt("id"));
            utente.setNome(resultSet.getString("nome"));
            utente.setCognome(resultSet.getString("cognome"));
            utente.setEmail(resultSet.getString("email"));
            utente.setIndirizzo(resultSet.getString("indirizzo"));
            utente.setTelefono(resultSet.getString("telefono"));
            return utente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
