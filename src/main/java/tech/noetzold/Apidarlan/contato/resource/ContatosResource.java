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

import tech.noetzold.Apidarlan.contato.model.Contato;
import tech.noetzold.Apidarlan.contato.repository.Contatos;
import tech.noetzold.Apidarlan.contato.repository.Especialidades;
import tech.noetzold.Apidarlan.contato.repository.Formacoes;

@RestController
@RequestMapping("/contatos")
public class ContatosResource {
	
	@Autowired
	private Contatos contatos;

	@Autowired
	private Formacoes formacoes;

	@Autowired
	private Especialidades especialidades;
	
	@PostMapping
	public Contato adicionar(@Valid @RequestBody Contato contato) {
		contato.setEspecialidade(especialidades.findById(contato.getId()).get());
		contato.setFormacao(formacoes.findById(contato.getFormacao().getId()).get());
		return contatos.save(contato);
	}
	
	@GetMapping
	public List<Contato> listar() {
		return contatos.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contato> buscar(@PathVariable Long id) {
		Contato contato = contatos.getOne(id);
		
		if (contato == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contato);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Contato contato) {
		Contato existente = contatos.getOne(id);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(contato, existente, "id");
		
		existente = contatos.save(existente);
		
		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Contato contato = contatos.getOne(id);
		
		if (contato == null) {
			return ResponseEntity.notFound().build();
		}
		
		contatos.delete(contato);
		
		return ResponseEntity.noContent().build();
	}
}