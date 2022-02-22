package tech.noetzold.Apidarlan.contato.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.Apidarlan.contato.model.Habilidade;
import tech.noetzold.Apidarlan.contato.repository.Habilidades;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/habilidades")
public class HabilidadesResource {
    @Autowired
    private Habilidades habilidades;

    @PostMapping
    public Habilidade adicionar(@Valid @RequestBody Habilidade habilidade) {
        return habilidades.save(habilidade);
    }

    @GetMapping
    public List<Habilidade> listar() {
        return habilidades.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habilidade> buscar(@PathVariable Long id) {
        Habilidade habilidade = habilidades.getOne(id);

        if (habilidade == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(habilidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habilidade> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody Habilidade habilidade) {
        Habilidade existente = habilidades.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(habilidade, existente, "id");

        existente = habilidades.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Habilidade habilidade = habilidades.getOne(id);

        if (habilidade == null) {
            return ResponseEntity.notFound().build();
        }

        habilidades.delete(habilidade);

        return ResponseEntity.noContent().build();
    }
}

