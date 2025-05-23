package hr.infsus.application.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.infsus.application.dto.EvidencijskaListaDTO;
import hr.infsus.application.service.EvidencijaService;

@RestController
@RequestMapping("/api/evidencija")
@CrossOrigin(origins = "http://localhost:3000")
public class EvidencijaController {
	private final EvidencijaService evidencijaService;

	public EvidencijaController(EvidencijaService evidencijaService) {
		this.evidencijaService = evidencijaService;
	}

	@GetMapping("/dijete/{id}")
	public ResponseEntity<List<EvidencijskaListaDTO>> getEvidencijeByDijeteId(@PathVariable Integer id) {
		List<EvidencijskaListaDTO> evidencije = evidencijaService.getEvidencijeByDijeteId(id);
		if (evidencije.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(evidencije);
	}

	@DeleteMapping("/dijete/{id}")
	public ResponseEntity<Void> deleteEvidencijeByDijeteId(@PathVariable Integer id) {
		evidencijaService.deleteEvidencijeByDijeteId(id);
		return ResponseEntity.noContent().build();
	}

}
