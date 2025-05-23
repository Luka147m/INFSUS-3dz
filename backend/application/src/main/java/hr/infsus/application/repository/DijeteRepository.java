package hr.infsus.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hr.infsus.application.model.Dijete;

public interface DijeteRepository extends JpaRepository<Dijete, Integer> {
	@Query("SELECT d.idDijete FROM Dijete d")
	List<Integer> findAllIdsDijete();
}