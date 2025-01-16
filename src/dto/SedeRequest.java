package dto;

import validator.Validator;

public record SedeRequest(
        String nome,
        String indirizzo,
        String comune,
        Boolean allAperto

) {
    public SedeRequest(String nome,
                       String indirizzo,
                       String comune,
                       Boolean allAperto) {
        this.nome = Validator.requireNotBlank(nome);
        this.indirizzo = Validator.requireNotBlank(indirizzo);
        this.comune = Validator.requireNotBlank(comune);
        this.allAperto =((Boolean)Validator.requireNotNull(allAperto));
    }
}
