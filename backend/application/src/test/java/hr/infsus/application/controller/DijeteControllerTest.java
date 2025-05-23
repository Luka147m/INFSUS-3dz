//package hr.infsus.application.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import hr.infsus.application.model.Dijete;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class DijeteControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Test
//	void testCRUDDijete() throws Exception {
//		// Create
//		Dijete dijete = new Dijete();
//		dijete.setIme("Ana");
//		dijete.setPrezime("Anić");
//		dijete.setOib("12345678901");
//		dijete.setMjestoRodenja("Split");
//		dijete.setAdresaStanovanja("Ulica 1");
//		dijete.setMbo("987654321");
//		dijete.setDatumRodenja(LocalDate.of(2015, 5, 5));
//		dijete.setIdSkupina(1);
//
//		String json = objectMapper.writeValueAsString(dijete);
//
//		String response = mockMvc
//				.perform(post("/api/djeca/dijete").contentType(MediaType.APPLICATION_JSON).content(json))
//				.andExpect(status().isOk()).andExpect(jsonPath("$.ime").value("Ana")).andReturn().getResponse()
//				.getContentAsString();
//
//		// Extract ID
//		Dijete created = objectMapper.readValue(response, Dijete.class);
//		Integer id = created.getIdDijete();
//
//		// Read
//		mockMvc.perform(get("/api/djeca/dijete/" + id)).andExpect(status().isOk())
//				.andExpect(jsonPath("$.ime").value("Ana"));
//
//		// Update
//		created.setPrezime("Novaić");
//		mockMvc.perform(put("/api/djeca/dijete/" + id).contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(created))).andExpect(status().isOk())
//				.andExpect(jsonPath("$.prezime").value("Novaić"));
//
//		// Delete
//		mockMvc.perform(delete("/api/djeca/dijete/" + id)).andExpect(status().isNoContent());
//
//		mockMvc.perform(get("/api/djeca/dijete/" + id)).andExpect(status().isNotFound());
//	}
//
//}
