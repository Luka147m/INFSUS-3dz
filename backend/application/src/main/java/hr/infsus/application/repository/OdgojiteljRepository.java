package hr.infsus.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.infsus.application.model.Odgojitelj;

public interface OdgojiteljRepository extends JpaRepository<Odgojitelj, Integer> {
}
