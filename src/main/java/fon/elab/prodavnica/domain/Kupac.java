package fon.elab.prodavnica.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Kupac extends Korisnik{

	String razlogRegistracije;
	
	public Kupac() {
		// TODO Auto-generated constructor stub
	}
	
	public Kupac(String ime, String prezime, String email, String lozinka, String razlogRegistracije) {
		super(ime,prezime, email, lozinka);
		this.razlogRegistracije = razlogRegistracije;
	}

	public String getRazlogRegistracije() {
		return razlogRegistracije;
	}
	public void setRazlogRegistracije(String razlogRegistracije) {
		this.razlogRegistracije = razlogRegistracije;
	}
	
}
