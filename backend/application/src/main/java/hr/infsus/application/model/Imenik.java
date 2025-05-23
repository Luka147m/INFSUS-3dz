package hr.infsus.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Imenik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idImenik;

	@OneToOne
	@MapsId
	@JoinColumn(name = "id_dijete")
	@JsonBackReference
	private Dijete dijete;

	@ManyToOne
	@JoinColumn(name = "id_roditelj1")
	private Roditelj roditelj1;

	@ManyToOne
	@JoinColumn(name = "id_roditelj2")
	private Roditelj roditelj2;

	public Integer getIdImenik() {
		return idImenik;
	}

	public void setIdImenik(Integer idImenik) {
		this.idImenik = idImenik;
	}

	public Dijete getDijete() {
		return dijete;
	}

	public void setDijete(Dijete dijete) {
		this.dijete = dijete;
	}

	public Roditelj getRoditelj1() {
		return roditelj1;
	}

	public void setRoditelj1(Roditelj roditelj1) {
		this.roditelj1 = roditelj1;
	}

	public Roditelj getRoditelj2() {
		return roditelj2;
	}

	public void setRoditelj2(Roditelj roditelj2) {
		this.roditelj2 = roditelj2;
	}

}
