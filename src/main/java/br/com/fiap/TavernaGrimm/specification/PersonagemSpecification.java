package br.com.fiap.TavernaGrimm.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.TavernaGrimm.controller.PersonagemController.PersonagemFilter;
import br.com.fiap.TavernaGrimm.model.Personagem;
import jakarta.persistence.criteria.Predicate;

public class PersonagemSpecification {
    
    public static Specification<Personagem> withFilters(PersonagemFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.nome() != null && !filter.nome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%"));
            }

            if (filter.classe() != null && !filter.classe().isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("classe")), filter.classe().toLowerCase()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
