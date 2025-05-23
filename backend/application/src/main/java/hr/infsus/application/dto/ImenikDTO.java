package hr.infsus.application.dto;

public class ImenikDTO {
	private Integer idImenik;
	private RoditeljDTO roditelj1;
	private RoditeljDTO roditelj2;

	public Integer getIdImenik() {
		return idImenik;
	}

	public void setIdImenik(Integer idImenik) {
		this.idImenik = idImenik;
	}

	public RoditeljDTO getRoditelj1() {
		return roditelj1;
	}

	public void setRoditelj1(RoditeljDTO roditelj1) {
		this.roditelj1 = roditelj1;
	}

	public RoditeljDTO getRoditelj2() {
		return roditelj2;
	}

	public void setRoditelj2(RoditeljDTO roditelj2) {
		this.roditelj2 = roditelj2;
	}

}