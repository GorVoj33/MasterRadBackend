package fon.elab.prodavnica.dtos;

public class MesecnaStatistikaDto {
	int godina;
	String mesec;
	int brojNarudzbina;
	double ukupniPrihod;
	
	public MesecnaStatistikaDto(int godina, String mesec, int brojNarudzbina, double ukupniPrihod) {
		super();
		this.godina = godina;
		this.mesec = mesec;
		this.brojNarudzbina = brojNarudzbina;
		this.ukupniPrihod = ukupniPrihod;
	}

	public MesecnaStatistikaDto() {
		// TODO Auto-generated constructor stub
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public String getMesec() {
		return mesec;
	}

	public void setMesec(String mesec) {
		this.mesec = mesec;
	}

	public int getBrojNarudzbina() {
		return brojNarudzbina;
	}

	public void setBrojNarudzbina(int brojNarudzbina) {
		this.brojNarudzbina = brojNarudzbina;
	}

	public double getUkupniPrihod() {
		return ukupniPrihod;
	}

	public void setUkupniPrihod(double ukupniPrihod) {
		this.ukupniPrihod = ukupniPrihod;
	}

	@Override
	public String toString() {
		return "MesecnaStatistikaDto [godina=" + godina + ", mesec=" + mesec + ", brojNarudzbina=" + brojNarudzbina
				+ ", ukupniPrihod=" + ukupniPrihod + "]";
	}
	
	
}
