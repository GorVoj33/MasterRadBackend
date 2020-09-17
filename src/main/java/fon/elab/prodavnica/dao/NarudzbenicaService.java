package fon.elab.prodavnica.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Narudzbina;
import fon.elab.prodavnica.dtos.MesecnaStatistikaDto;

public interface NarudzbenicaService {
	
	void sacuvaj(Narudzbina n);
	List<Narudzbina> vratiPremaKupcu(Korisnik korisnik);
	List<Narudzbina> vratiPremaProdavcu(Korisnik korisnik);
	Narudzbina vratiPoId(Integer id);
	List<Narudzbina> vratiPremaAtributuOdobrena(boolean odobrena);
	Double vratiSumu();
	int vratiBrojZaGrad(Integer ptt);
	List<Narudzbina> vratiSve();
	Double vratiMesecneStatistike(int mesec);
	Integer vratiBrojNarudzbi(int mesec);
}
