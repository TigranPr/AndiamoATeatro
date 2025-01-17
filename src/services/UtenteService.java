package services;

import dto.BigliettoRequest;
import dto.UtenteRequest;
import entity.Spettacolo;
import entity.Utente;
import repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UtenteService {
    public Utente getUtenteById(Integer id) {
        return UtenteRepository.getById(id);
    }

    public List<Utente> getAllUtenti() {
        return UtenteRepository.getAll();
    }

    public void insertUtente(UtenteRequest request) {
        UtenteRepository.insertUtente(request);
    }

    public void updateUtente(Integer id, UtenteRequest request) {
        UtenteRepository.updateUtente(id, request);
    }

    public void deleteUtenteById(Integer id) {
        UtenteRepository.deleteById(id);
    }

    public Double prenotaNBiglietti(Integer idUtente,Integer IdSpettacolo,Integer numeroBiglietti ,Integer idSala){
        Integer numeroPostiDisponibili= SalaRepository.countPostiDisponibiliByIdSalaAndIdSpettacolo(idSala,IdSpettacolo);
        List<Integer> nPostiDisponibili;
        Integer numeroBigliettiComprati = BigliettoRepository.countBigliettiPerUtenteInSpettacolo( IdSpettacolo,idUtente);
        System.out.println(numeroPostiDisponibili);
        if(numeroBigliettiComprati+numeroBiglietti>4){
            throw new IllegalArgumentException("Non puoi prenotare troppi biglietti");
        } else if (numeroPostiDisponibili>=numeroBiglietti) {
            nPostiDisponibili=SalaRepository.getNPostiDisponibiliByIdSalaAndIdSpettacolo(idSala,IdSpettacolo,numeroBiglietti);
            if(numeroBiglietti<=4){
                for(Integer posto:nPostiDisponibili){
                    BigliettoRepository.insertBiglietto(new BigliettoRequest(idUtente, IdSpettacolo,posto));
                }
                return numeroBiglietti*SpettacoloRepository.getById(IdSpettacolo).getPrezzo();
            }
            else{
                throw new IllegalArgumentException("Stai cercando di comprare troppi biglietti");
            }
        }
        else{
            throw new IllegalArgumentException("Posti non disponibili");
        }
    }

    public Double getPrezzoBigliettiPerSpettacolo(Integer idUtente,Integer idSpettacolo) {
        Integer numBiglietti= BigliettoRepository.countBigliettiPerUtenteInSpettacolo(idSpettacolo,idUtente);
        Double prezzo= SpettacoloRepository.getById(idSpettacolo).getPrezzo();
        return numBiglietti*prezzo;
    }

    public List<Spettacolo> getSpettacoliDisponibili(String comune, LocalDate data) {
        return SpettacoloRepository.getSpettacoliDisponibili(comune, data);
    }
    public List<Spettacolo> getSpettacoliDisponibili(String comune, LocalDate data,String genere) {
        return SpettacoloRepository.getSpettacoliDisponibili(comune, data, genere);
    }
    public List<Spettacolo> getSpettacoliDisponibili(String comune, LocalDate data, String genere,Integer idSala) {
        return SpettacoloRepository.getSpettacoliDisponibili(comune, data, genere,idSala);
    }

    public List<Spettacolo> getSpettacoliSuggeriti(Integer idUtente) {
        List<String> generi = SpettacoloRepository.get3GeneriUltimiSpettacoliByIdUtente(idUtente);
        List<Spettacolo> spettacoliSuggeriti = new ArrayList<>();
        for(String genere : generi){
            spettacoliSuggeriti.addAll(SpettacoloRepository.getSpettacoliByGenere(genere).stream()
                    .filter( spettacolo -> spettacolo.getDataOra().isAfter(LocalDate.now()
                            .atStartOfDay()) &&  spettacolo.getDataOra().isBefore(LocalDate.now().plusMonths(1).atStartOfDay())).toList());
        }
        return spettacoliSuggeriti;
    }
}
