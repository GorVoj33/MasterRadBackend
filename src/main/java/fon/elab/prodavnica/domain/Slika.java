package fon.elab.prodavnica.domain;

import javax.persistence.*;

@Entity
@Table(name = "slika")
public class Slika {

	

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "naziv")
	private String naziv;

	@Column(name = "tip")
	private String tip;

	@Lob
	@Column(name = "picByte", columnDefinition="LONGBLOB")
	private byte[] picByte;

	@Column(name = "za_objekat")
	private String naziv_objekta;
	
	public Slika() {
		super();
	}

	public Slika(String name, String tip, byte[] picByte, String naziv_objekta) {
		this.naziv = name;
		this.tip = tip;
		this.picByte = picByte;
		this.naziv_objekta = naziv_objekta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public String getNaziv_objekta() {
		return naziv_objekta;
	}

	public void setNaziv_objekta(String naziv_objekta) {
		this.naziv_objekta = naziv_objekta;
	}
	
	
}