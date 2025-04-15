package br.com.fiap.TavernaGrimm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.TavernaGrimm.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, String> {

    
}
