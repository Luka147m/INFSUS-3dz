package hr.infsus.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Skupina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_skupina")
	private Integer idSkupina;
	private String ime;

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

}
