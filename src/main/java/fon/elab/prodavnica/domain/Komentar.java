package fon.elab.prodavnica.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Komentar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String poruka;
	Date datumUnosa;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="korisnik_id")
	@JsonIgnore
	Korisnik korisnik;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="artikal_id")
	Artikal artikal;
	public Komentar() {
		// TODO Auto-generated constructor stub
	}

	public Komentar(Integer id, String poruka,Korisnik korisnik, Date datumUnosa) {
		super();
		this.id = id;
		this.poruka = poruka;
		this.datumUnosa = datumUnosa;
		this.korisnik = korisnik;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

	public Date getDatumUnosa() {
		return datumUnosa;
	}

	public void setDatumUnosa(Date datumUnosa) {
		this.datumUnosa = datumUnosa;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Artikal getArtikal() {
		return artikal;
	}

	public void setArtikal(Artikal artikal) {
		this.artikal = artikal;
	}
	
	
}
