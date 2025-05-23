package hr.infsus.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hr.infsus.application.model.Roditelj;

public interface RoditeljRepository extends JpaRepository<Roditelj, Integer> {
	@Query("SELECT r.idRoditelj FROM Roditelj r")
	List<Integer> findAllIds();
}
