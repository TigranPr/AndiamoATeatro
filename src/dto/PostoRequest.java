package dto;

import validator.Validator;

public record PostoRequest(
        Character fila,
        Integer numero,
        Integer idSala
) {

    public PostoRequest(
            Character fila,
            Integer numero,
            Integer idSala
    ) {
        this.fila =  Validator.requireNotBlank(fila);
        this.numero = Validator.requirePositive(numero);
        this.idSala = (Integer)Validator.requireNotNull(idSala);
    }

}
