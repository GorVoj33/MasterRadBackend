package fon.elab.prodavnica.dtos;

public class RegistracijaKupcaDto {
	String ime;
	String prezime;
	String email;
	String password;
	String razlogRegistracije;
	public RegistracijaKupcaDto() {
		// TODO Auto-generated constructor stub
	}
	public RegistracijaKupcaDto(String ime, String prezime, String email, String password) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRazlogRegistracije() {
		return razlogRegistracije;
	}
	public void setRazlogRegistracije(String razlogRegistracije) {
		this.razlogRegistracije = razlogRegistracije;
	}
	@Override
	public String toString() {
		return "RegistracijaKupcaDto [ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", password="
				+ password + "]";
	}
	
}
