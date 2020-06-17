package fon.elab.prodavnica.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class StavkaNarudzbine {
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
    @JoinColumn(name = "narudzbenica_id") 
	Narudzbina narudzbina;
	
	public StavkaNarudzbine() {
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

	public Narudzbina getNarudzbina() {
		return narudzbina;
	}

	public void setNarudzbina(Narudzbina narudzbina) {
		this.narudzbina = narudzbina;
	}

	@Override
	public String toString() {
		return "StavkaNarudzbine [id=" + id + ", kolicina=" + kolicina + ", artikal=" + artikal + ", cenaStavke="
				+ cenaStavke + ", narudzbina=" + narudzbina + "]";
	}
	
}
