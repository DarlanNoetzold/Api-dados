package tech.noetzold.Apidarlan.contato.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.Apidarlan.contato.model.Ferramenta;
import tech.noetzold.Apidarlan.contato.repository.Ferramentas;

import javax.validation.Valid;
import java.util.List;

public class FerramentasResource {
    @Autowired
    private Ferramentas ferramentas;

    @PostMapping
    public Ferramenta adicionar(@Valid @RequestBody Ferramenta ferramenta) {
        return ferramentas.save(ferramenta);
    }

    @GetMapping
    public List<Ferramenta> listar() {
        return ferramentas.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ferramenta> buscar(@PathVariable Long id) {
        Ferramenta ferramenta = ferramentas.getOne(id);

        if (ferramenta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ferramenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ferramenta> atualizar(@PathVariable Long id,
                                              @Valid @RequestBody Ferramenta ferramenta) {
        Ferramenta existente = ferramentas.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(ferramenta, existente, "id");

        existente = ferramentas.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Ferramenta ferramenta = ferramentas.getOne(id);

        if (ferramenta == null) {
            return ResponseEntity.notFound().build();
        }

        ferramentas.delete(ferramenta);

        return ResponseEntity.noContent().build();
    }
}
