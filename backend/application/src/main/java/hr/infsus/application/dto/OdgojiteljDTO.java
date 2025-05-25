package hr.infsus.application.dto;

public class OdgojiteljDTO {
	private Integer idOdgojitelj;
	private String ime;
	private String prezime;

	public OdgojiteljDTO(Integer idOdgojitelj, String ime, String prezime) {
		this.idOdgojitelj = idOdgojitelj;
		this.ime = ime;
		this.prezime = prezime;
	}

	public Integer getIdOdgojitelj() {
		return idOdgojitelj;
	}

	public void setIdOdgojitelj(Integer idOdgojitelj) {
		this.idOdgojitelj = idOdgojitelj;
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
}
