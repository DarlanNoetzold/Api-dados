package tech.noetzold.Apidarlan.contato.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.Apidarlan.contato.model.Tecnologia;
import tech.noetzold.Apidarlan.contato.repository.Tecnologias;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tecnologias")
public class TecnologiasResource {
    @Autowired
    private Tecnologias tecnologias;

    @PostMapping
    public Tecnologia adicionar(@Valid @RequestBody Tecnologia tecnologia) {
        return tecnologias.save(tecnologia);
    }

    @GetMapping
    public List<Tecnologia> listar() {
        return tecnologias.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnologia> buscar(@PathVariable Long id) {
        Tecnologia tecnologia = tecnologias.getOne(id);

        if (tecnologia == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tecnologia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecnologia> atualizar(@PathVariable Long id,
                                                 @Valid @RequestBody Tecnologia tecnologia) {
        Tecnologia existente = tecnologias.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(tecnologia, existente, "id");

        existente = tecnologias.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Tecnologia tecnologia = tecnologias.getOne(id);

        if (tecnologia == null) {
            return ResponseEntity.notFound().build();
        }

        tecnologias.delete(tecnologia);

        return ResponseEntity.noContent().build();
    }
}
