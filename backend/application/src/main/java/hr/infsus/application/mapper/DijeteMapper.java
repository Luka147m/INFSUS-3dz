package hr.infsus.application.mapper;

import hr.infsus.application.dto.DijeteDTO;
import hr.infsus.application.dto.ImenikDTO;
import hr.infsus.application.dto.KorisnikDTO;
import hr.infsus.application.dto.RoditeljDTO;
import hr.infsus.application.model.Dijete;
import hr.infsus.application.model.Imenik;
import hr.infsus.application.model.Korisnik;
import hr.infsus.application.model.Roditelj;

public class DijeteMapper {

	public static DijeteDTO toDTO(Dijete d) {
		if (d == null)
			return null;

		DijeteDTO dto = new DijeteDTO();
		dto.setIdDijete(d.getIdDijete());
		dto.setIme(d.getIme());
		dto.setPrezime(d.getPrezime());
		dto.setOib(d.getOib());
		dto.setMjestoRodenja(d.getMjestoRodenja());
		dto.setAdresaStanovanja(d.getAdresaStanovanja());
		dto.setMbo(d.getMbo());
		dto.setDatumRodenja(d.getDatumRodenja());
		dto.setIdSkupina(d.getIdSkupina());

		Imenik imenik = d.getImenik();
		if (imenik != null) {
			ImenikDTO imenikDTO = new ImenikDTO();
			imenikDTO.setIdImenik(imenik.getIdImenik());
			imenikDTO.setRoditelj1(toRoditeljDTO(imenik.getRoditelj1()));
			imenikDTO.setRoditelj2(toRoditeljDTO(imenik.getRoditelj2()));
			dto.setImenik(imenikDTO);
		}

		return dto;
	}

	private static RoditeljDTO toRoditeljDTO(Roditelj r) {
		if (r == null)
			return null;

		RoditeljDTO dto = new RoditeljDTO();
		dto.setIdRoditelj(r.getIdRoditelj());
		dto.setZanimanje(r.getZanimanje());
		dto.setRadnoMjesto(r.getRadnoMjesto());
		dto.setOib(r.getOib());

		Korisnik k = r.getKorisnik();
		if (k != null) {
			KorisnikDTO kDTO = new KorisnikDTO();
			kDTO.setIme(k.getIme());
			kDTO.setPrezime(k.getPrezime());
			kDTO.setEmail(k.getEmail());
			kDTO.setBrojTelefona(k.getBrojTelefona());
			dto.setKorisnik(kDTO);
		}

		return dto;
	}

	public static Dijete toEntity(DijeteDTO dto) {
		if (dto == null)
			return null;

		Dijete dijete = new Dijete();
		dijete.setIdDijete(dto.getIdDijete());
		dijete.setIme(dto.getIme());
		dijete.setPrezime(dto.getPrezime());
		dijete.setOib(dto.getOib());
		dijete.setMjestoRodenja(dto.getMjestoRodenja());
		dijete.setAdresaStanovanja(dto.getAdresaStanovanja());
		dijete.setMbo(dto.getMbo());
		dijete.setDatumRodenja(dto.getDatumRodenja());
		dijete.setIdSkupina(dto.getIdSkupina());
		return dijete;
	}

}
