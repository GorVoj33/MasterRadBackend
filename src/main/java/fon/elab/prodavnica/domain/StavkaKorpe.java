package fon.elab.prodavnica.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class StavkaKorpe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	int kolicina;
	@ManyToOne
    @JoinColumn(name = "artikal_id") 
	Artikal artikal;
	double cenaStavke;
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "korpa_id") 
    private Korpa korpa;
	public StavkaKorpe() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}

	public double getCenaStavke() {
		return cenaStavke;
	}

	public void setCenaStavke(double cenaStavke) {
		this.cenaStavke = cenaStavke;
	}
	@Override
	public boolean equals(Object obj) {
		return this.id == ((StavkaKorpe)obj).getId();
	}

	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		this.korpa = korpa;
	}

	@Override
	public String toString() {
		return "StavkaKorpe [id=" + id + ", kolicina=" + kolicina + ", artikal id=" + artikal.getId() + ", cenaStavke="
				+ cenaStavke + ", korpa id=" + korpa.getId() + "]";
	}
	
}
