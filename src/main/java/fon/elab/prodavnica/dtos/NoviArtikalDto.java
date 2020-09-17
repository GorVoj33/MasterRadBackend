package fon.elab.prodavnica.dtos;

import java.io.File;

import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.domain.Prodavac;

public class NoviArtikalDto {
	Integer id;
	File slika;
	String naziv;
	String opis;
	double cena;
	String poreklo;
	String kolicina;
	Kategorija kategorija;
	boolean mozeKurirom;
	Prodavac prodavac;
	int zaliha;
	
	public NoviArtikalDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public File getSlika() {
		return slika;
	}

	public void setSlika(File slika) {
		this.slika = slika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getPoreklo() {
		return poreklo;
	}

	public void setPoreklo(String poreklo) {
		this.poreklo = poreklo;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public boolean isMozeKurirom() {
		return mozeKurirom;
	}

	public void setMozeKurirom(boolean mozeKurirom) {
		this.mozeKurirom = mozeKurirom;
	}

	public int getZaliha() {
		return zaliha;
	}

	public void setZaliha(int zaliha) {
		this.zaliha = zaliha;
	}

	public String getKolicina() {
		return kolicina;
	}

	public void setKolicina(String kolicina) {
		this.kolicina = kolicina;
	}
	
	public Prodavac getProdavac() {
		return prodavac;
	}
	public void setProdavac(Prodavac prodavac) {
		this.prodavac = prodavac;
	}
}
