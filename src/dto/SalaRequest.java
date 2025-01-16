package dto;

import validator.Validator;

public record SalaRequest(
        String nome,
        Integer idSede
) {
    public SalaRequest(String nome,
                        Integer idSede) {
        this.nome = Validator.requireNotBlank(nome);
        this.idSede = (Integer)Validator.requireNotNull(idSede);
    }
}
