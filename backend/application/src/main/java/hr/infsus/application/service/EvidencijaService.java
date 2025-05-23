package hr.infsus.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hr.infsus.application.dto.EvidencijaRequestDTO;
import hr.infsus.application.dto.EvidencijskaListaDTO;
import hr.infsus.application.model.Dijete;
import hr.infsus.application.model.EvidencijskaLista;
import hr.infsus.application.repository.DijeteRepository;
import hr.infsus.application.repository.EvidencijaRepository;

@Service
public class EvidencijaService {
	private final EvidencijaRepository evidencijaRepository;
	private final DijeteRepository dijeteRepository;

	public EvidencijaService(EvidencijaRepository evidencijaRepository, DijeteRepository dijeteRepository) {
		this.evidencijaRepository = evidencijaRepository;
		this.dijeteRepository = dijeteRepository;
	}

	public List<EvidencijskaListaDTO> getEvidencijeByDijeteId(Integer idDijete) {
		List<EvidencijskaLista> evidencije = evidencijaRepository.findAllByDijete_IdDijete(idDijete);

		return evidencije.stream().map(e -> {
			EvidencijskaListaDTO dto = new EvidencijskaListaDTO();
			dto.setIdEvidencijskaLista(e.getIdEvidencijskaLista());
			dto.setDatum(e.getDatum());
			dto.setPrisutan(e.getPrisutan());
			dto.setProgram(e.getProgram());
			dto.setNapomena(e.getNapomena());
			dto.setIdOdgojitelj(e.getIdOdgojitelj());
			return dto;
		}).collect(Collectors.toList());
	}

	public String createEvidencija(EvidencijaRequestDTO dto, Integer id) {
		Dijete dijete = dijeteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dijete not found"));

		EvidencijskaLista evidencija = new EvidencijskaLista();
		evidencija.setDijete(dijete);
		evidencija.setIdOdgojitelj(dto.getIdOdgojitelj());
		evidencija.setDatum(dto.getDatum());
		evidencija.setPrisutan(dto.getPrisutan());
		evidencija.setProgram(dto.getProgram());
		evidencija.setNapomena(dto.getNapomena());

		evidencijaRepository.save(evidencija);

		return "Evidencija uspješno kreirana";
	}

	public boolean deleteEvidencija(Integer id) {
		if (evidencijaRepository.existsById(id)) {
			evidencijaRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public String updateEvidencija(EvidencijaRequestDTO dto, Integer id) {
		EvidencijskaLista evidencija = evidencijaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evidencija not found"));

		if (dto.getIdOdgojitelj() != null) {
			evidencija.setIdOdgojitelj(dto.getIdOdgojitelj());
		}
		if (dto.getDatum() != null) {
			evidencija.setDatum(dto.getDatum());
		}
		if (dto.getPrisutan() != null) {
			evidencija.setPrisutan(dto.getPrisutan());
		}
		if (dto.getProgram() != null) {
			evidencija.setProgram(dto.getProgram());
		}
		if (dto.getNapomena() != null) {
			evidencija.setNapomena(dto.getNapomena());
		}

		evidencijaRepository.save(evidencija);

		return "Evidencija ažurirana.";
	}

	public void deleteEvidencijeByDijeteId(Integer idDijete) {
		evidencijaRepository.deleteAllByDijete_IdDijete(idDijete);
	}

}
