package fon.elab.prodavnica.dtos;

import fon.elab.prodavnica.domain.Grad;

public class PodaciZaGradDto {
	Grad grad;
	int brojProdavaca;
	int brojNarudzbina;
	
	public PodaciZaGradDto() {
		// TODO Auto-generated constructor stub
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public int getBrojProdavaca() {
		return brojProdavaca;
	}

	public void setBrojProdavaca(int brojProdavaca) {
		this.brojProdavaca = brojProdavaca;
	}

	public int getBrojNarudzbina() {
		return brojNarudzbina;
	}

	public void setBrojNarudzbina(int brojNarudzbina) {
		this.brojNarudzbina = brojNarudzbina;
	}
	
	
}
