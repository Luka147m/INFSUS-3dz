package hr.infsus.application.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Dijete {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dijete")
	private Integer idDijete;

	@Column(name = "id_skupina")
	private Integer idSkupina;

	private String ime;
	private String prezime;
	private String oib;
	@Column(name = "mjesto_rodenja")
	private String mjestoRodenja;
	@Column(name = "adresa_stanovanja")
	private String adresaStanovanja;
	private String mbo;

	@Column(name = "datum_rodenja")
	private LocalDate datumRodenja;

	@OneToMany(mappedBy = "dijete", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EvidencijskaLista> evidencije;

	public List<EvidencijskaLista> getEvidencije() {
		return evidencije;
	}

	public void setEvidencije(List<EvidencijskaLista> evidencije) {
		this.evidencije = evidencije;
	}

	@OneToOne(mappedBy = "dijete", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Imenik imenik;

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

	public Imenik getImenik() {
		return imenik;
	}

	public void setImenik(Imenik imenik) {
		this.imenik = imenik;
	}

}