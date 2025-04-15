package br.com.fiap.TavernaGrimm.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.TavernaGrimm.controller.ItemController.ItemFilter;
import br.com.fiap.TavernaGrimm.model.Item;
import jakarta.persistence.criteria.Predicate;

public class ItemSpecification {

    public static Specification<Item> withFilters(ItemFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.nome() != null && !filter.nome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }

            if (filter.tipo() != null && !filter.tipo().isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("tipo")), filter.tipo().toLowerCase()));
            }

            if (filter.precoMin() != null && filter.precoMax() != null) {
                predicates.add(cb.between(root.get("preco"), filter.precoMin(), filter.precoMax()));
            } else if (filter.precoMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("preco"), filter.precoMin()));
            } else if (filter.precoMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("preco"), filter.precoMax()));
            }

            if (filter.raridade() != null && !filter.raridade().isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("raridade")), filter.raridade().toLowerCase()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}