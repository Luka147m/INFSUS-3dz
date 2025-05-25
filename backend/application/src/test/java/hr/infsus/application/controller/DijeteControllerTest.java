package hr.infsus.application.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.hamcrest.Matchers.*;

import hr.infsus.application.model.Dijete;
import hr.infsus.application.model.Imenik;
import hr.infsus.application.dto.DijeteDTO;
import hr.infsus.application.dto.DijeteRequestDTO;
import hr.infsus.application.service.DijeteService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DijeteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private DijeteService dijeteService;

	@Autowired
	private ObjectMapper objectMapper;

	private Integer testDijeteId;

	private DijeteRequestDTO dto;

	@BeforeEach
	void setup() throws Exception {
		dto = new DijeteRequestDTO();
		dto.setIme("TestIme");
		dto.setPrezime("TestPrezime");
		dto.setOib("12345678901");
		dto.setMjestoRodenja("Split");
		dto.setAdresaStanovanja("Ulica 1");
		dto.setMbo("987654321");
		dto.setDatumRodenja(LocalDate.of(2020, 5, 5));
		dto.setIdSkupina(1);
		dto.setIdRoditelj1(1);
		dto.setIdRoditelj2(2);

		String json = objectMapper.writeValueAsString(dto);

		String response = mockMvc.perform(post("/api/djeca")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())
				.andReturn()
				.getResponse()
				.getContentAsString();

		testDijeteId = Integer.valueOf(response);
	}

	@Test
	@DisplayName("GET /api/djeca - vraća listu djece")
	void getAllDjeca_whenDjecaExist_returnsListOfDjeca() throws Exception {
		mockMvc.perform(get("/api/djeca"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
				.andExpect(jsonPath("$[*].idDijete", hasItem(testDijeteId)))
				.andExpect(jsonPath("$[*].ime", hasItem(dto.getIme())))
				.andExpect(jsonPath("$[*].oib", hasItem(dto.getOib())))
				.andExpect(jsonPath("$[*].mbo", hasItem(dto.getMbo())))
				.andExpect(jsonPath("$[*].mjestoRodenja", hasItem(dto.getMjestoRodenja())));
		;
	}

	@Test
	@DisplayName("GET /api/djeca/ids - vraća listu id djece")
	void getAllIds_whenDjecaExist_returnsListOfIds() throws Exception {
		mockMvc.perform(get("/api/djeca/ids"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasItem(testDijeteId)));
	}

	@Test
	@DisplayName("POST /api/djeca - kreira dijete")
	void createDijete_withValidData_returnsCreated() throws Exception {
		DijeteRequestDTO dijete = new DijeteRequestDTO();
		dijete.setIme("NovoIme");
		dijete.setPrezime("NovoPrezime");
		dijete.setOib("12345678911");
		dijete.setMjestoRodenja("Osijek");
		dijete.setAdresaStanovanja("Nova Ulica 2");
		dijete.setMbo("123123123");
		dijete.setDatumRodenja(LocalDate.of(2021, 1, 1));
		dijete.setIdSkupina(2);
		dijete.setIdRoditelj1(1);
		dijete.setIdRoditelj2(2);

		String json = objectMapper.writeValueAsString(dijete);

		mockMvc.perform(post("/api/djeca")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("GET /api/djeca/dijete/{id} - dohvaća pojedino dijete preko id - postoji")
	void getDijeteById_whenIdExists_returnsDijeteDto() throws Exception {
		mockMvc.perform(get("/api/djeca/dijete/" + testDijeteId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.idDijete", is(testDijeteId)))
				.andExpect(jsonPath("$.ime", is("TestIme")))
				.andExpect(jsonPath("$.prezime", is("TestPrezime")));
	}

	@Test
	@DisplayName("GET /api/djeca/dijete/{id} - dohvaća pojedino dijete preko id - ne postoji")
	void getDijeteById_whenIdDoesNotExist_returnsNotFound() throws Exception {
		mockMvc.perform(get("/api/djeca/dijete/99999"))
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("PUT /api/djeca/dijete/{id} - ažurira dijete preko id")
	void updateDijete_whenValidRequest_returnsOkAndConfirmationMessage() throws Exception {
		DijeteRequestDTO dijete = new DijeteRequestDTO();
		dijete.setIme("AžuriranoIme");
		dijete.setPrezime("AžuriranoPrezime");
		dijete.setOib("12345678901");
		dijete.setMjestoRodenja("Zadar");
		dijete.setAdresaStanovanja("Ažurirana Ulica 3");
		dijete.setMbo("987654321");
		dijete.setDatumRodenja(LocalDate.of(2020, 5, 5));
		dijete.setIdSkupina(1);
		dijete.setIdRoditelj1(1);
		dijete.setIdRoditelj2(2);

		String json = objectMapper.writeValueAsString(dijete);

		mockMvc.perform(put("/api/djeca/dijete/" + testDijeteId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("updated")));

		mockMvc.perform(get("/api/djeca/dijete/" + testDijeteId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.ime").value("AžuriranoIme"))
				.andExpect(jsonPath("$.prezime").value("AžuriranoPrezime"))
				.andExpect(jsonPath("$.mjestoRodenja").value("Zadar"));

	}

	@Test
	@DisplayName("DELETE /api/djeca/dijete/{id} - briše dijete preko id - uspješno")
	void deleteDijete_whenDijeteExists_returnsNoContent() throws Exception {
		mockMvc.perform(delete("/api/djeca/dijete/" + testDijeteId))
				.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("DELETE /api/djeca/dijete/{id} - briše dijete preko id - dijete ne postoji")
	void deleteDijete_whenDijeteDoesNotExist_returnsNotfound() throws Exception {
		mockMvc.perform(delete("/api/djeca/dijete/99999"))
				.andExpect(status().isNotFound());
	}
}
