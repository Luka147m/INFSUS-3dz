package hr.infsus.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.infsus.application.dto.DijeteDTO;
import hr.infsus.application.mapper.DijeteMapper;
import hr.infsus.application.model.Dijete;
import hr.infsus.application.repository.DijeteRepository;

@Service
public class DijeteService {

	private final DijeteRepository dijeteRepository;

	public DijeteService(DijeteRepository dijeteRepository) {
		this.dijeteRepository = dijeteRepository;
	}

	public List<DijeteDTO> getAllDjeca() {
		List<Dijete> djeca = dijeteRepository.findAll();
		return djeca.stream().map(DijeteMapper::toDTO).collect(Collectors.toList());
	}

	public Optional<DijeteDTO> getDijeteById(Integer id) {
		Optional<Dijete> dijete = dijeteRepository.findById(id);
		return dijete.map(DijeteMapper::toDTO);
	}

	public Dijete saveDijete(Dijete dijete) {
		return dijeteRepository.save(dijete);
	}

	public Optional<Dijete> updateDijete(Integer id, Dijete updatedDijete) {
		return dijeteRepository.findById(id).map(existingDijete -> {
			existingDijete.setIme(updatedDijete.getIme());
			existingDijete.setPrezime(updatedDijete.getPrezime());
			existingDijete.setOib(updatedDijete.getOib());
			existingDijete.setMjestoRodenja(updatedDijete.getMjestoRodenja());
			existingDijete.setAdresaStanovanja(updatedDijete.getAdresaStanovanja());
			existingDijete.setMbo(updatedDijete.getMbo());
			existingDijete.setDatumRodenja(updatedDijete.getDatumRodenja());
			existingDijete.setIdSkupina(updatedDijete.getIdSkupina());
			return dijeteRepository.save(existingDijete);
		});
	}

	public boolean deleteDijete(Integer id) {
		if (dijeteRepository.existsById(id)) {
			dijeteRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
