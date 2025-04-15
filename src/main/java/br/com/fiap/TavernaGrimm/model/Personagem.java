package br.com.fiap.TavernaGrimm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {
    @NotEmpty(message = "O nome do personagem não pode ser vazio")
    @Id
    private String nome;
        
    @Min(value = 1, message = "O nível deve ser no mínimo 1")
    @Max(value = 99, message = "O nível deve ser no máximo 99")
    private Integer nivel;

    @Positive(message = "As moedas devem ser um valor positivo")
    private Integer moedas;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PersonagemType classe;
}
