package services;

import dto.BigliettoRequest;
import entity.Biglietto;
import repositories.BigliettoRepository;

import java.sql.SQLException;
import java.util.List;

public class BigliettoService {
    public Biglietto getBigliettoById(Integer id){
        return BigliettoRepository.getById(id);
    }

    public List<Biglietto> getAllBiglietti(){
        return BigliettoRepository.getAll();
    }

    public void insertBiglietto(BigliettoRequest request) throws SQLException {
        Integer numBiglietti = BigliettoRepository.countBigliettiPerUtenteInSpettacolo(request.idSpettacolo(),
                request.idUtente());
        if(numBiglietti<4){
            BigliettoRepository.insertBiglietto(request);
        }
        else{
            throw new IllegalArgumentException("Numero massimo di biglietti raggiunto");
        }
    }

    public void updateBiglietto(Integer id, BigliettoRequest request){
        BigliettoRepository.updateBiglietto(id, request);
    }

    public void deleteBigliettoById(Integer id){
        BigliettoRepository.deleteById(id);
    }
}
