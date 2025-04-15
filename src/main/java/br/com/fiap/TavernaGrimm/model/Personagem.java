package br.com.fiap.TavernaGrimm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
        
    @Size(min = 1, max = 99, message = "O nível deve ter entre 1 e 99")
    private Integer nivel;
    private Integer moedas;

    private PersonagemType classe;
}
