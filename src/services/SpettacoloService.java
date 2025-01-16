package services;

import dto.SpettacoloRequest;
import entity.Spettacolo;
import repositories.SpettacoloRepository;
import java.util.List;

public class SpettacoloService {
    public Spettacolo getSpettacoloById(Integer id) {
        return SpettacoloRepository.getById(id);
    }

    public List<Spettacolo> getAllSpettacolo() {
        return SpettacoloRepository.getAll();
    }

    public void insertSpettacolo(SpettacoloRequest request) {
        SpettacoloRepository.insertSpettacolo(request);
    }

    public void updateSpettacolo(Integer id, SpettacoloRequest request) {
        SpettacoloRepository.updateSpettacolo(id, request);
    }

    public void deleteSpettacoloById(Integer id) {
        SpettacoloRepository.deleteById(id);
    }

}
