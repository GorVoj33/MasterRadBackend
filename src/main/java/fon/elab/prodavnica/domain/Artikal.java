package fon.elab.prodavnica.domain;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class Artikal  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String naziv;
	String opis;
	double cena;
	String poreklo;
	String kolicina;
	@Temporal(TemporalType.DATE)
	Date datumUnosa;
	boolean aktivan;
	boolean zaPromovisanje;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="kategorija_id")
	Kategorija kategorija;
	boolean slanjeKuriromMoguce;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "slika_id")
	Slika slika;
	int zaliha;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prodavac_id")
	Prodavac prodavac;
	
	@JsonIgnore
	@OneToMany(mappedBy = "artikal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Komentar> komentari;
	public Artikal() {
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

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public boolean isSlanjeKuriromMoguce() {
		return slanjeKuriromMoguce;
	}

	public void setSlanjeKuriromMoguce(boolean slanjeKuriromMoguce) {
		this.slanjeKuriromMoguce = slanjeKuriromMoguce;
	}

	public int getZaliha() {
		return zaliha;
	}

	public void setZaliha(int zaliha) {
		this.zaliha = zaliha;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	
	

	public Slika getSlika() {
		return slika;
	}

	public void setSlika(Slika slika) {
		this.slika = slika;
	}

	public boolean isZaPromovisanje() {
		return zaPromovisanje;
	}



	public List<Komentar> getKomentari() {
		return komentari;
	}

	public void setKomentari(List<Komentar> komentari) {
		this.komentari = komentari;
	}

	public void setZaPromovisanje(boolean zaPromovisanje) {
		this.zaPromovisanje = zaPromovisanje;
	}

	public Date getDatumUnosa() {
		return datumUnosa;
	}

	public void setDatumUnosa(Date datumKreiranja) {
		this.datumUnosa = datumKreiranja;
	}

	public Prodavac getProdavac() {
		return prodavac;
	}

	public void setProdavac(Prodavac prodavac) {
		this.prodavac = prodavac;
	}

	@Override
	public String toString() {
		return "Artikal [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", cena=" + cena + ", poreklo=" + poreklo
				+ ", kolicina=" + kolicina + ", datumUnosa=" + datumUnosa + ", aktivan=" + aktivan + ", zaPromovisanje="
				+ zaPromovisanje + ", kategorija=" + kategorija + ", slanjeKuriromMoguce=" + slanjeKuriromMoguce
				+ ", slika=" + slika + ", zaliha=" + zaliha + ", prodavac=" + prodavac + ", komentari=" + komentari
				+ "]";
	}
	
}
