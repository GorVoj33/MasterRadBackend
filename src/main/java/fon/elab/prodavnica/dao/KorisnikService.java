package fon.elab.prodavnica.dao;
import java.util.List;

import fon.elab.prodavnica.domain.Grad;
import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Korpa;
import fon.elab.prodavnica.domain.Kupac;
import fon.elab.prodavnica.domain.Prodavac;

public interface KorisnikService {
	List<Korisnik> vratiSve();
	Korisnik vratiPoId(Integer id) throws Exception;
	Korisnik sacuvaj(Korisnik k);
	void korpa(Korpa korpaId, int korisnikId);
	List<Korisnik> vratiKorisnikaPoPrezimenu(String prezime);
	List<Prodavac> vratiSveProdavce();
	Prodavac vratiProdavcaPoId(Integer id);
	List<Kupac> vratiSveKupce();
	List<Kupac> vratiSveOdobreneKupce();
	List<Kupac> vratiSveOdbijeneKupce();
	List<Prodavac> vratiSveOdobreneProdavce();
	List<Prodavac> vratiSveOdbijeneProdavce();
	Korisnik vratiProdavcaPoEmail(String email);
	int vratiBrojProdavacaZaGrad(int gradId);
}
