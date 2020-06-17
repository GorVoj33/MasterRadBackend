package fon.elab.prodavnica.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import util.Utility;

@Entity
public class Kategorija{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String naziv;
	int brojArtikala;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "slika_id")
	Slika slika;
	public Kategorija() {
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

	public Slika getSlika() {
//		Slika img = new Slika(
//					slika.getNaziv(), 
//					slika.getTip(), 
//					Utility.decompressBytes(slika.getPicByte()),
//					slika.getNaziv_objekta());
		return slika;
	}

	public void setSlika(Slika slika) {
//		Slika img = null;
//		if(slika !=null) {
//			img = new Slika(
//					slika.getNaziv(), 
//					slika.getTip(), 
//					Utility.compressBytes(slika.getPicByte()),
//					slika.getNaziv_objekta());
//			
//		}
		
//		kategorija.setSlika(img);
		this.slika = slika;
	}

	public int getBrojArtikala() {
		return brojArtikala;
	}

	public void setBrojArtikala(int brojArtikala) {
		this.brojArtikala = brojArtikala;
	}

	@Override
	public String toString() {
		return "Kategorija [id=" + id + ", naziv=" + naziv + ", brojArtikala=" + brojArtikala
				+ "]";
	}
	
	
}
