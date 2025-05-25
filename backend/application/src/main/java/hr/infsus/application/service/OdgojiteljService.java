package hr.infsus.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.infsus.application.dto.OdgojiteljDTO;
import hr.infsus.application.model.Odgojitelj;
import hr.infsus.application.repository.OdgojiteljRepository;

@Service
public class OdgojiteljService {
	private final OdgojiteljRepository odgojiteljRepository;

	public OdgojiteljService(OdgojiteljRepository odgojiteljRepository) {
		this.odgojiteljRepository = odgojiteljRepository;
	}

	public List<OdgojiteljDTO> getOdgojitelji() {
		List<Odgojitelj> odgojitelji = odgojiteljRepository.findAll();

		return odgojitelji.stream().map(
				o -> new OdgojiteljDTO(o.getIdOdgojitelj(), o.getKorisnik().getIme(), o.getKorisnik().getPrezime()))
				.collect(Collectors.toList());
	}
}
