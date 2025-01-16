package dto;

import validator.Validator;

public record BigliettoRequest(
    Integer idUtente,
    Integer idSpettacolo,
    Integer idPosto
) {
    public BigliettoRequest(Integer idUtente,
                             Integer idSpettacolo,
                             Integer idPosto) {
        this.idUtente = (Integer) Validator.requireNotNull(idUtente);
        this.idSpettacolo = (Integer) Validator.requireNotNull(idSpettacolo);
        this.idPosto = (Integer) Validator.requireNotNull(idPosto);
    }
}
