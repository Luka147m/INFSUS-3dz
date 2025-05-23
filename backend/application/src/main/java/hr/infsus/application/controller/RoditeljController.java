
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

import hr.infsus.application.dto.RoditeljRequestDTO;
import hr.infsus.application.dto.RoditeljResponseDTO;
import hr.infsus.application.service.RoditeljService;

@RestController
@RequestMapping("/api/roditelji")
@CrossOrigin(origins = "http://localhost:3000")
public class RoditeljController {
	private final RoditeljService roditeljService;

	public RoditeljController(RoditeljService roditeljService) {
		this.roditeljService = roditeljService;
	}

	// Dohvat svih id roditelja
	@GetMapping("/ids")
	public List<Integer> getAllIds() {
		return roditeljService.getAllIds();
	}

	// Dohvat svih roditelja
	@GetMapping
	public List<RoditeljResponseDTO> getRoditelji() {
		return roditeljService.getAllRoditelji();
	}

	// Dodavanje roditelja
	@PostMapping
	public ResponseEntity<String> createRoditelj(@RequestBody RoditeljRequestDTO dto) {
		String savedRoditelj = roditeljService.createRoditelj(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedRoditelj);
	}

	// Azuriranje roditelja preko ID
	@PutMapping("/roditelj/{id}")
	public ResponseEntity<String> updateDijete(@RequestBody RoditeljRequestDTO dto, @PathVariable Integer id) {
		String updatedRoditelj = roditeljService.updateRoditelj(dto, id);
		return ResponseEntity.status(HttpStatus.OK).body(updatedRoditelj);
	}

	// Brisanje pojedinog roditelja s ID ako nije povezan ni s jednim djetetom u
	// Imeniku inace ne dopustamo
	@DeleteMapping("/roditelj/{id}")
	public ResponseEntity<Void> deleteRoditelj(@PathVariable Integer id) {
		boolean deleted = roditeljService.deleteRoditelj(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
