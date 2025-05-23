package hr.infsus.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.infsus.application.dto.DijeteDTO;
import hr.infsus.application.service.DijeteService;

@RestController
@RequestMapping("/api/djeca")
@CrossOrigin(origins = "http://localhost:3000")
public class DijeteController {
	private final DijeteService dijeteService;

	public DijeteController(DijeteService dijeteService) {
		this.dijeteService = dijeteService;
	}

	@GetMapping
	public ResponseEntity<List<DijeteDTO>> getAllDjeca() {
		List<DijeteDTO> djecaDTO = dijeteService.getAllDjeca();
		return ResponseEntity.ok(djecaDTO);
	}

//	@PostMapping("/dijete")
//	public ResponseEntity<DijeteDTO> createDijete(@RequestBody DijeteDTO novoDijeteDTO) {
//		Dijete dijeteEntity = DijeteMapper.toEntity(novoDijeteDTO);
//		Dijete spremljenoDijete = dijeteService.saveDijete(dijeteEntity, novoDijeteDTO.getRoditeljIds());
//		DijeteDTO resultDTO = DijeteMapper.toDTO(spremljenoDijete);
//		return ResponseEntity.ok(resultDTO);
//	}

	@GetMapping("/dijete/{id}")
	public ResponseEntity<DijeteDTO> getDijeteById(@PathVariable Integer id) {
		Optional<DijeteDTO> dijeteDTO = dijeteService.getDijeteById(id);
		return dijeteDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

//	@PutMapping("/dijete/{id}")
//	public ResponseEntity<DijeteDTO> updateDijete(@PathVariable Integer id, @RequestBody DijeteDTO updatedDijeteDTO) {
//		Dijete updatedEntity = DijeteMapper.toEntity(updatedDijeteDTO);
//		Optional<Dijete> updated = dijeteService.updateDijete(id, updatedEntity, updatedDijeteDTO.getRoditeljIds());
//		return updated.map(d -> ResponseEntity.ok(DijeteMapper.toDTO(d)))
//				.orElseGet(() -> ResponseEntity.notFound().build());
//	}

	@DeleteMapping("/dijete/{id}")
	public ResponseEntity<Void> deleteDijete(@PathVariable Integer id) {
		boolean deleted = dijeteService.deleteDijete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
