package hr.infsus.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hr.infsus.application.dto.RoditeljRequestDTO;
import hr.infsus.application.dto.RoditeljResponseDTO;
import hr.infsus.application.model.Korisnik;
import hr.infsus.application.model.Roditelj;
import hr.infsus.application.repository.ImenikRepository;
import hr.infsus.application.repository.KorisnikRepository;
import hr.infsus.application.repository.RoditeljRepository;

@Service
public class RoditeljService {

	private final RoditeljRepository roditeljRepository;
	private final ImenikRepository imenikRepository;
	private final KorisnikRepository korisnikRepository;

	public RoditeljService(RoditeljRepository roditeljRepository, ImenikRepository imenikRepository,
			KorisnikRepository korisnikRepository) {
		this.roditeljRepository = roditeljRepository;
		this.imenikRepository = imenikRepository;
		this.korisnikRepository = korisnikRepository;
	}

	public List<Integer> getAllIds() {
		return roditeljRepository.findAllIds();
	}

	public List<RoditeljResponseDTO> getAllRoditelji() {
		List<Roditelj> roditelji = roditeljRepository.findAll();
		return roditelji.stream().map(RoditeljResponseDTO::new).collect(Collectors.toList());
	}

	public String createRoditelj(RoditeljRequestDTO dto) {
		Korisnik korisnik = new Korisnik();
		korisnik.setIme(dto.getIme());
		korisnik.setPrezime(dto.getPrezime());
		korisnik.setEmail(dto.getEmail());
		korisnik.setBrojTelefona(dto.getBrojTelefona());
		korisnik.setLozinka(dto.getLozinka());
		korisnik.setUloga(dto.getUloga());

		korisnik = korisnikRepository.save(korisnik);

		Roditelj roditelj = new Roditelj();
		roditelj.setKorisnik(korisnik);
		roditelj.setZanimanje(dto.getZanimanje());
		roditelj.setRadnoMjesto(dto.getRadnoMjesto());
		roditelj.setOib(dto.getOib());

		roditeljRepository.save(roditelj);

		return "Roditelj i korisnik uspješno kreirani s ID: " + roditelj.getIdRoditelj();
	}

	public String updateRoditelj(RoditeljRequestDTO dto, Integer id) {
		Roditelj roditelj = roditeljRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Roditelj not found"));

		Korisnik korisnik = roditelj.getKorisnik();
		korisnik.setIme(dto.getIme());
		korisnik.setPrezime(dto.getPrezime());
		korisnik.setEmail(dto.getEmail());
		korisnik.setBrojTelefona(dto.getBrojTelefona());
		korisnik.setLozinka(dto.getLozinka());
		korisnik.setUloga(dto.getUloga());

		korisnikRepository.save(korisnik);

		roditelj.setZanimanje(dto.getZanimanje());
		roditelj.setRadnoMjesto(dto.getRadnoMjesto());
		roditelj.setOib(dto.getOib());

		roditeljRepository.save(roditelj);

		return "Roditelj i korisnik uspješno ažurirani.";
	}

	public boolean deleteRoditelj(Integer idRoditelj) {
		boolean isUsedInImenik = imenikRepository.existsByRoditelj1_IdRoditelj(idRoditelj)
				|| imenikRepository.existsByRoditelj2_IdRoditelj(idRoditelj);

		if (isUsedInImenik) {
			return false;
		}

		Roditelj roditelj = roditeljRepository.findById(idRoditelj)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Roditelj not found"));

		roditeljRepository.delete(roditelj);

		return true;
	}
}
