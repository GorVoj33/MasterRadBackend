package fon.elab.prodavnica.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.MessagingException;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import fon.elab.prodavnica.dao.GradService;
import fon.elab.prodavnica.dao.KorisnikService;
import fon.elab.prodavnica.dao.NarudzbenicaService;
import fon.elab.prodavnica.dao.impl.EmailServiceImpl;
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
import fon.elab.prodavnica.dtos.MesecnaStatistikaDto;
import fon.elab.prodavnica.dtos.NarudzbinaDto;
import fon.elab.prodavnica.dtos.NovaKategorijaDto;
import fon.elab.prodavnica.dtos.ServerResponse;
import fon.elab.prodavnica.dtos.StavkaKorpeDto;
import fon.elab.prodavnica.rep.ArtikalRepository;
import fon.elab.prodavnica.rep.KorisnikRepository;
import util.Utility;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/rest")
public class NarudzbinaController {
	@Autowired
	KorisnikService korisnikService;
	@Autowired
	ArtikalRepository artikalRepository;
	@Autowired
	NarudzbenicaService narudzbenicaService;
	@Autowired
	GradService gradService;
	@Autowired
	EmailServiceImpl emailServiceImpl;
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
		sr.setStatus("USPESNO");
		sr.setPoruka("Uspesno ste narucili. Sacekajte da prodavci odobre vase narudzbine. ");
		return sr;
	}
	
	
	@RequestMapping(path = "/narudzbina/prodavacId/{korisnikId}", method = RequestMethod.GET)
	public List<Narudzbina> vratiNarudzbineProdavca (@PathVariable Integer korisnikId) {
//		ServerResponse sr = new ServerResponse();
		Prodavac k = new Prodavac();
		k.setId(korisnikId);
		List<Narudzbina> list = narudzbenicaService.vratiPremaProdavcu(k);
		
		return list;
	}
	@RequestMapping(path = "/narudzbina/kupacId/{korisnikId}", method = RequestMethod.GET)
	public List<Narudzbina> vratiNarudzbineKupca (@PathVariable Integer korisnikId) {
//		ServerResponse sr = new ServerResponse();
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
		try {
			Utility.getInstance().sendMailWithAttachment(n);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			Utility.getInstance().sendMailWithAttachment(n);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sr;
	}
	@RequestMapping(path = "/narudzbina/vratiOdbijene", method = RequestMethod.GET)
	public ServerResponse vratiOdbijene () {
		ServerResponse sr = new ServerResponse();
//		List<Narudzbina> list = narudzbenicaService.vratiPremaAtributuOdobrena(true);
//		List<Narudzbina> odbijene = new ArrayList<Narudzbina>();
//		for(Narudzbina n: list) {
//			if(n.getDatumOdbijanja() != null)
//				odbijene.add(n);
//		}
		System.out.println("GET ODBIJENE NARUDZBINE");
		List<Narudzbina> odbijene = narudzbenicaService.vratiPremaAtributuOdobrena(false);
		sr.setStatus("USPESNO");
		for(Narudzbina n : odbijene ) {
			System.out.println(n.getUlica());
		}
		sr.setObject(odbijene);
		
		
		return sr;
	}
	
	@RequestMapping(path = "/narudzbina/vratiSveOdobrene", method = RequestMethod.GET)
	public ServerResponse vratiPremaAtributuOdobrena () {
		ServerResponse sr = new ServerResponse();
		System.out.println("GET ODOBRENA NARUDZBINE");
		//List<Narudzbina> list = narudzbenicaService.vratiSve().stream().filter(n -> n.getDatumOdobrenja() != null).collect(Collectors.toList());
//		List<Narudzbina> list = narudzbenicaService.vratiPremaAtributuOdobrena(true);
//		for(Narudzbina n : list ) {
//			System.out.println(n.getUlica());
//		}
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
	
	@RequestMapping(path = "/narudzbina/mesecneStatistike", method = RequestMethod.GET)
	public ServerResponse vratiMesecneStatistike () {
		ServerResponse sr = new ServerResponse();
		List<MesecnaStatistikaDto> lista = formirajMesecneStatistike();
		for(MesecnaStatistikaDto msd : lista) {
			System.out.println(msd);
		}
		sr.setObject(lista);
		sr.setStatus("USPESNO");
		return sr;
	}
	public List<MesecnaStatistikaDto> formirajMesecneStatistike () {
		List<MesecnaStatistikaDto> lista = new ArrayList<>();
		MesecnaStatistikaDto april = new MesecnaStatistikaDto();
		april.setMesec("April");
		Double prihod = narudzbenicaService.vratiMesecneStatistike(4);
		if(prihod!=null) {
			april.setUkupniPrihod(prihod);
		} else 
		{
			april.setUkupniPrihod(0);
		}
		Integer broj = narudzbenicaService.vratiBrojNarudzbi(4);
		if(broj !=null ) {
			april.setBrojNarudzbina(broj);
		}else {
			april.setBrojNarudzbina(0);
		}
		april.setGodina(2020);
		lista.add(april);

		MesecnaStatistikaDto maj = new MesecnaStatistikaDto();
		maj.setMesec("Maj");
		prihod = narudzbenicaService.vratiMesecneStatistike(5);
		if(prihod!=null) {
			maj.setUkupniPrihod(prihod);
		} else 
		{
			maj.setUkupniPrihod(0);
		}
		broj = narudzbenicaService.vratiBrojNarudzbi(5);
		if(broj !=null ) {
			maj.setBrojNarudzbina(broj);
		}else {
			maj.setBrojNarudzbina(0);
		}
		maj.setGodina(2020);
		lista.add(maj);

		MesecnaStatistikaDto jun = new MesecnaStatistikaDto();
		jun.setMesec("Jun");
		prihod = narudzbenicaService.vratiMesecneStatistike(6);
		if(prihod!=null) {
			jun.setUkupniPrihod(prihod);
		} else 
		{
			jun.setUkupniPrihod(0);
		}
		broj = narudzbenicaService.vratiBrojNarudzbi(6);
		if(broj !=null ) {
			jun.setBrojNarudzbina(broj);
		}else {
			jun.setBrojNarudzbina(0);
		}
		jun.setGodina(2020);
		lista.add(jun);
		
		MesecnaStatistikaDto jul = new MesecnaStatistikaDto();
		jul.setMesec("Jul");
		prihod = narudzbenicaService.vratiMesecneStatistike(7);
		if(prihod!=null) {
			jul.setUkupniPrihod(prihod);
		} else 
		{
			jul.setUkupniPrihod(0);
		}
		broj = narudzbenicaService.vratiBrojNarudzbi(7);
		if(broj !=null ) {
			jul.setBrojNarudzbina(broj);
		}else {
			jul.setBrojNarudzbina(0);
		}
		jul.setGodina(2020);
		lista.add(jul);
		
		MesecnaStatistikaDto avgust = new MesecnaStatistikaDto();
		avgust.setMesec("Avgust");
		prihod = narudzbenicaService.vratiMesecneStatistike(8);
		if(prihod!=null) {
			avgust.setUkupniPrihod(prihod);
		} else 
		{
			avgust.setUkupniPrihod(0);
		}
		broj = narudzbenicaService.vratiBrojNarudzbi(8);
		if(broj !=null ) {
			avgust.setBrojNarudzbina(broj);
		}else {
			avgust.setBrojNarudzbina(0);
		}
		avgust.setGodina(2020);
		lista.add(avgust);
		
		MesecnaStatistikaDto sept = new MesecnaStatistikaDto();
		sept.setMesec("Septembar");
		prihod = narudzbenicaService.vratiMesecneStatistike(9);
		if(prihod!=null) {
			sept.setUkupniPrihod(prihod);
		} else 
		{
			sept.setUkupniPrihod(0);
		}
		broj = narudzbenicaService.vratiBrojNarudzbi(9);
		if(broj !=null ) {
			sept.setBrojNarudzbina(broj);
		}else {
			sept.setBrojNarudzbina(0);
		}
		sept.setGodina(2020);
		lista.add(sept);
		
		return lista;
		
	}
}
