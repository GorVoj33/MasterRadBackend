package fon.elab.prodavnica.dtos;

public class ProdavacOsnovnoDto {
	Integer id;
	String ime;
	String prezime;
	String gazdinstvo;
	boolean direktnaIsporuka;
	boolean kurirskaIsporuka;
	String email;
	public ProdavacOsnovnoDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getGazdinstvo() {
		return gazdinstvo;
	}

	public void setGazdinstvo(String gazdinstvo) {
		this.gazdinstvo = gazdinstvo;
	}

	public boolean isDirektnaIsporuka() {
		return direktnaIsporuka;
	}

	public void setDirektnaIsporuka(boolean direktnaIsporuka) {
		this.direktnaIsporuka = direktnaIsporuka;
	}

	public boolean getKurirskaIsporuka() {
		return kurirskaIsporuka;
	}

	public void setKurirskaIsporuka(boolean kurirskaIsporuka) {
		this.kurirskaIsporuka = kurirskaIsporuka;
	}

	public ProdavacOsnovnoDto(Integer id, String ime, String prezime, String gazdinstvo, boolean direktnaIsporuka,
			boolean kurirskaIsporuka) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.gazdinstvo = gazdinstvo;
		this.direktnaIsporuka = direktnaIsporuka;
		this.kurirskaIsporuka = kurirskaIsporuka;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
