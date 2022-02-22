package tech.noetzold.Apidarlan.contato.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.Apidarlan.contato.model.Certificado;
import tech.noetzold.Apidarlan.contato.repository.Certificados;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/certificados")
public class CertificadosResource {

    @Autowired
    private Certificados certificados;

    @PostMapping
    public Certificado adicionar(@Valid @RequestBody Certificado certificado) {
        return certificados.save(certificado);
    }

    @GetMapping
    public List<Certificado> listar() {
        return certificados.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificado> buscar(@PathVariable Long id) {
        Certificado certificado = certificados.getOne(id);

        if (certificado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(certificado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificado> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody Certificado certificado) {
        Certificado existente = certificados.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(certificado, existente, "id");

        existente = certificados.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Certificado certificado = certificados.getOne(id);

        if (certificado == null) {
            return ResponseEntity.notFound().build();
        }

        certificados.delete(certificado);

        return ResponseEntity.noContent().build();
    }
}
