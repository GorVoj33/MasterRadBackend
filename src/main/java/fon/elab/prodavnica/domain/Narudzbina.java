package fon.elab.prodavnica.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class Narudzbina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Date datumKreiranja;
	double ukupnaVrednost;
	String ulica;
	String napomena;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="grad_id")
	Grad grad;
	String nacinIsporuke;
	boolean odobrena;
	Date datumOdobrenja;
	Date datumOdbijanja;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="prodavac_id")
	Prodavac prodavac;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="kupac_id")
	Korisnik kupac;
	@OneToMany(mappedBy = "narudzbina", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	List<StavkaNarudzbine> stavke = new ArrayList<>();
	public Narudzbina() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDatumKreiranja() {
		return datumKreiranja;
	}
	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}
	public double getUkupnaVrednost() {
		return ukupnaVrednost;
	}
	public void setUkupnaVrednost(double ukupnaVrednost) {
		this.ukupnaVrednost = ukupnaVrednost;
	}
	public boolean isOdobrena() {
		return odobrena;
	}
	public void setOdobrena(boolean odobrena) {
		this.odobrena = odobrena;
	}
	public Date getDatumOdobrenja() {
		return datumOdobrenja;
	}
	public void setDatumOdobrenja(Date datumOdobrenja) {
		this.datumOdobrenja = datumOdobrenja;
	}
	public Prodavac getProdavac() {
		return prodavac;
	}
	public void setProdavac(Prodavac prodavac) {
		this.prodavac = prodavac;
	}
	public Korisnik getKupac() {
		return kupac;
	}
	public void setKupac(Korisnik kupac) {
		this.kupac = kupac;
	}
	public List<StavkaNarudzbine> getStavke() {
		return stavke;
	}
	public void setStavke(List<StavkaNarudzbine> stavke) {
		this.stavke = stavke;
	}
	
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public Grad getGrad() {
		return grad;
	}
	public void setGrad(Grad grad) {
		this.grad = grad;
	}
	public String getNapomena() {
		return napomena;
	}
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	
	public String getNacinIsporuke() {
		return nacinIsporuke;
	}
	public void setNacinIsporuke(String nacinIsporuke) {
		this.nacinIsporuke = nacinIsporuke;
	}
	
	public Date getDatumOdbijanja() {
		return datumOdbijanja;
	}
	public void setDatumOdbijanja(Date datumOdbijanja) {
		this.datumOdbijanja = datumOdbijanja;
	}
	@Override
	public String toString() {
		return "Narudzbina [id=" + id + ", datumKreiranja=" + datumKreiranja + ", ukupnaVrednost=" + ukupnaVrednost
				+ ", ulica=" + ulica + ", napomena=" + napomena + ", grad=" + grad + ", odobrena=" + odobrena
				+ ", datumOdobrenja=" + datumOdobrenja + ", prodavac=" + prodavac + ", kupac=" + kupac + ", stavke="
				+ stavke + "]";
	}
	
	
}
