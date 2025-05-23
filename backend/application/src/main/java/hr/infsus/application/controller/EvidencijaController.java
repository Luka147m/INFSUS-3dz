package hr.infsus.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.infsus.application.dto.EvidencijaRequestDTO;
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

	// Dohvat svih evidencija za pojedino dijete ID
	@GetMapping("/dijete/{id}")
	public ResponseEntity<List<EvidencijskaListaDTO>> getEvidencijeByDijeteId(@PathVariable Integer id) {
		List<EvidencijskaListaDTO> evidencije = evidencijaService.getEvidencijeByDijeteId(id);
		if (evidencije.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(evidencije);
	}

	// Brisanje svih evidencija za pojedino dijete
	@DeleteMapping("/dijete/{id}")
	public ResponseEntity<Void> deleteEvidencijeByDijeteId(@PathVariable Integer id) {
		evidencijaService.deleteEvidencijeByDijeteId(id);
		return ResponseEntity.noContent().build();
	}

	// Create pojedine evidencije
	@PostMapping("/dijete/{id}")
	public ResponseEntity<String> createDijete(@RequestBody EvidencijaRequestDTO dto, @PathVariable Integer id) {
		String savedEvidencija = evidencijaService.createEvidencija(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEvidencija);
	}

	// Delete pojedine evidencije idevidencije
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvidencija(@PathVariable Integer id) {
		boolean deleted = evidencijaService.deleteEvidencija(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

	// Update ako postoji
	@PutMapping("/{id}")
	public ResponseEntity<String> updateEvidencija(@RequestBody EvidencijaRequestDTO dto, @PathVariable Integer id) {
		String updated = evidencijaService.updateEvidencija(dto, id);
		return ResponseEntity.ok(updated);
	}

}
