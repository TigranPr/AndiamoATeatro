package dto;

import validator.Validator;

import java.time.LocalDateTime;

public record SpettacoloRequest(
        LocalDateTime dataOra,
        String genere,
        Double prezzo,
        Double durataInOre,
        Integer idSala
) {
    public SpettacoloRequest(LocalDateTime dataOra,
                             String genere,
                             Double prezzo,
                             Double durataInOre,
                             Integer idSala) {
        this.dataOra = (LocalDateTime) Validator.requireNotNull(dataOra);
        this.genere = Validator.requireNotBlank(genere);
        this.prezzo = Validator.requireGreaterThenZero(prezzo);
        this.durataInOre = Validator.requireGreaterThenZero(durataInOre);
        this.idSala =(Integer)Validator.requireNotNull(idSala);
    }
}
