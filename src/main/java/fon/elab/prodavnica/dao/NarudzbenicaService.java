package fon.elab.prodavnica.dao;

import java.util.List;
import java.util.Optional;

import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Narudzbina;

public interface NarudzbenicaService {
	void sacuvaj(Narudzbina n);
	List<Narudzbina> vratiPremaKupcu(Korisnik korisnik);
	Narudzbina vratiPoId(Integer id);
	List<Narudzbina> vratiPremaAtributuOdobrena(boolean odobrena);
	Double vratiSumu();
}
