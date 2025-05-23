package hr.infsus.application.dto;

public class RoditeljDTO {
	private Integer idRoditelj;
	private String zanimanje;
	private String radnoMjesto;
	private String oib;
	private KorisnikDTO korisnik;

	public Integer getIdRoditelj() {
		return idRoditelj;
	}

	public void setIdRoditelj(Integer idRoditelj) {
		this.idRoditelj = idRoditelj;
	}

	public String getZanimanje() {
		return zanimanje;
	}

	public void setZanimanje(String zanimanje) {
		this.zanimanje = zanimanje;
	}

	public String getRadnoMjesto() {
		return radnoMjesto;
	}

	public void setRadnoMjesto(String radnoMjesto) {
		this.radnoMjesto = radnoMjesto;
	}

	public String getOib() {
		return oib;
	}

	public void setOib(String oib) {
		this.oib = oib;
	}

	public KorisnikDTO getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}

}
