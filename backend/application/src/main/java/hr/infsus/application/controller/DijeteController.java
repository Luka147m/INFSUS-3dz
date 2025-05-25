package hr.infsus.application.controller;

import java.util.List;
import java.util.Optional;

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

import hr.infsus.application.dto.DijeteDTO;
import hr.infsus.application.dto.DijeteRequestDTO;
import hr.infsus.application.service.DijeteService;

@RestController
@RequestMapping("/api/djeca")
@CrossOrigin(origins = "http://localhost:3000")
public class DijeteController {
	private final DijeteService dijeteService;

	public DijeteController(DijeteService dijeteService) {
		this.dijeteService = dijeteService;
	}

	// Sva djeca
	@GetMapping
	public ResponseEntity<List<DijeteDTO>> getAllDjeca() {
		List<DijeteDTO> djecaDTO = dijeteService.getAllDjeca();
		return ResponseEntity.ok(djecaDTO);
	}

	// Svi ID djece
	@GetMapping("/ids")
	public List<Integer> getAllIds() {
		return dijeteService.getAllIdsDijete();
	}

	@PostMapping
	public ResponseEntity<String> createDijete(@RequestBody DijeteRequestDTO dto) {
		String savedDijete = dijeteService.createDijete(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedDijete);
	}

	// Dohvat pojedinog djeteta preko ID
	@GetMapping("/dijete/{id}")
	public ResponseEntity<DijeteDTO> getDijeteById(@PathVariable Integer id) {
		Optional<DijeteDTO> dijeteDTO = dijeteService.getDijeteById(id);
		return dijeteDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Azuriranje djeteta preko ID
	@PutMapping("/dijete/{id}")
	public ResponseEntity<String> updateDijete(@RequestBody DijeteRequestDTO dto, @PathVariable Integer id) {
		String updatedDijete = dijeteService.updateDijete(dto, id);
		return ResponseEntity.status(HttpStatus.OK).body(updatedDijete);
	}

	// Brisanje djeteta preko ID
	@DeleteMapping("/dijete/{id}")
	public ResponseEntity<Void> deleteDijete(@PathVariable Integer id) {
		boolean deleted = dijeteService.deleteDijete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}