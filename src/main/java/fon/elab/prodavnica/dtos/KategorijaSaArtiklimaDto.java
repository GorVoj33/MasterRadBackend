package fon.elab.prodavnica.dtos;
import java.util.List;

import fon.elab.prodavnica.domain.*;

public class KategorijaSaArtiklimaDto {
	Kategorija kategorija;
	List<ArtikalOsnovnoDto> artikli;
	
	public KategorijaSaArtiklimaDto() {
		// TODO Auto-generated constructor stub
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public List<ArtikalOsnovnoDto> getArtikli() {
		return artikli;
	}

	public void setArtikli(List<ArtikalOsnovnoDto> artikli) {
		this.artikli = artikli;
	}
	
	
}
