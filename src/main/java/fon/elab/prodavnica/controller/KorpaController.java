package fon.elab.prodavnica.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fon.elab.prodavnica.dao.ArtikalService;
import fon.elab.prodavnica.dao.KorpaService;
import fon.elab.prodavnica.dao.StavkaKorpeService;
import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Korpa;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.domain.StavkaKorpe;
import fon.elab.prodavnica.dtos.ArtikalOsnovnoDto;
import fon.elab.prodavnica.dtos.ArtikalStavka;
import fon.elab.prodavnica.dtos.KorpaDto;
import fon.elab.prodavnica.dtos.NovaStavkaKorpeDto;
import fon.elab.prodavnica.dtos.ProdavacOsnovnoDto;
import fon.elab.prodavnica.dtos.ServerResponse;
import fon.elab.prodavnica.dtos.StavkaKorpeDto;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/korpa")
public class KorpaController {
	@Autowired
	KorpaService korpaService;
	@Autowired
	ArtikalService artikalService;
	@Autowired
	StavkaKorpeService stavkaKorpeService;
	
	
	
	@RequestMapping("/")
	public ServerResponse hello () {
		return new ServerResponse("Ok", "Sve je ok");
		
	}
	
	@RequestMapping("/id/{korpaId}")
	public ServerResponse vratiPoId (@PathVariable Integer korpaId) {
		ServerResponse sr = new ServerResponse();
		sr.setStatus("OK");
		sr.setPoruka("Uspesno ste ucitali korpu.");
		KorpaDto kd = new KorpaDto();
		
		try {
			Korpa korpa = korpaService.vratiPoID(korpaId);
			sr.setObject(korpa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sr.setStatus("GRESKA");
			sr.setPoruka("Greska prilikom ucitavanja korpe");
		}
		
		return sr;
	}
	
	@RequestMapping("/id/{korpaId}/drugi")
	public ServerResponse vratiPoIdDrugi (@PathVariable Integer korpaId) {
		ServerResponse sr = new ServerResponse();
		sr.setStatus("OK");
		sr.setPoruka("Uspesno ste ucitali korpu.");
		KorpaDto kd = new KorpaDto();
		ArrayList<StavkaKorpeDto> lista = new ArrayList<>();
		try {
			Korpa korpa = korpaService.vratiPoID(korpaId);
			System.out.println(korpa);
			Collections.sort(korpa.getStavke(), new Comparator<StavkaKorpe>(){
			    public int compare(StavkaKorpe s1, StavkaKorpe s2) {
			        return s1.getArtikal().getProdavac().getId().compareTo(s2.getArtikal().getProdavac().getId());
			    }
			});
			kd.setBrojStavki(korpa.getBrojStavki());
			kd.setUkupnaVrednost(korpa.getUkupnaVrednost());
			kd.setId(korpa.getId());
			
			
			//List<StavkaKorpeDto> postojeceStavke = new ArrayList<>();
			System.out.println(korpa);
			for(StavkaKorpe sk : korpa.getStavke()) {
				ArtikalStavka as = new ArtikalStavka();
				Artikal a = sk.getArtikal();
				as.setKolicina(sk.getKolicina());
				as.setVrednost(sk.getCenaStavke());
				as.setStavkaId(sk.getId());
				ArtikalOsnovnoDto aod = new ArtikalOsnovnoDto();
				aod.setId(a.getId());
				//aod.setKategorija(a.getKategorija());
				aod.setMoguceSlanjeKurirom(a.isSlanjeKuriromMoguce());
				aod.setSlika(a.getSlika());
				aod.setCena(a.getCena());
				aod.setNaziv(a.getNaziv());
				as.setArtikal(aod);
				Prodavac p = (Prodavac) sk.getArtikal().getProdavac();
				ProdavacOsnovnoDto pod = new ProdavacOsnovnoDto();
				pod.setDirektnaIsporuka(p.isDirektnaIsporukaMoguca());
				pod.setKurirskaIsporuka(p.isKurirskaIsporukaMoguca());
				pod.setId(p.getId());
				pod.setIme(p.getIme());
				pod.setPrezime(p.getPrezime());
				pod.setGazdinstvo(p.getNazivGazdinstva());
				
				StavkaKorpeDto skd = new StavkaKorpeDto();
				System.out.println(a);
				if(!a.isSlanjeKuriromMoguce()) {
					System.out.println("Artikal ne moze kurirom: "+a.getNaziv());
					skd.setMoguceKurirski(false);
				}
				skd.setProdavac(pod);
				boolean postoji = false;
				    for(int i =0 ; i< lista.size(); i++) {
				    	if(lista.get(i).equals(skd)) {
				    		if(!skd.isMoguceKurirski()) {
				    			lista.get(i).setMoguceKurirski(false);
				    		}
				    		lista.get(i).getArtikli().add(as);
				    		
				    		postoji = true;
				    	}
				    }
				    if(postoji == false) {
				    	skd.getArtikli().add(as);
				    	lista.add(skd);
				    	
				    }
				   
			    
			}
			
			
//			for (StavkaKorpe sk: korpa.getStavke()) {
//				if(sk.getArtikal().getProdavac())
//				ArtikalStavka as = new ArtikalStavka();
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sr.setStatus("GRESKA");
			sr.setPoruka("Greska prilikom ucitavanja korpe");
		}
		kd.setStavke(lista);
		sr.setObject(kd);
		return sr;
	}
	@RequestMapping("/{korpaId}/dodajArtikal")
	public ServerResponse dodajNoviArtikalUKorpu (@PathVariable int korpaId, @RequestBody NovaStavkaKorpeDto novaStavkaDto) {
		int artikalId = novaStavkaDto.getArtikalId();
		int kolicina = novaStavkaDto.getKolicina();
		System.out.println("ArtikalId: "+artikalId+" kolicina: "+kolicina);
		ServerResponse response = new ServerResponse();
		
		StavkaKorpe sk = new StavkaKorpe();
		
		try {
			Artikal a = artikalService.vratiPoId(artikalId);
			Korpa k = korpaService.vratiPoID(korpaId);
			sk.setArtikal(a);
			sk.setKolicina(kolicina);
			sk.setCenaStavke(sk.getArtikal().getCena() * sk.getKolicina());
			
			k.setUkupnaVrednost(k.getUkupnaVrednost() + sk.getCenaStavke());
			k.setBrojStavki(k.getBrojStavki() + 1);
			k.getStavke().add(sk);
			System.out.println("Korpa: "+k);
			korpaService.sacuvaj(k);
			response.setStatus("OK");
			response.setPoruka("Artikal uspesno dodat u korpu.");
			response.setObject(k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setStatus("GRESKA");
			response.setPoruka("Artikal nije dodat u korpu.");
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping("/id/{korpaId}/izbaci/{stavkaId}")
	public ServerResponse izbaciArtikal (@PathVariable int korpaId, @PathVariable Integer stavkaId) {
		System.out.println("Korpa id: "+korpaId + "   stavka id : "+stavkaId);
		ServerResponse response = new ServerResponse();
		try {
			stavkaKorpeService.obrisiPoId(stavkaId);
			Korpa k = korpaService.vratiPoID(korpaId);
			//Artikal a = artikalService.vratiPoId(artikalId);
			StavkaKorpe stavka = stavkaKorpeService.vratiPoId(stavkaId);
			System.out.println("Stavka obrisana");
			System.out.println(stavka);
			
			System.out.println("A ovo je korpa");
			System.out.println(k);
			//Integer stavkaId = stavka.getId();
			double vrednost = stavka.getCenaStavke();
			k.setUkupnaVrednost(k.getUkupnaVrednost() - vrednost);
			k.setBrojStavki(k.getBrojStavki() - 1);
			//stavkaKorpeService.obrisiPoId(stavkaId);
			
			k.getStavke().remove(stavka);
			korpaService.sacuvaj(k);
			System.out.println("A sada");
			System.out.println(k);
			KorpaDto kdto = vratiKorpaDto(k);
			response.setObject(kdto);
			response.setStatus("OK");
			response.setPoruka("Artikal uspesno izbacen iz korpe");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setStatus("GRESKA");
			response.setPoruka("Artikal nije izbacen iz korpe.");
			e.printStackTrace();
		}
		
		return response;
	}
	
	public KorpaDto vratiKorpaDto (Korpa korpa) {
		
		ArrayList<StavkaKorpeDto> lista = new ArrayList<>();
		KorpaDto kd = new KorpaDto();
		
			System.out.println(korpa);
			Collections.sort(korpa.getStavke(), new Comparator<StavkaKorpe>(){
			    public int compare(StavkaKorpe s1, StavkaKorpe s2) {
			        return s1.getArtikal().getProdavac().getId().compareTo(s2.getArtikal().getProdavac().getId());
			    }
			});
			kd.setBrojStavki(korpa.getBrojStavki());
			kd.setUkupnaVrednost(korpa.getUkupnaVrednost());
			kd.setId(korpa.getId());
			
			
			//List<StavkaKorpeDto> postojeceStavke = new ArrayList<>();
			System.out.println(korpa);
			for(StavkaKorpe sk : korpa.getStavke()) {
				ArtikalStavka as = new ArtikalStavka();
				Artikal a = sk.getArtikal();
				as.setKolicina(sk.getKolicina());
				as.setVrednost(sk.getCenaStavke());
				as.setStavkaId(sk.getId());
				ArtikalOsnovnoDto aod = new ArtikalOsnovnoDto();
				aod.setId(a.getId());
				//aod.setKategorija(a.getKategorija());
				aod.setMoguceSlanjeKurirom(a.isSlanjeKuriromMoguce());
				aod.setSlika(a.getSlika());
				aod.setCena(a.getCena());
				aod.setNaziv(a.getNaziv());
				as.setArtikal(aod);
				Prodavac p = (Prodavac) sk.getArtikal().getProdavac();
				ProdavacOsnovnoDto pod = new ProdavacOsnovnoDto();
				pod.setDirektnaIsporuka(p.isDirektnaIsporukaMoguca());
				pod.setKurirskaIsporuka(p.isKurirskaIsporukaMoguca());
				pod.setId(p.getId());
				pod.setIme(p.getIme());
				pod.setPrezime(p.getPrezime());
				pod.setGazdinstvo(p.getNazivGazdinstva());
				
				StavkaKorpeDto skd = new StavkaKorpeDto();
				System.out.println(a);
				if(!a.isSlanjeKuriromMoguce()) {
					System.out.println("Artikal ne moze kurirom: "+a.getNaziv());
					skd.setMoguceKurirski(false);
				}
				skd.setProdavac(pod);
				boolean postoji = false;
				    for(int i =0 ; i< lista.size(); i++) {
				    	if(lista.get(i).equals(skd)) {
				    		if(!skd.isMoguceKurirski()) {
				    			lista.get(i).setMoguceKurirski(false);
				    		}
				    		lista.get(i).getArtikli().add(as);
				    		
				    		postoji = true;
				    	}
				    }
				    if(postoji == false) {
				    	skd.getArtikli().add(as);
				    	lista.add(skd);
				    	
				    }
				   
			    
			}
			kd.setStavke(lista);
			return kd;
	}
}
