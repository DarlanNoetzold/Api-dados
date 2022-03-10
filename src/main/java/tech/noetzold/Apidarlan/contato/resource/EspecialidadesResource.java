package tech.noetzold.Apidarlan.contato.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.Apidarlan.contato.model.Especialidade;
import tech.noetzold.Apidarlan.contato.repository.Especialidades;
import tech.noetzold.Apidarlan.contato.repository.Experiencias;
import tech.noetzold.Apidarlan.contato.repository.Tecnologias;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadesResource {
    @Autowired
    private Especialidades especialidades;

    @Autowired
    private Tecnologias tecnologias;

    @Autowired
    private Experiencias experiencias;

    @PostMapping
    public Especialidade adicionar(@Valid @RequestBody Especialidade especialidade) {
        especialidade.setTecnologia(tecnologias.findById(especialidade.getTecnologia().getId()).get());
        especialidade.setExperiencia(experiencias.findById(especialidade.getExperiencia().getId()).get());

        return especialidades.save(especialidade);
    }

    @GetMapping
    public List<Especialidade> listar() {
        return especialidades.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscar(@PathVariable Long id) {
        Especialidade especialidade = especialidades.getOne(id);

        if (especialidade == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(especialidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Long id,
                                                @Valid @RequestBody Especialidade especialidade) {
        Especialidade existente = especialidades.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(especialidade, existente, "id");

        existente = especialidades.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Especialidade especialidade = especialidades.getOne(id);

        if (especialidade == null) {
            return ResponseEntity.notFound().build();
        }

        especialidades.delete(especialidade);

        return ResponseEntity.noContent().build();
    }
}
