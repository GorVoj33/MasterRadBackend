package fon.elab.prodavnica.dtos;

public class NovaStavkaKorpeDto {
	int artikalId;
	int korpaId;
	int kolicina;
	public NovaStavkaKorpeDto() {
		// TODO Auto-generated constructor stub
	}
	public int getArtikalId() {
		return artikalId;
	}
	public void setArtikalId(int artikalId) {
		this.artikalId = artikalId;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public int getKorpaId() {
		return korpaId;
	}
	public void setKorpaId(int korpaId) {
		this.korpaId = korpaId;
	}
	
}
