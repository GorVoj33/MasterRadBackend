package fon.elab.prodavnica.dtos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class NovaKategorijaDto {
	String naziv;
//	MultipartFile slika;
	public NovaKategorijaDto() {
		// TODO Auto-generated constructor stub
	}
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
//	public MultipartFile getSlika() {
//		return slika;
//	}
//	public void setSlika(MultipartFile slika) {
//		this.slika = slika;
//	}
	
}
