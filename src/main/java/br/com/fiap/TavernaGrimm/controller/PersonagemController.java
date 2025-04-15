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

import br.com.fiap.TavernaGrimm.model.Personagem;
import br.com.fiap.TavernaGrimm.repository.PersonagemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/personagem")
@Slf4j
public class PersonagemController {

    public record PersonagemFilter(String nome, String classe) {
    }

    @Autowired
    private PersonagemRepository repository;

    @Cacheable("personagens")
    @GetMapping
    @Operation(description = "Listar todos os personagens", tags = "Personagem", summary = "Listar personagens")
    public List<Personagem> index() {
        log.info("Listando personagens");
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "personagens", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Personagem create(@RequestBody @Valid Personagem personagem) {
        log.info("Criando personagem: ", personagem.getNome());
        return repository.save(personagem);
    }
    
    @GetMapping("{nome}")
    @Operation(description = "Buscar personagem por nome", tags = "Personagem", summary = "Buscar personagem")
    public Personagem get(@PathVariable String nome) {
        log.info("Buscando personagem: ", nome);
        return getPergonagem(nome);
    }

    @DeleteMapping("{nome}")
    @CacheEvict(value = "personagens", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String nome) {
        log.info("Deletando personagem: ", nome);
        repository.delete(getPergonagem(nome));
    }

    @PutMapping("{nome}")
    public Personagem update(@PathVariable String nome,@RequestBody @Valid Personagem personagem) {
        log.info("Atualizando personagem: ", nome);
        getPergonagem(nome);
        personagem.setNome(nome);
        return repository.save(personagem);
    }

    private Personagem getPergonagem(String nome) {
        return repository.findById(nome).orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }
}
