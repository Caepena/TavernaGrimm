package br.com.fiap.TavernaGrimm.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PersonagemType {
    GUERREIRO,
    MAGO,
    ARQUEIRO;

    @JsonCreator
    public static PersonagemType fromString(String value) {
        return PersonagemType.valueOf(value.toUpperCase());
    }
}
