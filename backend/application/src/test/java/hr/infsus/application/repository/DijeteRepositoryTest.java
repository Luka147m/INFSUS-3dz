package hr.infsus.application.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import hr.infsus.application.model.Dijete;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DijeteRepositoryTest {
	@Autowired
	private DijeteRepository dijeteRepository;

	@Test
	@DisplayName("Spremanje i pronalaženje djeteta po ID")
	@Rollback
	void testSaveAndFindById() {
		Dijete dijete = new Dijete();
		dijete.setIme("TestIme");
		dijete.setPrezime("TestPrezime");
		dijete.setOib("12345678901");
		dijete.setMjestoRodenja("TestMjesto");
		dijete.setAdresaStanovanja("TestAdresa");
		dijete.setMbo("987654321");
		dijete.setDatumRodenja(LocalDate.of(2020, 1, 1));
		dijete.setIdSkupina(1);

		Dijete saved = dijeteRepository.save(dijete);
		assertThat(saved.getIdDijete()).isNotNull();

		Dijete found = dijeteRepository.findById(saved.getIdDijete()).orElse(null);
		assertThat(found).isNotNull();
		assertThat(found.getIme()).isEqualTo("TestIme");
	}

	@Test
	@DisplayName("Vrati sve ID djece i provjeri sadrži li novi ID")
	@Rollback
	void testFindAllIdsDijete() {
		Dijete dijete = new Dijete();
		dijete.setIme("TestIme2");
		dijete.setPrezime("TestPrezime2");
		dijete.setOib("12345678902");
		dijete.setMjestoRodenja("TestMjesto2");
		dijete.setAdresaStanovanja("TestAdresa2");
		dijete.setMbo("987654322");
		dijete.setDatumRodenja(LocalDate.of(2021, 2, 2));
		dijete.setIdSkupina(2);

		Dijete saved = dijeteRepository.save(dijete);
		Integer savedId = saved.getIdDijete();
		List<Integer> ids = dijeteRepository.findAllIdsDijete();
		assertThat(ids).contains(savedId);
	}
}
