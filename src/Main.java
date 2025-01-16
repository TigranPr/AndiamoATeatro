import dto.UtenteRequest;
import entity.Utente;
import services.UtenteService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UtenteService utenteService = new UtenteService();
        UtenteRequest utente = new UtenteRequest("Mario", "Rossi",
                "mario.rossi@gmail.com","Roma","3483943843");
       // utenteService.insertUtente(utente);

        utenteService.prenotaNBiglietti(1,1,3,1);

    }
}
