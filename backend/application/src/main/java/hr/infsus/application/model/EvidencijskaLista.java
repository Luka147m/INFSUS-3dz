package hr.infsus.application.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "evidencijskalista")
public class EvidencijskaLista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEvidencijskaLista;

	@ManyToOne
	@JoinColumn(name = "id_dijete")
	private Dijete dijete;

	@Column(name = "id_odgojitelj")
	private Integer idOdgojitelj;
	private LocalDate datum;
	private Boolean prisutan;
	private String program;
	private String napomena;

	public Integer getIdEvidencijskaLista() {
		return idEvidencijskaLista;
	}

	public void setIdEvidencijskaLista(Integer idEvidencijskaLista) {
		this.idEvidencijskaLista = idEvidencijskaLista;
	}

	public Dijete getDijete() {
		return dijete;
	}

	public void setDijete(Dijete dijete) {
		this.dijete = dijete;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Boolean getPrisutan() {
		return prisutan;
	}

	public void setPrisutan(Boolean prisutan) {
		this.prisutan = prisutan;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public Integer getIdOdgojitelj() {
		return idOdgojitelj;
	}

	public void setIdOdgojitelj(Integer idOdgojitelj) {
		this.idOdgojitelj = idOdgojitelj;
	}
}
