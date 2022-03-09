package tech.noetzold.Apidarlan.contato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.noetzold.Apidarlan.contato.model.Contato;

public interface Contatos extends JpaRepository<Contato, Long> {

}
