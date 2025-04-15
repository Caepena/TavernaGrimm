package br.com.fiap.TavernaGrimm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @NotBlank(message = "O nome do item não pode ser vazio")
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo do item deve ser preenchido")
    private ItemType tipo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A raridade do item deve ser preenchida")
    private RaridadeType raridade;

    @NotNull(message = "O preço do item deve ser preenchido")
    @Positive(message = "O preço do item deve ser maior que zero")
    private Integer preco;

    @NotNull(message = "O item deve ter um dono.")
    @ManyToOne
    private Personagem dono;
}
