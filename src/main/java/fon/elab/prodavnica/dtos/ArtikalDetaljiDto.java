package fon.elab.prodavnica.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.domain.Komentar;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.domain.Slika;

public class ArtikalDetaljiDto {
	Integer id;
	String naziv;
	String opis;
	double cena;
	String poreklo;
	String kolicina;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	KategorijaBezSlikeDto kategorija;
	boolean slanjeKuriromMoguce;
	Slika slika;
	int zaliha;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	ProdavacOsnovnoDto prodavac;
	boolean aktivan;
	int brojKomentara;
	boolean istaknut;
	List<ArtikalOsnovnoDto> slicniArtikli = new ArrayList<>();
	public ArtikalDetaljiDto() {
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
	public String getKolicina() {
		return kolicina;
	}
	public void setKolicina(String kolicina) {
		this.kolicina = kolicina;
	}

	public KategorijaBezSlikeDto getKategorija() {
		return kategorija;
	}
	public void setKategorija(KategorijaBezSlikeDto kategorija) {
		this.kategorija = kategorija;
	}
	public boolean isSlanjeKuriromMoguce() {
		return slanjeKuriromMoguce;
	}
	public void setSlanjeKuriromMoguce(boolean slanjeKuriromMoguce) {
		this.slanjeKuriromMoguce = slanjeKuriromMoguce;
	}
	public Slika getSlika() {
		return slika;
	}
	public void setSlika(Slika slika) {
		this.slika = slika;
	}
	public int getZaliha() {
		return zaliha;
	}
	public void setZaliha(int zaliha) {
		this.zaliha = zaliha;
	}
	public ProdavacOsnovnoDto getProdavac() {
		return prodavac;
	}
	public void setProdavac(ProdavacOsnovnoDto prodavac) {
		this.prodavac = prodavac;
	}
	public int getBrojKomentara() {
		return brojKomentara;
	}
	public void setBrojKomentara(int brojKomentara) {
		this.brojKomentara = brojKomentara;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public boolean isIstaknut() {
		return istaknut;
	}

	public void setIstaknut(boolean istaknut) {
		this.istaknut = istaknut;
	}

	public List<ArtikalOsnovnoDto> getSlicniArtikli() {
		return slicniArtikli;
	}

	public void setSlicniArtikli(List<ArtikalOsnovnoDto> slicniArtikli) {
		this.slicniArtikli = slicniArtikli;
	}
	
}
