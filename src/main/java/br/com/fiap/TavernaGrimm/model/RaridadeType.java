package br.com.fiap.TavernaGrimm.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RaridadeType {
    COMUM,
    RARO,
    ÉPICO,
    LENDÁRIO;

    @JsonCreator
    public static PersonagemType fromString(String value) {
        return PersonagemType.valueOf(value.toUpperCase());
    }
}
