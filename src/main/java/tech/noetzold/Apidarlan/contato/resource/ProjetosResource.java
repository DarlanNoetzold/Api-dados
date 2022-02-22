package tech.noetzold.Apidarlan.contato.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.Apidarlan.contato.model.Projeto;
import tech.noetzold.Apidarlan.contato.repository.Projetos;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetosResource {
    @Autowired
    private Projetos projetos;

    @PostMapping
    public Projeto adicionar(@Valid @RequestBody Projeto projeto) {
        return projetos.save(projeto);
    }

    @GetMapping
    public List<Projeto> listar() {
        return projetos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscar(@PathVariable Long id) {
        Projeto projeto = projetos.getOne(id);

        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(projeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Long id,
                                                @Valid @RequestBody Projeto projeto) {
        Projeto existente = projetos.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(projeto, existente, "id");

        existente = projetos.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Projeto projeto = projetos.getOne(id);

        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }

        projetos.delete(projeto);

        return ResponseEntity.noContent().build();
    }
}
