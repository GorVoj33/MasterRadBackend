package fon.elab.prodavnica.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fon.elab.prodavnica.dtos.LoginAttemptDto;
import fon.elab.prodavnica.dtos.PodaciZaGradDto;
import fon.elab.prodavnica.dtos.RegistracijaKupcaDto;
import fon.elab.prodavnica.dtos.RegistracijaProdavcaDto;
import fon.elab.prodavnica.dtos.ServerResponse;
import fon.elab.prodavnica.rep.KorisnikRepository;
import util.Utility;
import fon.elab.prodavnica.dao.GradService;
import fon.elab.prodavnica.dao.KorisnikService;
import fon.elab.prodavnica.dao.KorpaService;
import fon.elab.prodavnica.dao.NarudzbenicaService;
import fon.elab.prodavnica.dao.SlikaService;
import fon.elab.prodavnica.dao.StavkaKorpeService;
import fon.elab.prodavnica.dao.impl.EmailServiceImpl;
import fon.elab.prodavnica.domain.*;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/rest")
public class HomeController {
	@Autowired
	KorisnikService korisnikService;
	@Autowired
	KorpaService korpaService;
	@Autowired
	GradService gradService;
	@Autowired
	StavkaKorpeService stavka;
	@Autowired
	SlikaService slikaService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	NarudzbenicaService narudzbenicaService;
	@Autowired
	EmailServiceImpl emailServiceImpl;
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String home() {
		return "sve radi ok";
		
	}
	@RequestMapping(path = "/vratiListuGradova", method = RequestMethod.GET)
	public List<Grad> vratiListuGradova() {
		return gradService.vratiSve();
		
	}
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ServerResponse login(@RequestBody LoginAttemptDto lad) {
		ServerResponse res = new ServerResponse();
		System.out.println("username:"+lad.getUsername());
		System.out.println("password:"+lad.getPassword());
		String username = lad.getUsername();
		String password = lad.getPassword();
		return res;
	}

	@RequestMapping(path = "/registrujProdavca", method = RequestMethod.POST)
	public ServerResponse registrujProdavca(@RequestBody RegistracijaProdavcaDto rpd) {
		ServerResponse res = new ServerResponse();
		String ime = rpd.getImeprodavca();
		String prezime = rpd.getPrezimeprodavca();
		String email = rpd.getEmailprodavca();
		String lozinka = passwordEncoder.encode(rpd.getLozinka());
		String opis = rpd.getOpis();
		String gazdinstvo = rpd.getNazivGazdinstva();
		double minIznos = rpd.getMinIznos();
		String ulica = rpd.getUlicaprodavca();
		String kontakt = rpd.getKontaktprodavca();
		boolean direktnaDostava = rpd.isDirektnaDostava();
		boolean kurirskaDostava = rpd.isKurirskaDostava();
		Grad grad = gradService.vratiPoId(rpd.getGrad().getPtt());
		grad.setBrojKorisnika(grad.getBrojKorisnika()+1);
		gradService.sacuvaj(grad);
		//gradService.updateBrojKorisnika(grad.getBrojKorisnika()+1, grad.getPtt());
		Korisnik k = new Prodavac(ime, prezime, email, lozinka, kontakt, ulica, gazdinstvo, opis, grad, minIznos, direktnaDostava, kurirskaDostava);
		Korpa korpa = new Korpa();
		
		k = korisnikService.sacuvaj(k);
		korpa.setKorisnik(k);
		korpaService.sacuvaj(korpa);
		k.setKorpa(korpa);	
		res.setStatus("USPESNO");
		res.setPoruka("Postovani prodavce "+ime+" ,uspesno ste se registrovali. Sacekajte da administrator odobri vas zahtev.");
		res.setObject(k.getId());
		return res;
	}
	
