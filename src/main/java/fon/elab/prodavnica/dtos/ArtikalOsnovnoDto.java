package fon.elab.prodavnica.dtos;

import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.domain.Slika;
import util.Utility;

public class ArtikalOsnovnoDto {
	Integer id;
	String naziv;
	double cena;
	Slika slika;
	Kategorija kategorija;
	boolean moguceSlanjeKurirom;
	String kolicina;
	
	public ArtikalOsnovnoDto() {
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

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Slika getSlika() {
		slika = new Slika(slika.getNaziv(), slika.getTip(), Utility.getInstance().decompressBytes(slika.getPicByte()), "artikal");
		return slika;
	}

	public void setSlika(Slika slika) {
		this.slika = slika;
	}

	public boolean isMoguceSlanjeKurirom() {
		return moguceSlanjeKurirom;
	}

	public void setMoguceSlanjeKurirom(boolean moguceSlanjeKurirom) {
		this.moguceSlanjeKurirom = moguceSlanjeKurirom;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public String getKolicina() {
		return kolicina;
	}

	public void setKolicina(String kolicina) {
		this.kolicina = kolicina;
	}

	public ArtikalOsnovnoDto(Integer id, String naziv, double cena, Slika slika, Kategorija kategorija,
			boolean moguceSlanjeKurirom) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
		this.slika = slika;
		this.kategorija = kategorija;
		this.moguceSlanjeKurirom = moguceSlanjeKurirom;
	}
	
}
