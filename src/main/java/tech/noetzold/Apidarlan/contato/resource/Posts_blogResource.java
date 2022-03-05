package tech.noetzold.Apidarlan.contato.resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.Apidarlan.contato.model.Post_blog;
import tech.noetzold.Apidarlan.contato.repository.Especialidades;
import tech.noetzold.Apidarlan.contato.repository.Posts_blog;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts_blog")
public class Posts_blogResource {
    @Autowired
    private Posts_blog posts_blog;

    @Autowired
    private Especialidades especialidades;

    @PostMapping
    public Post_blog adicionar(@Valid @RequestBody Post_blog post_blog) {
        post_blog.setEspecialidade(especialidades.findById(post_blog.getEspecialidade().getId()).get());
        return posts_blog.save(post_blog);
    }

    @GetMapping
    public List<Post_blog> listar() {
        return posts_blog.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post_blog> buscar(@PathVariable Long id) {
        Post_blog post_blog = posts_blog.getOne(id);

        if (post_blog == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(post_blog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post_blog> atualizar(@PathVariable Long id,
                                             @Valid @RequestBody Post_blog post_blog) {
        Post_blog existente = posts_blog.getOne(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(post_blog, existente, "id");

        existente = posts_blog.save(existente);

        return ResponseEntity.ok(existente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Post_blog post_blog = posts_blog.getOne(id);

        if (post_blog == null) {
            return ResponseEntity.notFound().build();
        }

        posts_blog.delete(post_blog);

        return ResponseEntity.noContent().build();
    }
}
