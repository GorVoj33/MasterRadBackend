package fon.elab.prodavnica.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grad {
	@Id
	Integer ptt;
	String naziv;
	int brojKorisnika;
	
	public Grad() {
		// TODO Auto-generated constructor stub
	}

	public Integer getPtt() {
		return ptt;
	}

	public void setPtt(Integer ptt) {
		this.ptt = ptt;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getBrojKorisnika() {
		return brojKorisnika;
	}

	public void setBrojKorisnika(int brojKorisnika) {
		this.brojKorisnika = brojKorisnika;
	}
	
	
}
