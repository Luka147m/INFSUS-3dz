package hr.infsus.application.dto;

import java.time.LocalDate;

public class DijeteDTO {
	private Integer idDijete;
	private Integer idSkupina;
	private String ime;
	private String prezime;
	private String oib;
	private String mjestoRodenja;
	private String adresaStanovanja;
	private String mbo;
	private LocalDate datumRodenja;
	private ImenikDTO imenik;

	public Integer getIdDijete() {
		return idDijete;
	}

	public void setIdDijete(Integer idDijete) {
		this.idDijete = idDijete;
	}

	public Integer getIdSkupina() {
		return idSkupina;
	}

	public void setIdSkupina(Integer idSkupina) {
		this.idSkupina = idSkupina;
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

	public String getOib() {
		return oib;
	}

	public void setOib(String oib) {
		this.oib = oib;
	}

	public String getMjestoRodenja() {
		return mjestoRodenja;
	}

	public void setMjestoRodenja(String mjestoRodenja) {
		this.mjestoRodenja = mjestoRodenja;
	}

	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}

	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}

	public String getMbo() {
		return mbo;
	}

	public void setMbo(String mbo) {
		this.mbo = mbo;
	}

	public LocalDate getDatumRodenja() {
		return datumRodenja;
	}

	public void setDatumRodenja(LocalDate datumRodenja) {
		this.datumRodenja = datumRodenja;
	}

	public ImenikDTO getImenik() {
		return imenik;
	}

	public void setImenik(ImenikDTO imenik) {
		this.imenik = imenik;
	}

}