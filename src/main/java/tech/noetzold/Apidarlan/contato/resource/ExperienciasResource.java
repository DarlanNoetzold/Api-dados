package tech.noetzold.Apidarlan.contato.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.Apidarlan.contato.model.Experiencia;
import tech.noetzold.Apidarlan.contato.repository.Experiencias;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/experiencias")
public class ExperienciasResource {

    @Autowired
    private Experiencias experiencias;

    @PostMapping
    public Experiencia adicionar(@Valid @RequestBody Experiencia experiencia) {
        return experiencias.save(experiencia);
    }

    @GetMapping
    public List<Experiencia> listar() {
        return experiencias.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experiencia> buscar(@PathVariable Long id) {
        Experiencia experiencia = experiencias.getOne(id);

        if (experiencia == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(experiencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experiencia> atualizar(@PathVariable Long id,
                                                 @Valid @RequestBody Experiencia experiencia) {
        Experiencia existente = experiencias.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(experiencia, existente, "id");

        existente = experiencias.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Experiencia experiencia = experiencias.getOne(id);

        if (experiencia == null) {
            return ResponseEntity.notFound().build();
        }

        experiencias.delete(experiencia);

        return ResponseEntity.noContent().build();
    }
}

