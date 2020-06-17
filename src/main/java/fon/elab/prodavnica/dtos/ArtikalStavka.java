package fon.elab.prodavnica.dtos;

public class ArtikalStavka {
	ArtikalOsnovnoDto artikal;
	int kolicina;
	double vrednost;
	int stavkaId;
	public ArtikalStavka() {
		// TODO Auto-generated constructor stub
	}

	public ArtikalOsnovnoDto getArtikal() {
		return artikal;
	}

	public void setArtikal(ArtikalOsnovnoDto artikal) {
		this.artikal = artikal;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getVrednost() {
		return vrednost;
	}

	public void setVrednost(double vrednost) {
		this.vrednost = vrednost;
	}

	public int getStavkaId() {
		return stavkaId;
	}

	public void setStavkaId(int stavkaId) {
		this.stavkaId = stavkaId;
	}
	
}
