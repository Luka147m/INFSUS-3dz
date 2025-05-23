package hr.infsus.application.dto;

import java.time.LocalDate;

public class EvidencijskaListaDTO {
	private Integer idEvidencijskaLista;
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

	public Integer getIdOdgojitelj() {
		return idOdgojitelj;
	}

	public void setIdOdgojitelj(Integer idOdgojitelj) {
		this.idOdgojitelj = idOdgojitelj;
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
}
