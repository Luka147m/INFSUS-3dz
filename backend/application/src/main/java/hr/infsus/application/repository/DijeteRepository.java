package hr.infsus.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.infsus.application.model.Dijete;

public interface DijeteRepository extends JpaRepository<Dijete, Integer> {
}