package hr.infsus.application.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Odgojitelj {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_odgojitelj")
	private Integer idOdgojitelj;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "id_korisnik", referencedColumnName = "id_korisnik")
	private Korisnik korisnik;

	public Integer getIdOdgojitelj() {
		return idOdgojitelj;
	}

	public void setIdOdgojitelj(Integer idOdgojitelj) {
		this.idOdgojitelj = idOdgojitelj;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}
