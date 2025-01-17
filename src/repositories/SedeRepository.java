package repositories;

import configuration.DBConnection;
import dto.SedeRequest;
import entity.Sede;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SedeRepository {
    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Sede getById(Integer id) {
        String query = "SELECT * FROM sede WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToSede(resultSet);
            }
            else throw new IllegalArgumentException("Sede con id " + id + " non presente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Sede> getAll() {
        try {
            String query = "SELECT * FROM sede";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Sede> sede = new ArrayList<>();
            while (resultSet.next()) {
                sede.add(mapResultSetToSede(resultSet));
            }
            return sede;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insertSede(SedeRequest request) {
        String query = "INSERT INTO sede (nome,indirizzo,comune,all_aperto)" +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,request.nome());
            statement.setString(2,request.indirizzo());
            statement.setString(3,request.comune());
            statement.setBoolean(4,request.allAperto());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateSede(Integer id, SedeRequest request) {
        String query = "UPDATE sede SET nome = ?, indirizzo = ?, comune = ?, all_aperto = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,request.nome());
            statement.setString(2,request.indirizzo());
            statement.setString(3,request.comune());
            statement.setBoolean(4,request.allAperto());
            statement.setInt(5,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteById(Integer id) {
        String query = "DELETE FROM sede WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Sede mapResultSetToSede(ResultSet resultSet) {
        Sede sede = new Sede();
        try {
            sede.setId(resultSet.getInt("id"));
            sede.setNome(resultSet.getString("nome"));
            sede.setIndirizzo(resultSet.getString("indirizzo"));
            sede.setComune(resultSet.getString("comune"));
            sede.setAllAperto(resultSet.getBoolean("all_aperto"));

            return sede;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
