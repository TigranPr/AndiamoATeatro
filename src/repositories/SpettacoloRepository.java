package repositories;

import configuration.DBConnection;
import dto.SpettacoloRequest;
import entity.Spettacolo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SpettacoloRepository {
    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Spettacolo getById(Integer id) {
        String query = "SELECT * FROM spettacolo WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToSpettacolo(resultSet);
            }
            else throw new IllegalArgumentException("Spettacolo con id " + id + " non presente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Spettacolo> getAll() {
        try {
            String query = "SELECT * FROM spettacolo";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Spettacolo> spettacolo = new ArrayList<>();
            while (resultSet.next()) {
                spettacolo.add(mapResultSetToSpettacolo(resultSet));
            }
            return spettacolo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insertSpettacolo(SpettacoloRequest request) {
        String query = "INSERT INTO spettacolo (data_ora,genere,durata_in_ore,prezzo,id_sala)" +
                "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setTimestamp(1,Timestamp.valueOf(request.dataOra()));
            statement.setString(2,request.genere());
            statement.setDouble(3,request.durataInOre());
            statement.setDouble(4,request.prezzo());
            statement.setInt(5,request.idSala());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateSpettacolo(Integer id, SpettacoloRequest request) {
        String query = "UPDATE spettacolo SET data_ora = ?," +
                " genere = ?, " +
                "durata_in_ore = ?, " +
                "prezzo = ?, " +
                "id_sala = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setTimestamp(1,Timestamp.valueOf(request.dataOra()));
            statement.setString(2,request.genere());
            statement.setDouble(3,request.durataInOre());
            statement.setDouble(4,request.prezzo());
            statement.setInt(5,request.idSala());
            statement.setInt(6,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteById(Integer id) {
        String query = "DELETE FROM spettacolo WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Spettacolo mapResultSetToSpettacolo(ResultSet resultSet) {
        Spettacolo spettacolo = new Spettacolo();
        try {
            spettacolo.setId(resultSet.getInt("id"));
            spettacolo.setDataOra(resultSet.getTimestamp("data_ora").toLocalDateTime());
            spettacolo.setGenere(resultSet.getString("genere"));
            spettacolo.setDurataInOre(resultSet.getDouble("durata_in_ore"));
            spettacolo.setPrezzo(resultSet.getDouble("prezzo"));
            spettacolo.setIdSala(resultSet.getInt("id_sala"));

            return spettacolo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Spettacolo> getSpettacoliDisponibili(String comune, LocalDate data) {
        String query = "SELECT spettacolo.* FROM spettacolo JOIN sala ON spettacolo.id_sala = sala.id" +
                " JOIN sede ON sala.id_sede = sede.id WHERE sede.comune = '" + comune
                + "' AND DATE(spettacolo.data_ora) = '" + data.toString() + "'";
        System.out.println(query);
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Spettacolo> spettacoli = new ArrayList<>();
            while (resultSet.next()) {
                spettacoli.add(mapResultSetToSpettacolo(resultSet));
            }
            return spettacoli;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Spettacolo> getSpettacoliDisponibili(String comune, LocalDate data,String genere) {
        String query = "SELECT spettacolo.* FROM spettacolo JOIN sala ON spettacolo.id_sala = sala.id" +
                " JOIN sede ON sala.id_sede = sede.id WHERE spettacolo.genere = '" + genere + "' AND sede.comune = '" + comune
                + "' AND DATE(spettacolo.data_ora) = '" + data.toString() +"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Spettacolo> spettacoli = new ArrayList<>();
            while (resultSet.next()) {
                spettacoli.add(mapResultSetToSpettacolo(resultSet));
            }
            return spettacoli;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Spettacolo> getSpettacoliDisponibili(String comune, LocalDate data,String genere,Integer idSede) {
        String query = "SELECT spettacolo.* FROM spettacolo JOIN sala ON spettacolo.id_sala = sala.id" +
                " JOIN sede ON sala.id_sede = sede.id WHERE sede.id =" + idSede +
                " AND spettacolo.genere = '" + genere + "' AND sede.comune = '" + comune
                + "' AND DATE(spettacolo.data_ora) = '" + data.toString() + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Spettacolo> spettacoli = new ArrayList<>();
            while (resultSet.next()) {
                spettacoli.add(mapResultSetToSpettacolo(resultSet));
            }
            return spettacoli;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> get3GeneriUltimiSpettacoliByIdUtente(Integer idUtente) {
        String query =  "SELECT DISTINCT spettacolo.genere,spettacolo.data_ora FROM spettacolo " +
                "JOIN biglietto ON spettacolo.id=biglietto.id_spettacolo " +
                "WHERE biglietto.id_utente = " + idUtente +
                " ORDER BY spettacolo.data_ora DESC " +
                "LIMIT 3";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<String> generi = new ArrayList<>();
            while (resultSet.next()) {
                generi.add(resultSet.getString("genere"));
            }
            return generi;
        } catch (SQLException e) {
            throw new RuntimeException("L'utente con id "+ idUtente +" non ha visto nessuno spettacolo");
        }
    }

    public static List<Spettacolo> getSpettacoliByGenere(String genere) {
        String query = "SELECT * FROM spettacolo WHERE genere = '" + genere + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Spettacolo> spettacolo = new ArrayList<>();
            while (resultSet.next()) {
                spettacolo.add(mapResultSetToSpettacolo(resultSet));
            }
            return spettacolo;
        } catch (SQLException e) {
            throw new RuntimeException("Non ci sono spettacoli di genere : "+ genere);
        }
    }
}
