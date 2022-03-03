package tech.noetzold.Apidarlan.contato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.Apidarlan.contato.model.Tecnologia;

public interface Tecnologias extends JpaRepository<Tecnologia, Long> {
}
