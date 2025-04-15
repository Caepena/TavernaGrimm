package br.com.fiap.TavernaGrimm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.TavernaGrimm.model.Item;
import br.com.fiap.TavernaGrimm.repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {
    
    public record ItemFilter(String nome, String tipo, Integer precoMin, Integer precoMax, String raridade) {
    }

    @Autowired
    private ItemRepository repository;

    @GetMapping
    @Cacheable("itens")
    @Operation(description = "Listar todos os itens", tags = "Itens", summary = "Lista de itens")
    public List<Item> index() {
        log.info("Buscando todos os itens");
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "itens", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Item create(@RequestBody @Valid Item item) {
        log.info("Cadastrando item " + item.getNome());
        return repository.save(item);
    }

    @GetMapping("{nome}")
    public Item get(@PathVariable String nome) {
        log.info("Buscando item " + nome);
        return getItem(nome);
    }

    @DeleteMapping("{nome}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable String nome) {
        log.info("Apagando item " + nome);
        repository.delete(getItem(nome));
    }

    @PutMapping("{nome}")
    public Item update(@PathVariable String nome, @RequestBody @Valid Item item) {
        log.info("Atualizando item " + nome);

        getItem(nome);
        item.setNome(nome);
        return repository.save(item);
    }

    private Item getItem(String nome) {
        return repository.findById(nome).orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }
}
