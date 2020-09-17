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
			boolean moguceSlanjeKurirom, String kolicina) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
		this.slika = slika;
		this.kategorija = kategorija;
		this.moguceSlanjeKurirom = moguceSlanjeKurirom;
		this.kolicina = kolicina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtikalOsnovnoDto other = (ArtikalOsnovnoDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
