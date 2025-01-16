package services;

import dto.SedeRequest;
import entity.Sede;
import repositories.SedeRepository;

import java.sql.SQLException;
import java.util.List;

public class SedeService {
    public Sede getSedeById(Integer id) throws SQLException {
        return SedeRepository.getById(id);
    }

    public List<Sede> getAllSede() throws SQLException {
        return SedeRepository.getAll();
    }

    public void insertSede(SedeRequest request) throws SQLException {
        SedeRepository.insertSede(request);
    }

    public void updateSede(Integer id, SedeRequest request) throws SQLException {
        SedeRepository.updateSede(id, request);
    }

    public void deleteSedeById(Integer id) throws SQLException {
        SedeRepository.deleteById(id);
    }

}
