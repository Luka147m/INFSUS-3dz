package hr.infsus.application.integration;

import hr.infsus.application.dto.DijeteRequestDTO;
import hr.infsus.application.model.Dijete;
import hr.infsus.application.repository.DijeteRepository;
import hr.infsus.application.service.DijeteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class IntegrationTest {

    @Autowired
    private DijeteService dijeteService;

    @Autowired
    private DijeteRepository dijeteRepository;

    @Test
    void createAndFindDijeteIntegration() {
        DijeteRequestDTO dto = new DijeteRequestDTO();
        dto.setIme("Integracija");
        dto.setPrezime("Test");
        dto.setOib("12345678999");
        dto.setMjestoRodenja("Zagreb");
        dto.setAdresaStanovanja("Integracijska 1");
        dto.setMbo("MBO999");
        dto.setDatumRodenja(LocalDate.of(2015, 5, 5));
        dto.setIdSkupina(1);
        dto.setIdRoditelj1(1);
        dto.setIdRoditelj2(2);

        Integer id = dijeteService.createDijete(dto);
        Optional<Dijete> found = dijeteRepository.findById(id);

        assertThat(found).isPresent();
        assertThat(found.get().getIme()).isEqualTo("Integracija");
    }
}
