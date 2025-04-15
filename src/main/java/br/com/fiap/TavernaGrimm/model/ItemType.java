package br.com.fiap.TavernaGrimm.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ItemType {
    ARMA,
    ARMADURA,
    POÇÃO,
    ACESSÓRIO;

    @JsonCreator
    public static PersonagemType fromString(String value) {
        return PersonagemType.valueOf(value.toUpperCase());
    }
}
