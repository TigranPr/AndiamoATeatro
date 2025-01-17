
import entity.Utente;
import services.UtenteService;


public class Main {
    public static void main(String[] args){
        Utente utente;
        utente = new Utente();
        utente.setNome("tizio2");
        utente.setCognome("belcognome");
        utente.setEmail("tizio2@gmail.com");
        utente.setIndirizzo("Roma");

        UtenteService utenteService = new UtenteService();
        //inserimento/registrazione utente
        //utenteService.insertUtente(new UtenteRequest(utente.getNome(),utente.getCognome(),utente.getEmail(),utente.getIndirizzo(),utente.getTelefono() ));

        //prenotazione biglietti + ritorno prezzo totale
        //System.out.println(utenteService.prenotaNBiglietti(1,1,1,1));

        //ricerca spettacoli disponibili per città e data + genere + idsede
        //System.out.println(SpettacoloRepository.getSpettacoliDisponibili("Pippo C", LocalDate.of(2025,2,10)));

        //suggerimenti su prossimi spettacoli disponibili del prossimo mese in base ad ultimi 3 generi visti
        //System.out.println(utenteService.getSpettacoliSuggeriti(2));

    }
}
