package tech.noetzold.Apidarlan.contato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.Apidarlan.contato.model.Ferramenta;

public interface Ferramentas extends JpaRepository<Ferramenta, Long> {
}
