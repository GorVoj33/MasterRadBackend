package fon.elab.prodavnica.dtos;

import fon.elab.prodavnica.domain.Kategorija;

import java.util.List;

import fon.elab.prodavnica.domain.Artikal;
public class KategorijaSaSvimArtiklima {
	Kategorija kategorija;
	List<Artikal> artikli;
	public KategorijaSaSvimArtiklima() {
		// TODO Auto-generated constructor stub
	}
	public Kategorija getKategorija() {
		return kategorija;
	}
	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	public List<Artikal> getArtikli() {
		return artikli;
	}
	public void setArtikli(List<Artikal> artikli) {
		this.artikli = artikli;
	}
	
}
