package fon.elab.prodavnica.dtos;

import java.util.ArrayList;
import java.util.List;

import fon.elab.prodavnica.domain.Korisnik;

public class StavkaKorpeDto {
	ProdavacOsnovnoDto prodavac;
	List<ArtikalStavka> artikli = new ArrayList<>();
	boolean moguceKurirski = true;
	String izabranaDostava;
	double ukupnaVrednost;
	public StavkaKorpeDto() {
		// TODO Auto-generated constructor stub
	}
	public ProdavacOsnovnoDto getProdavac() {
		return prodavac;
	}
	public void setProdavac(ProdavacOsnovnoDto prodavac) {
		this.prodavac = prodavac;
	}
	public List<ArtikalStavka> getArtikli() {
		return artikli;
	}
	public void setArtikli(List<ArtikalStavka> artikli) {
		this.artikli = artikli;
	}
	
	public boolean isMoguceKurirski() {
		return moguceKurirski;
	}
	public void setMoguceKurirski(boolean moguceKurirski) {
		this.moguceKurirski = moguceKurirski;
	}
	
	@Override
	public String toString() {
		return "StavkaKorpeDto [prodavac=" + prodavac + ", artikli=" + artikli + ", moguceKurirski=" + moguceKurirski
				+ ", izabranaDostava=" + izabranaDostava + ", ukupnaVrednost=" + ukupnaVrednost + "]";
	}
	public String getIzabranaDostava() {
		return izabranaDostava;
	}
	public void setIzabranaDostava(String izabranaDostava) {
		this.izabranaDostava = izabranaDostava;
	}
	public double getUkupnaVrednost() {
		return ukupnaVrednost;
	}
	public void setUkupnaVrednost(double ukupnaVrednost) {
		this.ukupnaVrednost = ukupnaVrednost;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		StavkaKorpeDto skd = (StavkaKorpeDto) obj;
		return this.getProdavac().getId() == skd.getProdavac().getId();
	}
}
