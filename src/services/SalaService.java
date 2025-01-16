package services;

import dto.SalaRequest;
import entity.Sala;
import repositories.SalaRepository;

import java.sql.SQLException;
import java.util.List;

public class SalaService {
    public Sala getSalaById(Integer id) throws SQLException {
        return SalaRepository.getById(id);
    }

    public List<Sala> getAllSala() throws SQLException {
        return SalaRepository.getAll();
    }

    public void insertSala(SalaRequest request) throws SQLException {
        SalaRepository.insertSala(request);
    }

    public void updateSala(Integer id, SalaRequest request) throws SQLException {
        SalaRepository.updateSala(id, request);
    }

    public void deleteSalaById(Integer id) throws SQLException {
        SalaRepository.deleteById(id);
    }
}
