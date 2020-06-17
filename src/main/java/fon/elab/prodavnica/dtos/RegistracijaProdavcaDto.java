package fon.elab.prodavnica.dtos;

import fon.elab.prodavnica.domain.Grad;

public class RegistracijaProdavcaDto {
	String imeprodavca;
	String prezimeprodavca;
	String emailprodavca;
	String lozinka;
	String kontaktprodavca;
	double minIznos;
	String ulicaprodavca;
	String opis;
	boolean direktnaDostava;
	boolean kurirskaDostava;
	Grad grad;
	String nazivGazdinstva;

	public RegistracijaProdavcaDto() {
		// TODO Auto-generated constructor stub
	}

	public String getImeprodavca() {
		return imeprodavca;
	}

	public void setImeprodavca(String imeprodavca) {
		this.imeprodavca = imeprodavca;
	}

	public String getPrezimeprodavca() {
		return prezimeprodavca;
	}

	public void setPrezimeprodavca(String prezimeprodavca) {
		this.prezimeprodavca = prezimeprodavca;
	}

	public String getEmailprodavca() {
		return emailprodavca;
	}

	public void setEmailprodavca(String emailprodavca) {
		this.emailprodavca = emailprodavca;
	}

	public String getKontaktprodavca() {
		return kontaktprodavca;
	}

	public void setKontaktprodavca(String kontaktprodavca) {
		this.kontaktprodavca = kontaktprodavca;
	}

	public double getMinIznos() {
		return minIznos;
	}

	public void setMinIznos(double minIznos) {
		this.minIznos = minIznos;
	}

	public String getUlicaprodavca() {
		return ulicaprodavca;
	}

	public void setUlicaprodavca(String ulicaprodavca) {
		this.ulicaprodavca = ulicaprodavca;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isDirektnaDostava() {
		return direktnaDostava;
	}

	public void setDirektnaDostava(boolean direktnaDostava) {
		this.direktnaDostava = direktnaDostava;
	}

	public boolean isKurirskaDostava() {
		return kurirskaDostava;
	}

	public void setKurirskaDostava(boolean kurirskaDostava) {
		this.kurirskaDostava = kurirskaDostava;
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public String getNazivGazdinstva() {
		return nazivGazdinstva;
	}

	public void setNazivGazdinstva(String nazivGazdinstva) {
		this.nazivGazdinstva = nazivGazdinstva;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	@Override
	public String toString() {
		return "RegistracijaProdavcaDto [imeprodavca=" + imeprodavca + ", prezimeprodavca=" + prezimeprodavca
				+ ", emailprodavca=" + emailprodavca + ", kontaktprodavca=" + kontaktprodavca + ", minIznos=" + minIznos
				+ ", ulicaprodavca=" + ulicaprodavca + ", opis=" + opis + ", direktnaDostava=" + direktnaDostava
				+ ", kurirskaDostava=" + kurirskaDostava + ", grad=" + grad + ", nazivGazdinstva=" + nazivGazdinstva
				+ "]";
	}
	
}
