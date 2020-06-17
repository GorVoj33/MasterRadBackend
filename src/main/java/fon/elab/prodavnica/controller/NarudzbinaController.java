package fon.elab.prodavnica.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fon.elab.prodavnica.dao.GradService;
import fon.elab.prodavnica.dao.KorisnikService;
import fon.elab.prodavnica.dao.NarudzbenicaService;
import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Grad;
import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Narudzbina;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.domain.StavkaKorpe;
import fon.elab.prodavnica.domain.StavkaNarudzbine;
import fon.elab.prodavnica.dtos.ArtikalStavka;
import fon.elab.prodavnica.dtos.KorpaDto;
import fon.elab.prodavnica.dtos.NarudzbinaDto;
import fon.elab.prodavnica.dtos.NovaKategorijaDto;
import fon.elab.prodavnica.dtos.ServerResponse;
import fon.elab.prodavnica.dtos.StavkaKorpeDto;
import fon.elab.prodavnica.rep.ArtikalRepository;
import fon.elab.prodavnica.rep.KorisnikRepository;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NarudzbinaController {
	@Autowired
	KorisnikService korisnikService;
	@Autowired
	ArtikalRepository artikalRepository;
	@Autowired
	NarudzbenicaService narudzbenicaService;
	@Autowired
	GradService gradService;
	@RequestMapping(path = "/narudzbina/dodaj", method = RequestMethod.POST)
	public ServerResponse dodajNarudzbine (@RequestBody NarudzbinaDto narudzbina) {
		System.out.println("Cuvanje nova narudzbina");
		System.out.println(narudzbina);
		ServerResponse sr = new ServerResponse();
		sr.setStatus("OK");
		Korisnik kupac = null;
		try {
			kupac = korisnikService.vratiPoId(narudzbina.getKupac());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sr.setStatus("GRESKA");
		}
		String adresa = narudzbina.getAdresa();
		
		Grad grad = gradService.vratiPoId(narudzbina.getGrad().getPtt());
		String altKontakt = narudzbina.getAlternativniKontaktTelefon();
		for (StavkaKorpeDto skd : narudzbina.getKorpa().getStavke()) {
			Narudzbina n = new Narudzbina();
			Prodavac prodavac = null;
			try {
				prodavac = (Prodavac) korisnikService.vratiPoId(skd.getProdavac().getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				sr.setStatus("GRESKA");
			}
			n.setDatumKreiranja(new Date());
			n.setOdobrena(false);
			n.setDatumOdobrenja(null);
			n.setGrad(grad);
			n.setUlica(adresa);
			n.setNapomena(altKontakt);
			n.setNacinIsporuke(skd.getIzabranaDostava());
			n.setUkupnaVrednost(skd.getUkupnaVrednost());
			n.setProdavac(prodavac);
			n.setKupac(kupac);
			for(ArtikalStavka sk: skd.getArtikli()) {
				Optional<Artikal> a = artikalRepository.findById(sk.getArtikal().getId());
				Artikal artikal = null;
				if(a.isPresent()) {
					artikal = a.get();
				}
				StavkaNarudzbine stavkaNarudzbine = new StavkaNarudzbine();
				stavkaNarudzbine.setArtikal(artikal);
				stavkaNarudzbine.setCenaStavke(sk.getVrednost());
				stavkaNarudzbine.setKolicina(sk.getKolicina());
				stavkaNarudzbine.setNarudzbina(n);
				n.getStavke().add(stavkaNarudzbine);
			}
			narudzbenicaService.sacuvaj(n);
		}
		sr.setPoruka("Uspesno ste narucili. Sacekajte da prodavci odobre vase narudzbine. ");
		return sr;
	}
	
	
	@RequestMapping(path = "/narudzbina/korisnikId/{korisnikId}", method = RequestMethod.GET)
	public List<Narudzbina> vratiNarudzbineProdavca (@PathVariable Integer korisnikId) {
		ServerResponse sr = new ServerResponse();
		Korisnik k = new Korisnik();
		k.setId(korisnikId);
		List<Narudzbina> list = narudzbenicaService.vratiPremaKupcu(k);
		
		return list;
	}
	@RequestMapping(path = "/narudzbina/{id}/odbijanje", method = RequestMethod.GET)
	public ServerResponse odbijNarudzbinu (@PathVariable Integer id) {
		ServerResponse sr = new ServerResponse();
		Narudzbina n = narudzbenicaService.vratiPoId(id);
		n.setDatumOdbijanja(new Date());
		n.setDatumOdobrenja(null);
		n.setOdobrena(false);
		sr.setStatus("USPESNO");
		sr.setObject(n);
		
		narudzbenicaService.sacuvaj(n);
		return sr;
	}
	@RequestMapping(path = "/narudzbina/{id}/prihvatanje", method = RequestMethod.GET)
	public ServerResponse prihvatiNarudzbinu (@PathVariable Integer id) {
		ServerResponse sr = new ServerResponse();
		Narudzbina n = narudzbenicaService.vratiPoId(id);
		n.setDatumOdobrenja(new Date());
		n.setOdobrena(true);
		n.setDatumOdbijanja(null);
		narudzbenicaService.sacuvaj(n);
		sr.setStatus("USPESNO");
		sr.setObject(n);
		
		
		return sr;
	}
	@RequestMapping(path = "/narudzbina/vratiOdbijene", method = RequestMethod.GET)
	public ServerResponse vratiPremaAtributuOdobrenaOdbijene () {
		ServerResponse sr = new ServerResponse();
		List<Narudzbina> list = narudzbenicaService.vratiPremaAtributuOdobrena(true);
		List<Narudzbina> odbijene = new ArrayList<Narudzbina>();
		for(Narudzbina n: list) {
			if(n.getDatumOdbijanja() != null)
				odbijene.add(n);
		}
		sr.setStatus("USPESNO");
		sr.setObject(odbijene);
		
		
		return sr;
	}
	
	@RequestMapping(path = "/narudzbina/vratiOdobrene", method = RequestMethod.GET)
	public ServerResponse vratiPremaAtributuOdobrena () {
		ServerResponse sr = new ServerResponse();
		List<Narudzbina> list = narudzbenicaService.vratiPremaAtributuOdobrena(true);
		sr.setStatus("USPESNO");
		sr.setObject(list);
		
		
		return sr;
	}
	
	@RequestMapping(path = "/narudzbina/suma", method = RequestMethod.GET)
	public ServerResponse vratiUkupnuSumu () {
		ServerResponse sr = new ServerResponse();
		Double suma = narudzbenicaService.vratiSumu();
		sr.setStatus("USPESNO");
		sr.setObject(suma);
		
		
		return sr;
	}
}
