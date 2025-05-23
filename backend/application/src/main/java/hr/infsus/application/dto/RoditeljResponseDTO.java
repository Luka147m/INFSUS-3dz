package hr.infsus.application.dto;

import hr.infsus.application.model.Roditelj;

public class RoditeljResponseDTO {

	private Integer idRoditelj;
	private String ime;
	private String prezime;
	private String zanimanje;
	private String radnoMjesto;
	private String oib;

	public RoditeljResponseDTO(Roditelj r) {
		this.idRoditelj = r.getIdRoditelj();
		this.ime = r.getKorisnik().getIme();
		this.prezime = r.getKorisnik().getPrezime();
		this.zanimanje = r.getZanimanje();
		this.radnoMjesto = r.getRadnoMjesto();
		this.oib = r.getOib();
	}

	public Integer getIdRoditelj() {
		return idRoditelj;
	}

	public void setIdRoditelj(Integer idRoditelj) {
		this.idRoditelj = idRoditelj;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
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

}
