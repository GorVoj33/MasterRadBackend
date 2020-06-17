package fon.elab.prodavnica.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Korpa {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
	Korisnik korisnik;
	double ukupnaVrednost;
	int brojStavki;

	@OneToMany(mappedBy = "korpa", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<StavkaKorpe> stavke=new ArrayList<>();
	
	public Korpa() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
//		if(korisnik != null)
//			korisnik.setKorpa(this);
		this.korisnik = korisnik;
	}
	public double getUkupnaVrednost() {
		return ukupnaVrednost;
	}
	public void setUkupnaVrednost(double ukupnaVrednost) {
		this.ukupnaVrednost = ukupnaVrednost;
	}
	public int getBrojStavki() {
		return brojStavki;
	}
	public void setBrojStavki(int brojStavki) {
		this.brojStavki = brojStavki;
	}
	public List<StavkaKorpe> getStavke() {
		return stavke;
	}
	public void setStavke(List<StavkaKorpe> stavke) {
		this.stavke = stavke;
	}
	@Override
	public String toString() {
		return "Korpa [id=" + id + ", korisnik=" + korisnik + ", ukupnaVrednost=" + ukupnaVrednost + ", brojStavki="
				+ brojStavki + ", stavke=" + stavke + "]";
	}

	
}
