package repositories;

import configuration.DBConnection;
import dto.PostoRequest;
import entity.Posto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostoRepository {
    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Posto getById(Integer id) {
        String query = "SELECT * FROM posto WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPosto(resultSet);
            }
            else throw new IllegalArgumentException("Posto con id " + id + " non presente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Posto> getAll() {
        try {
            String query = "SELECT * FROM posto";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Posto> posto = new ArrayList<>();
            while (resultSet.next()) {
                posto.add(mapResultSetToPosto(resultSet));
            }
            return posto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insertPosto(PostoRequest request) {
        String query = "INSERT INTO biglietto (fila,numero,id_sala)" +
                "VALUES (?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,String.valueOf(request.fila()));
            statement.setInt(2,request.numero());
            statement.setInt(3,request.idSala());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePosto(Integer id, PostoRequest request) {
        String query = "UPDATE posto SET fila = ?, numero = ?, id_sala = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,String.valueOf(request.fila()));
            statement.setInt(2,request.numero());
            statement.setInt(3,request.idSala());
            statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteById(Integer id) {
        String query = "DELETE FROM posto WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private static Posto mapResultSetToPosto(ResultSet resultSet) {
        Posto posto = new Posto();
        try {
            posto.setId(resultSet.getInt("id"));
            posto.setFila((Character)resultSet.getObject("fila"));
            posto.setNumero(resultSet.getInt("numero"));
            posto.setIdSala(resultSet.getInt("id_sala"));

            return posto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
