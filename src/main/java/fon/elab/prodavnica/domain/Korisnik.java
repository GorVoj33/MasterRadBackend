package fon.elab.prodavnica.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="korisnik")
public class Korisnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String email;
	@JsonIgnore
	String lozinka;
	String ime;
	String prezime;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "slika_id")
	Slika slika;

	boolean odobren;
	@OneToOne(mappedBy="korisnik", cascade = CascadeType.DETACH, orphanRemoval = true,
	        fetch = FetchType.LAZY)
	@JsonIgnore
	Korpa korpa;
	
	public Korisnik() {
		// TODO Auto-generated constructor stub
	}

	public Korisnik( String ime, String prezime, String email, String lozinka) {
		super();
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Korpa getKorpa() {
		return korpa;
	}

	public void setKorpa(Korpa korpa) {
		
		if (korpa == null) {
            if (this.korpa != null) {
                this.korpa.setKorisnik(null);
            }
        }
        else {
            korpa.setKorisnik(this);
        }
        
		this.korpa = korpa;
	}
	
	public Slika getSlika() {
		return slika;
	}

	public void setSlika(Slika slika) {
		this.slika = slika;
	}

	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", email=" + email + ", lozinka=" + lozinka + ", ime=" + ime + ", prezime="
				+ prezime + ",  odobren=" + odobren;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
