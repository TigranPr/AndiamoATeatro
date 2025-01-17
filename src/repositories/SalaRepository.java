package repositories;

import configuration.DBConnection;
import dto.SalaRequest;
import entity.Sala;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaRepository {
    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Sala getById(Integer id) {
        String query = "SELECT * FROM sala WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToSala(resultSet);
            }
            else throw new IllegalArgumentException("Sala con id " + id + " non presente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Sala> getAll() {
        try {
            String query = "SELECT * FROM sala";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Sala> sala = new ArrayList<>();
            while (resultSet.next()) {
                sala.add(mapResultSetToSala(resultSet));
            }
            return sala;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insertSala(SalaRequest request) {
        String query = "INSERT INTO sala (nome,id_sede)" +
                "VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,request.nome());
            statement.setInt(2,request.idSede());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateSala(Integer id, SalaRequest request) {
        String query = "UPDATE sala SET nome = ?, id_sede = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,request.nome());
            statement.setInt(2,request.idSede());
            statement.setInt(3,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteById(Integer id) {
        String query = "DELETE FROM sala WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Integer countPostiDisponibiliByIdSalaAndIdSpettacolo(Integer idSala,Integer idSpettacolo){
        String query = "SELECT COUNT(*) AS numero_posti_disponibili FROM posto JOIN sala ON sala.id=posto.id_sala " +
                "JOIN spettacolo ON spettacolo.id_sala=sala.id WHERE spettacolo.id=? AND posto.id_sala = ? AND posto.id NOT IN " +
                "(SELECT posto.id FROM biglietto JOIN posto ON biglietto.id_posto = posto.id WHERE posto.id_sala = ? " +
                "AND biglietto.id_spettacolo = ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,idSpettacolo);
            statement.setInt(2,idSala);
            statement.setInt(3,idSala);
            statement.setInt(4,idSpettacolo);
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("numero_posti_disponibili");
            }
            else throw new IllegalArgumentException("Sala con id " + idSala + " non presente");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Integer> getNPostiDisponibiliByIdSalaAndIdSpettacolo(Integer idSala,Integer idSpettacolo,Integer nPosti){
        String query = "SELECT posto.id FROM posto JOIN sala ON sala.id=posto.id_sala " +
                "JOIN spettacolo ON spettacolo.id_sala=sala.id WHERE spettacolo.id=? AND posto.id_sala = ? AND posto.id NOT IN " +
                "(SELECT posto.id FROM biglietto JOIN posto ON biglietto.id_posto = posto.id WHERE posto.id_sala = ? " +
                "AND biglietto.id_spettacolo = ?)" +
                "ORDER BY posto.fila ASC, posto.numero ASC " +
                "LIMIT ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,idSpettacolo);
            statement.setInt(2,idSala);
            statement.setInt(3,idSala);
            statement.setInt(4,idSpettacolo);
            statement.setInt(5,nPosti);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> posti = new ArrayList<>();
            while (resultSet.next()){
                posti.add(resultSet.getInt("id"));
            }
            return posti;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private static Sala mapResultSetToSala(ResultSet resultSet) {
        Sala sala = new Sala();
        try {
            sala.setId(resultSet.getInt("id"));
            sala.setNome(resultSet.getString("nome"));
            sala.setIdSede(resultSet.getInt("id_sede"));

            return sala;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
