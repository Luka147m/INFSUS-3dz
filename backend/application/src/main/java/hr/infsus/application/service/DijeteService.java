package hr.infsus.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hr.infsus.application.dto.DijeteDTO;
import hr.infsus.application.dto.DijeteRequestDTO;
import hr.infsus.application.mapper.DijeteMapper;
import hr.infsus.application.model.Dijete;
import hr.infsus.application.model.Imenik;
import hr.infsus.application.model.Roditelj;
import hr.infsus.application.model.Skupina;
import hr.infsus.application.repository.DijeteRepository;
import hr.infsus.application.repository.ImenikRepository;
import hr.infsus.application.repository.RoditeljRepository;
import hr.infsus.application.repository.SkupinaRepository;

@Service
public class DijeteService {

	private final DijeteRepository dijeteRepository;
	private final RoditeljRepository roditeljRepository;
	private final SkupinaRepository skupinaRepository;
	private final ImenikRepository imenikRepository;

	public DijeteService(DijeteRepository dijeteRepository, RoditeljRepository roditeljRepository,
			SkupinaRepository skupinaRepository, ImenikRepository imenikRepository) {
		this.dijeteRepository = dijeteRepository;
		this.roditeljRepository = roditeljRepository;
		this.skupinaRepository = skupinaRepository;
		this.imenikRepository = imenikRepository;
	}

	public List<Integer> getAllIdsDijete() {
		return dijeteRepository.findAllIdsDijete();
	}

	public List<DijeteDTO> getAllDjeca() {
		List<Dijete> djeca = dijeteRepository.findAll();
		return djeca.stream().map(DijeteMapper::toDTO).collect(Collectors.toList());
	}

	public Optional<DijeteDTO> getDijeteById(Integer id) {
		Optional<Dijete> dijete = dijeteRepository.findById(id);
		return dijete.map(DijeteMapper::toDTO);
	}

	public String createDijete(DijeteRequestDTO dto) {
		@SuppressWarnings("unused")
		Skupina skupina = skupinaRepository.findById(dto.getIdSkupina())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skupina not found"));

		Roditelj roditelj1 = roditeljRepository.findById(dto.getIdRoditelj1())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Roditelj1 not found"));

		Roditelj roditelj2 = roditeljRepository.findById(dto.getIdRoditelj2())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Roditelj2 not found"));

		Dijete dijete = new Dijete();

		dijete.setIme(dto.getIme());
		dijete.setPrezime(dto.getPrezime());
		dijete.setOib(dto.getOib());
		dijete.setMjestoRodenja(dto.getMjestoRodenja());
		dijete.setAdresaStanovanja(dto.getAdresaStanovanja());
		dijete.setMbo(dto.getMbo());
		dijete.setDatumRodenja(dto.getDatumRodenja());
		dijete.setIdSkupina(dto.getIdSkupina());

		dijete = dijeteRepository.save(dijete);

		Imenik imenik = new Imenik();
		imenik.setDijete(dijete);
		imenik.setRoditelj1(roditelj1);
		imenik.setRoditelj2(roditelj2);

		imenik = imenikRepository.save(imenik);

		return "Success";

	}

	public String updateDijete(DijeteRequestDTO dto, Integer id) {
		Dijete dijete = dijeteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dijete not found"));

		@SuppressWarnings("unused")
		Skupina skupina = skupinaRepository.findById(dto.getIdSkupina())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Skupina not found"));

		Roditelj roditelj1 = roditeljRepository.findById(dto.getIdRoditelj1())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Roditelj1 not found"));

		Roditelj roditelj2 = roditeljRepository.findById(dto.getIdRoditelj2())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Roditelj2 not found"));

		dijete.setIme(dto.getIme());
		dijete.setPrezime(dto.getPrezime());
		dijete.setOib(dto.getOib());
		dijete.setMjestoRodenja(dto.getMjestoRodenja());
		dijete.setAdresaStanovanja(dto.getAdresaStanovanja());
		dijete.setMbo(dto.getMbo());
		dijete.setDatumRodenja(dto.getDatumRodenja());
		dijete.setIdSkupina(dto.getIdSkupina());
		dijeteRepository.save(dijete);

		Imenik imenik = imenikRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imenik not found for this Dijete"));

		imenik.setRoditelj1(roditelj1);
		imenik.setRoditelj2(roditelj2);

		imenikRepository.save(imenik);

		return "Dijete and Imenik updated successfully.";
	}

	public boolean deleteDijete(Integer id) {
		if (dijeteRepository.existsById(id)) {
			dijeteRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
