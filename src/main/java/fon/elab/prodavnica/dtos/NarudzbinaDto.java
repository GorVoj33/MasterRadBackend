package fon.elab.prodavnica.dtos;

import java.util.Date;

import fon.elab.prodavnica.domain.Grad;
import fon.elab.prodavnica.domain.Korisnik;

public class NarudzbinaDto {
	Integer id;
	double ukupnaVrednost;
	boolean odobreno;
	Integer kupac;
	Date datumKreiranja;
	Date datumOdobravanja;
	String adresa;
	Grad grad;
	String alternativniKontaktTelefon;
	KorpaDto korpa;
	public NarudzbinaDto() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getUkupnaVrednost() {
		return ukupnaVrednost;
	}
	public void setUkupnaVrednost(double ukupnaVrednost) {
		this.ukupnaVrednost = ukupnaVrednost;
	}
	public boolean isOdobreno() {
		return odobreno;
	}
	public void setOdobreno(boolean odobreno) {
		this.odobreno = odobreno;
	}
	public Integer getKupac() {
		return kupac;
	}
	public void setKupac(Integer kupac) {
		this.kupac = kupac;
	}
	public Date getDatumKreiranja() {
		return datumKreiranja;
	}
	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}
	public Date getDatumOdobravanja() {
		return datumOdobravanja;
	}
	public void setDatumOdobravanja(Date datumOdobravanja) {
		this.datumOdobravanja = datumOdobravanja;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Grad getGrad() {
		return grad;
	}
	public void setGrad(Grad grad) {
		this.grad = grad;
	}
	public String getAlternativniKontaktTelefon() {
		return alternativniKontaktTelefon;
	}
	public void setAlternativniKontaktTelefon(String alternativniKontaktTelefon) {
		this.alternativniKontaktTelefon = alternativniKontaktTelefon;
	}
	public KorpaDto getKorpa() {
		return korpa;
	}
	public void setKorpa(KorpaDto korpa) {
		this.korpa = korpa;
	}
	@Override
	public String toString() {
		return "NarudzbinaDto [id=" + id + ", ukupnaVrednost=" + ukupnaVrednost + ", odobreno=" + odobreno + ", kupac="
				+ kupac + ", datumKreiranja=" + datumKreiranja + ", datumOdobravanja=" + datumOdobravanja + ", adresa="
				+ adresa + ", grad=" + grad + ", alternativniKontaktTelefon=" + alternativniKontaktTelefon + ", korpa="
				+ korpa + "]";
	}
	
	
}
