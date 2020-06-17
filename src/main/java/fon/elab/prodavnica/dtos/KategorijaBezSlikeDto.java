package fon.elab.prodavnica.dtos;

public class KategorijaBezSlikeDto {
	Integer id;
	String naziv;
	public KategorijaBezSlikeDto() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public KategorijaBezSlikeDto(Integer id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
	
}
