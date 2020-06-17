package fon.elab.prodavnica.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class Prodavac extends Korisnik {
	String kontakt;
	String ulica;
	String nazivGazdinstva;
	String opis;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="grad_id")
	Grad grad;
	double minimalnaCenaNarudzbine;
	boolean direktnaIsporukaMoguca;
	boolean kurirskaIsporukaMoguca;
	@JsonIgnore
	@OneToMany(mappedBy = "prodavac", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Artikal> artikli = new ArrayList<>();
	
	public Prodavac() {
		// TODO Auto-generated constructor stub
	}

	public Prodavac(String ime,String prezime, String email, String lozinka, String kontakt, String ulica, String nazivGazdinstva, String opis, Grad grad,
			double minimalnaCenaNarudzbine, boolean direktnaIsporukaMoguca, boolean kurirskaIsporukaMoguca) {
		super(ime,prezime,email,lozinka);
		this.kontakt = kontakt;
		this.ulica = ulica;
		this.nazivGazdinstva = nazivGazdinstva;
		this.opis = opis;
		this.grad = grad;
		this.minimalnaCenaNarudzbine = minimalnaCenaNarudzbine;
		this.direktnaIsporukaMoguca = direktnaIsporukaMoguca;
		this.kurirskaIsporukaMoguca = kurirskaIsporukaMoguca;
		//this.artikli = artikli;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getNazivGazdinstva() {
		return nazivGazdinstva;
	}

	public void setNazivGazdinstva(String nazivGazdinstva) {
		this.nazivGazdinstva = nazivGazdinstva;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public double getMinimalnaCenaNarudzbine() {
		return minimalnaCenaNarudzbine;
	}

	public void setMinimalnaCenaNarudzbine(double minimalnaCenaNarudzbine) {
		this.minimalnaCenaNarudzbine = minimalnaCenaNarudzbine;
	}

	public boolean isDirektnaIsporukaMoguca() {
		return direktnaIsporukaMoguca;
	}

	public void setDirektnaIsporukaMoguca(boolean direktnaIsporukaMoguca) {
		this.direktnaIsporukaMoguca = direktnaIsporukaMoguca;
	}

	public boolean isKurirskaIsporukaMoguca() {
		return kurirskaIsporukaMoguca;
	}

	public void setKurirskaIsporukaMoguca(boolean kurirskaIsporukaMoguca) {
		this.kurirskaIsporukaMoguca = kurirskaIsporukaMoguca;
	}

	public List<Artikal> getArtikli() {
		return artikli;
	}

	public void setArtikli(List<Artikal> artikli) {
		this.artikli = artikli;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Prodavac p = (Prodavac) obj;
		return this.id == p.id;
	}
	
	
	
	//List<NacinDostave>
}
