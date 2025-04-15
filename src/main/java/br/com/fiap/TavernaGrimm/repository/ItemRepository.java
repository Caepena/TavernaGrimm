package br.com.fiap.TavernaGrimm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.TavernaGrimm.model.Item;

public interface ItemRepository extends JpaRepository<Item, String> {


}
