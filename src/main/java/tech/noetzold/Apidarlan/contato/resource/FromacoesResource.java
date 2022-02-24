package tech.noetzold.Apidarlan.contato.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.Apidarlan.contato.model.Formacao;
import tech.noetzold.Apidarlan.contato.repository.Formacoes;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formacoes")
public class FromacoesResource {
    @Autowired
    private Formacoes formacoes;

    @PostMapping
    public Formacao adicionar(@Valid @RequestBody Formacao formacoe) {
        return formacoes.save(formacoe);
    }

    @GetMapping
    public List<Formacao> listar() {
        return formacoes.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formacao> buscar(@PathVariable Long id) {
        Formacao formacoe = formacoes.getOne(id);

        if (formacoe == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(formacoe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formacao> atualizar(@PathVariable Long id,
                                                @Valid @RequestBody Formacao formacoe) {
        Formacao existente = formacoes.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(formacoe, existente, "id");

        existente = formacoes.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Formacao formacoe = formacoes.getOne(id);

        if (formacoe == null) {
            return ResponseEntity.notFound().build();
        }

        formacoes.delete(formacoe);

        return ResponseEntity.noContent().build();
    }
}
