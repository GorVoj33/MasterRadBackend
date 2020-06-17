package fon.elab.prodavnica.domain;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Recept {
	Integer id;
	String naziv;
	String nacinPripreme;
	String vremeZaPripremu;
	int ocenaTezineSpremanja;
	Korisnik kreator;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "slika_id")
	Slika slika;
	
	
	public Recept() {
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

	public String getNacinPripreme() {
		return nacinPripreme;
	}

	public void setNacinPripreme(String nacinPripreme) {
		this.nacinPripreme = nacinPripreme;
	}

	public String getVremeZaPripremu() {
		return vremeZaPripremu;
	}

	public void setVremeZaPripremu(String vremeZaPripremu) {
		this.vremeZaPripremu = vremeZaPripremu;
	}

	public int getOcenaTezineSpremanja() {
		return ocenaTezineSpremanja;
	}

	public void setOcenaTezineSpremanja(int ocenaTezineSpremanja) {
		this.ocenaTezineSpremanja = ocenaTezineSpremanja;
	}

	public Korisnik getKreator() {
		return kreator;
	}

	public void setKreator(Korisnik kreator) {
		this.kreator = kreator;
	}

	public Slika getSlika() {
		return slika;
	}

	public void setSlika(Slika slika) {
		this.slika = slika;
	}
	
}
