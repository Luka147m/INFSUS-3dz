package hr.infsus.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.infsus.application.dto.EvidencijskaListaDTO;
import hr.infsus.application.model.EvidencijskaLista;
import hr.infsus.application.repository.EvidencijaRepository;

@Service
public class EvidencijaService {
	private final EvidencijaRepository evidencijaRepository;

	public EvidencijaService(EvidencijaRepository evidencijaRepository) {
		this.evidencijaRepository = evidencijaRepository;
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

	public void deleteEvidencijeByDijeteId(Integer idDijete) {
		evidencijaRepository.deleteAllByDijete_IdDijete(idDijete);
	}

}
