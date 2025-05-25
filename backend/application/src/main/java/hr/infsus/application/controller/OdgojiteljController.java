package hr.infsus.application.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.infsus.application.dto.OdgojiteljDTO;
import hr.infsus.application.service.OdgojiteljService;

@RestController
@RequestMapping("/api/odgojitelji")
@CrossOrigin(origins = "http://localhost:3000")
public class OdgojiteljController {
	private final OdgojiteljService odgojiteljService;

	public OdgojiteljController(OdgojiteljService odgojiteljService) {
		this.odgojiteljService = odgojiteljService;
	}

	@GetMapping
	public List<OdgojiteljDTO> getOdgojitelji() {
		return odgojiteljService.getOdgojitelji();
	}
}
