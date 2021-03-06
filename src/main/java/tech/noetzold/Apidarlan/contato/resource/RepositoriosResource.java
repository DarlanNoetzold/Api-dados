package tech.noetzold.Apidarlan.contato.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.noetzold.Apidarlan.contato.model.Repositorio;
import tech.noetzold.Apidarlan.contato.repository.Repositorios;

@RestController
@RequestMapping("/repositorios")
public class RepositoriosResource {

    @Autowired
    private Repositorios repositorios;

    @PostMapping
    public Repositorio adicionar(@Valid @RequestBody Repositorio repositorio) {
        return repositorios.save(repositorio);
    }

    @GetMapping
    public List<Repositorio> listar() {
        return repositorios.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repositorio> buscar(@PathVariable Long id) {
        Repositorio repositorio = repositorios.getOne(id);

        if (repositorio == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repositorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repositorio> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody Repositorio repositorio) {
        Repositorio existente = repositorios.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(repositorio, existente, "id");

        existente = repositorios.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Repositorio repositorio = repositorios.getOne(id);

        if (repositorio == null) {
            return ResponseEntity.notFound().build();
        }

        repositorios.delete(repositorio);

        return ResponseEntity.noContent().build();
    }
}