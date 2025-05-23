package hr.infsus.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.infsus.application.model.EvidencijskaLista;

public interface EvidencijaRepository extends JpaRepository<EvidencijskaLista, Integer> {
	List<EvidencijskaLista> findAllByDijete_IdDijete(Integer idDijete);

	void deleteAllByDijete_IdDijete(Integer idDijete);
}
