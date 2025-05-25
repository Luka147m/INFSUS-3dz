package hr.infsus.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;

import hr.infsus.application.dto.DijeteDTO;
import hr.infsus.application.dto.DijeteRequestDTO;
import hr.infsus.application.mapper.DijeteMapper;
import hr.infsus.application.model.Dijete;
import hr.infsus.application.model.Imenik;
import hr.infsus.application.model.Roditelj;
import hr.infsus.application.model.Skupina;
import hr.infsus.application.repository.DijeteRepository;
import hr.infsus.application.repository.ImenikRepository;
import hr.infsus.application.repository.RoditeljRepository;
import hr.infsus.application.repository.SkupinaRepository;

class DijeteServiceTest {

    @Mock
    private DijeteRepository dijeteRepository;
    @Mock
    private RoditeljRepository roditeljRepository;
    @Mock
    private SkupinaRepository skupinaRepository;
    @Mock
    private ImenikRepository imenikRepository;

    @InjectMocks
    private DijeteService dijeteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllIdsDijete() {
        when(dijeteRepository.findAllIdsDijete()).thenReturn(Arrays.asList(1, 2, 3));
        List<Integer> ids = dijeteService.getAllIdsDijete();
        assertEquals(3, ids.size());
        verify(dijeteRepository).findAllIdsDijete();
    }

    @Test
    void testGetAllDjeca() {
        Dijete dijete1 = new Dijete();
        dijete1.setIme("Ivan");
        Dijete dijete2 = new Dijete();
        dijete2.setIme("Ana");
        when(dijeteRepository.findAll()).thenReturn(Arrays.asList(dijete1, dijete2));

        List<DijeteDTO> result = dijeteService.getAllDjeca();

        assertEquals(2, result.size());
        assertEquals("Ivan", result.get(0).getIme());
        assertEquals("Ana", result.get(1).getIme());
        verify(dijeteRepository).findAll();
    }

    @Test
    void testGetDijeteById_Found() {
        Dijete dijete = new Dijete();
        dijete.setIdDijete(1);
        dijete.setIme("Marko");
        when(dijeteRepository.findById(1)).thenReturn(Optional.of(dijete));

        Optional<DijeteDTO> dto = dijeteService.getDijeteById(1);

        assertTrue(dto.isPresent());
        assertEquals("Marko", dto.get().getIme());
        verify(dijeteRepository).findById(1);
    }

    @Test
    void testGetDijeteById_NotFound() {
        when(dijeteRepository.findById(99)).thenReturn(Optional.empty());
        Optional<DijeteDTO> dto = dijeteService.getDijeteById(99);
        assertFalse(dto.isPresent());
    }

    @Test
    void testCreateDijete_Success() {
        DijeteRequestDTO request = new DijeteRequestDTO();
        request.setIme("Petar");
        request.setPrezime("Perić");
        request.setOib("12345678901");
        request.setMjestoRodenja("Zagreb");
        request.setAdresaStanovanja("Ulica 1");
        request.setMbo("MBO123");
        request.setDatumRodenja(LocalDate.of(2010, 1, 1));
        request.setIdSkupina(1);
        request.setIdRoditelj1(1);
        request.setIdRoditelj2(2);

        Skupina skupina = new Skupina();
        skupina.setIdSkupina(1);

        Roditelj roditelj1 = new Roditelj();
        roditelj1.setIdRoditelj(1);

        Roditelj roditelj2 = new Roditelj();
        roditelj2.setIdRoditelj(2);

        Dijete savedDijete = new Dijete();
        savedDijete.setIdDijete(10);

        when(skupinaRepository.findById(1)).thenReturn(Optional.of(skupina));
        when(roditeljRepository.findById(1)).thenReturn(Optional.of(roditelj1));
        when(roditeljRepository.findById(2)).thenReturn(Optional.of(roditelj2));
        when(dijeteRepository.save(any(Dijete.class))).thenReturn(savedDijete);
        when(imenikRepository.save(any(Imenik.class))).thenAnswer(i -> i.getArgument(0));

        String result = dijeteService.createDijete(request);
        assertEquals("Success", result);

        verify(dijeteRepository).save(any(Dijete.class));
        verify(imenikRepository).save(any(Imenik.class));
    }

    @Test
    void testCreateDijete_SkupinaNotFound() {
        DijeteRequestDTO request = new DijeteRequestDTO();
        request.setIdSkupina(99);
        when(skupinaRepository.findById(99)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> dijeteService.createDijete(request));
        assertEquals("404 NOT_FOUND \"Skupina not found\"", ex.getMessage());
    }

    @Test
    void testUpdateDijete_Success() {
        Integer id = 5;
        DijeteRequestDTO request = new DijeteRequestDTO();
        request.setIme("Luka");
        request.setPrezime("Lukić");
        request.setOib("98765432109");
        request.setMjestoRodenja("Split");
        request.setAdresaStanovanja("Adresa 2");
        request.setMbo("MBO456");
        request.setDatumRodenja(LocalDate.of(2012, 6, 15));
        request.setIdSkupina(1);
        request.setIdRoditelj1(1);
        request.setIdRoditelj2(2);

        Dijete existingDijete = new Dijete();
        existingDijete.setIdDijete(id);

        Skupina skupina = new Skupina();
        skupina.setIdSkupina(1);

        Roditelj roditelj1 = new Roditelj();
        roditelj1.setIdRoditelj(1);

        Roditelj roditelj2 = new Roditelj();
        roditelj2.setIdRoditelj(2);

        Imenik imenik = new Imenik();

        when(dijeteRepository.findById(id)).thenReturn(Optional.of(existingDijete));
        when(skupinaRepository.findById(1)).thenReturn(Optional.of(skupina));
        when(roditeljRepository.findById(1)).thenReturn(Optional.of(roditelj1));
        when(roditeljRepository.findById(2)).thenReturn(Optional.of(roditelj2));
        when(imenikRepository.findById(id)).thenReturn(Optional.of(imenik));

        String result = dijeteService.updateDijete(request, id);

        assertEquals("Dijete and Imenik updated successfully.", result);
        verify(dijeteRepository).save(existingDijete);
        verify(imenikRepository).save(imenik);
    }

    @Test
    void testUpdateDijete_DijeteNotFound() {
        when(dijeteRepository.findById(10)).thenReturn(Optional.empty());
        DijeteRequestDTO request = new DijeteRequestDTO();
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> dijeteService.updateDijete(request, 10));
        assertEquals("404 NOT_FOUND \"Dijete not found\"", ex.getMessage());
    }

    @Test
    void testDeleteDijete_Exists() {
        Integer id = 3;
        when(dijeteRepository.existsById(id)).thenReturn(true);
        doNothing().when(dijeteRepository).deleteById(id);

        boolean deleted = dijeteService.deleteDijete(id);
        assertTrue(deleted);
        verify(dijeteRepository).deleteById(id);
    }

    @Test
    void testDeleteDijete_NotExists() {
        Integer id = 4;
        when(dijeteRepository.existsById(id)).thenReturn(false);
        boolean deleted = dijeteService.deleteDijete(id);
        assertFalse(deleted);
        verify(dijeteRepository, never()).deleteById(any());
    }
}