	@RequestMapping(path = "/registracijaKupca", method = RequestMethod.POST)
	public ServerResponse registracijaKupca(@RequestBody RegistracijaKupcaDto rkd) {
		ServerResponse res = new ServerResponse();
		String ime = rkd.getIme();
		String prezime = rkd.getPrezime();
		String email = rkd.getEmail();
		String lozinka = passwordEncoder.encode(rkd.getPassword());
		String razlog = rkd.getRazlogRegistracije();
		System.out.println("Razlog : "+razlog);
		Kupac k = new Kupac(ime, prezime, email, lozinka, razlog);
		
		System.out.println("NOVI KUPAC: "+k.getRazlogRegistracije() );
		Korpa korpa = new Korpa();
		
		//k.setKorpa(korpa);
//		korpaService.sacuvaj(k);
		k = (Kupac) korisnikService.sacuvaj(k);
		korpa.setKorisnik(k);
		korpaService.sacuvaj(korpa);
		k.setKorpa(korpa);
		res.setStatus("USPESNO");
		res.setPoruka("Postovani kupce "+ime+" ,uspesno ste se registrovali. Sacekajte da administrator odobri vas zahtev.");
		res.setObject(k.getId());
		return res;
	}
	@RequestMapping(path = "/registracijaKupca/test", method = RequestMethod.POST)
	public Korpa registracijaKupcaTest() {
		Korisnik k = new Kupac();
		Korpa korpa = new Korpa();
		k.setEmail("test1@gmail.com");
		k.setIme("ime");
		k.setPrezime("prezimenovic");
		k.setLozinka("lozinka");	
		k.setKorpa(korpa);		
		korisnikService.sacuvaj(k);
		StavkaKorpe sk = new StavkaKorpe();
		sk.setArtikal(null);
		sk.setKolicina(2);
		sk.setCenaStavke(2020);
		sk.setKorpa(korpa);
		korpa.setBrojStavki(korpa.getBrojStavki()+1);
		korpa.setUkupnaVrednost(korpa.getUkupnaVrednost() + sk.getCenaStavke());
		korpa.getStavke().add(sk);
		
		stavka.sacuvaj(sk);
		StavkaKorpe sk2= new StavkaKorpe();
		sk2.setArtikal(null);
		sk2.setKolicina(3);
		sk2.setCenaStavke(5020);
		sk2.setKorpa(korpa);
		korpa.setBrojStavki(korpa.getBrojStavki()+1);
		korpa.setUkupnaVrednost(korpa.getUkupnaVrednost() + sk2.getCenaStavke());
		korpa.getStavke().add(sk2);
		stavka.sacuvaj(sk2);
		return korpa;
	}
	
