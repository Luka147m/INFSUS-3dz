package hr.infsus.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.infsus.application.model.Imenik;

public interface ImenikRepository extends JpaRepository<Imenik, Integer> {
	boolean existsByRoditelj1_IdRoditelj(Integer idRoditelj);

	boolean existsByRoditelj2_IdRoditelj(Integer idRoditelj);
}
