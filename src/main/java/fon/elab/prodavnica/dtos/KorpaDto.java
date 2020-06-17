package fon.elab.prodavnica.dtos;

import java.util.ArrayList;
import java.util.List;

public class KorpaDto {
	Integer id;
	double ukupnaVrednost;
	int brojStavki;
	List<StavkaKorpeDto> stavke = new ArrayList<>();
	
	public KorpaDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getUkupnaVrednost() {
		return ukupnaVrednost;
	}

	public void setUkupnaVrednost(double ukupnaVrednost) {
		this.ukupnaVrednost = ukupnaVrednost;
	}

	public int getBrojStavki() {
		return brojStavki;
	}

	public void setBrojStavki(int brojStavki) {
		this.brojStavki = brojStavki;
	}

	public List<StavkaKorpeDto> getStavke() {
		return stavke;
	}

	public void setStavke(List<StavkaKorpeDto> stavke) {
		this.stavke = stavke;
	}

	@Override
	public String toString() {
		return "KorpaDto [id=" + id + ", ukupnaVrednost=" + ukupnaVrednost + ", brojStavki=" + brojStavki + ", stavke="
				+ stavke + "]";
	}
	
	
}