	@RequestMapping(path = "/test/getByKorpa/{korpaId}", method = RequestMethod.POST)
	public Korpa getByKorpaId(@PathVariable Integer korpaId) {
		Korpa korpa = new Korpa();
		try {
			korpa = korpaService.vratiPoID(korpaId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return korpa;
	}
	@RequestMapping(path = "/korisnici/vratiProdavce", method = RequestMethod.GET)
	public ServerResponse vratiProdavce() {
		ServerResponse sr = new ServerResponse();
		
		List<Prodavac> prodavci = korisnikService.vratiSveProdavce();
		for(Prodavac p:prodavci) {
			Slika slika = new Slika(p.getSlika().getNaziv(), p.getSlika().getTip(),Utility.decompressBytes(p.getSlika().getPicByte()), p.getSlika().getNaziv_objekta());
			p.setSlika(slika);
			
		}
		sr.setStatus("OK");
		sr.setObject(prodavci);
		return sr;
	}
	@RequestMapping(path = "/prodavac/id/{id}", method = RequestMethod.GET)
	public ServerResponse vratiProdavcaPoId(@PathVariable Integer id) {
		ServerResponse sr = new ServerResponse();
		
		Prodavac prodavac = korisnikService.vratiProdavcaPoId(id);
		prodavac.setSlika(new Slika(prodavac.getSlika().getNaziv(), prodavac.getSlika().getTip(), Utility.getInstance().decompressBytes(prodavac.getSlika().getPicByte()),prodavac.getSlika().getNaziv_objekta()));
		
		sr.setStatus("OK");
		sr.setObject(prodavac);
		return sr;
	}
	
	@RequestMapping(path = "/kupci/prihvaceni", method = RequestMethod.GET)
	public ServerResponse vratiSvePrihvaceneKupce () {
		ServerResponse sr = new ServerResponse();
		
		List<Kupac> kupci = korisnikService.vratiSveOdobreneKupce();		
		sr.setStatus("OK");
		sr.setObject(kupci);
		return sr;
	}
	@RequestMapping(path = "/kupci/odbijeni", method = RequestMethod.GET)
	public ServerResponse vratiSveOdbijeneKupce () {
		ServerResponse sr = new ServerResponse();
		
		List<Kupac> kupci = korisnikService.vratiSveOdbijeneKupce();		
		sr.setStatus("OK");
		sr.setObject(kupci);
		return sr;
	}
	
	@RequestMapping(path = "/odobriRegistraciju/{id}", method = RequestMethod.GET)
	public ServerResponse odobriRegistraciju (@PathVariable Integer id) {
		ServerResponse sr = new ServerResponse();
		try {
			Korisnik k = korisnikService.vratiPoId(id);
			k.setOdobren(true);
			korisnikService.sacuvaj(k);
			sr.setStatus("USPESNO");
			sr.setObject(k);
			String email = k.getEmail();
			String message = "Postovani "+k.getIme()+ ", vas zahtev za registraciju je odobren. Možete se ulogovati.";
			String subject = "Odobrenje registracije";
			emailServiceImpl.sendSimpleMessage(email, subject, message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sr.setStatus("GRESKA");
			sr.setPoruka("Neuspesno odobravanje registracije korisnika, proverite ID!");
		}
		
		return sr;
	}
	
	
	@RequestMapping(path = "/prodavci/prihvaceni", method = RequestMethod.GET)
	public ServerResponse vratiSvePrihvaceneProdavce () {
		ServerResponse sr = new ServerResponse();
		
		List<Prodavac> prodavci = korisnikService.vratiSveOdobreneProdavce();		
		sr.setStatus("OK");
		sr.setObject(prodavci);
		return sr;
	}
	@RequestMapping(path = "/prodavci/odbijeni", method = RequestMethod.GET)
	public ServerResponse vratiSveOdbijeneProdavce () {
		ServerResponse sr = new ServerResponse();
		
		List<Prodavac> prodavci = korisnikService.vratiSveOdbijeneProdavce();		
		sr.setStatus("OK");
		sr.setObject(prodavci);
		return sr;
	}
	@RequestMapping(path = "/gradovi/sveInformacije", method = RequestMethod.GET)
	public List<PodaciZaGradDto> vratiPodatkeOGradovima () {
		ServerResponse sr = new ServerResponse();
		
		List<Grad> gradovi = gradService.vratiSve();
		gradovi = gradovi.stream().sorted(Comparator.comparing(Grad::getNaziv)).collect(Collectors.toList());
		List<PodaciZaGradDto> gradoviDto = new ArrayList();
		for (Grad grad : gradovi) {
			
			PodaciZaGradDto podaciZaGrad = new PodaciZaGradDto();
			podaciZaGrad.setBrojNarudzbina(narudzbenicaService.vratiBrojZaGrad(grad.getPtt()));
			podaciZaGrad.setBrojProdavaca(korisnikService.vratiBrojProdavacaZaGrad(grad.getPtt()));
			podaciZaGrad.setGrad(grad);
			gradoviDto.add(podaciZaGrad);
		}
		return gradoviDto;
	}
	
	
	@RequestMapping(path = "/korisnik/{korisnikId}/sacuvajSliku", method = RequestMethod.POST)
	public ServerResponse sacuvajSlikuKorisnika (@PathVariable Integer korisnikId, @RequestParam("imageFile") MultipartFile file) {
		Slika img;
		ServerResponse r = new ServerResponse();
		try {
			img = new Slika(file.getOriginalFilename(), file.getContentType(),
					Utility.compressBytes(file.getBytes()),"korisnik");
			slikaService.sacuvaj(img);
			
			Korisnik korisnik;
			try {
				korisnik = korisnikService.vratiPoId(korisnikId);
				korisnik.setSlika(img);
				korisnikService.sacuvaj(korisnik);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				r.setStatus("ERROR");
				r.setPoruka("Slika je loseg formata, pokusajte sa nekom drugom. ");
			}
			r.setStatus("USPESNO");
		} catch (IOException e) {
			//response = (BodyBuilder) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
			e.printStackTrace();
			r.setStatus("ERROR");
			r.setPoruka("Slika je loseg formata, pokusajte sa nekom drugom. ");
		}
		
		return r;
	}
	
}
