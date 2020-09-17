package fon.elab.prodavnica.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fon.elab.prodavnica.dao.ArtikalService;
import fon.elab.prodavnica.dao.KategorijaService;
import fon.elab.prodavnica.dao.KomentarService;
import fon.elab.prodavnica.dao.KorisnikService;
import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.domain.Komentar;
import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.domain.Slika;
import fon.elab.prodavnica.dtos.AdminPanelInfoDto;
import fon.elab.prodavnica.dtos.ArtikalDetaljiDto;
import fon.elab.prodavnica.dtos.ArtikalOsnovnoDto;
import fon.elab.prodavnica.dtos.KategorijaBezSlikeDto;
import fon.elab.prodavnica.dtos.NoviArtikalDto;
import fon.elab.prodavnica.dtos.NoviKomentarDto;
import fon.elab.prodavnica.dtos.ProdavacOsnovnoDto;
import fon.elab.prodavnica.dtos.RegistracijaProdavcaDto;
import fon.elab.prodavnica.dtos.ServerResponse;
import fon.elab.prodavnica.rep.ArtikalRepository;
import fon.elab.prodavnica.rep.SlikaRepository;
import util.Utility;
import fon.elab.prodavnica.rep.KategorijaRepository;
import fon.elab.prodavnica.rep.KomentarRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/rest/artikal")
public class ArtikalController {
	ArtikalOsnovnoDto lazniArt = new ArtikalOsnovnoDto();
	@Autowired
	SlikaRepository imageRepository;
	@Autowired
	KategorijaService kategorijaService;
	@Autowired
	KorisnikService korisnikService;
	@Autowired
	ArtikalService artikalService;
	@Autowired
	KomentarService komentarService;
	@RequestMapping(path = "/hello")
	public String hello () {
		return "hello!!!!!";
	}
	@RequestMapping(path = "/dodajArtikal", method = RequestMethod.POST)
	public Artikal dodajArtikal(@RequestBody NoviArtikalDto art) throws IOException {
		System.out.println("Zahtev za dodavanje artikla");
		Artikal a = new Artikal();
		//MultipartFile slika = (MultipartFile) art.getSlika();
		String email = art.getProdavac().getEmail();
		Korisnik prodavac = korisnikService.vratiProdavcaPoEmail(email);
		System.out.println("Email prodavca: "+email);
		a.setProdavac((Prodavac) prodavac);
		a.setAktivan(true);
		a.setCena(art.getCena());
		a.setNaziv(art.getNaziv());
		a.setKategorija(art.getKategorija());
		a.setPoreklo(art.getPoreklo());
		a.setSlanjeKuriromMoguce(art.isMozeKurirom());
		a.setZaliha(art.getZaliha());
		a.setKolicina(art.getKolicina());
		a.setOpis(art.getOpis());
		a.setDatumUnosa(new Date());
		kategorijaService.sacuvaj(a.getKategorija());
		//MultipartFile slika = art.getSlika();
		a = artikalService.sacuvaj(a);
		System.out.println("Novi id artikla: "+a.getId());
//		ImageModel img = new ImageModel(name, slika.getContentType(),
//				compressBytes(slika.getBytes()));
		//imageRepository.save(img);
		//System.out.println(art);
		return a;
	}
	@RequestMapping(path = "/artikli/osnovno/svi", method = RequestMethod.GET)
	public List<ArtikalOsnovnoDto> vratiOsnovneInformacijeOArtiklima() throws IOException {
		List<Artikal> artikli = artikalService.vratiSve();
		List<ArtikalOsnovnoDto> lista = new ArrayList<ArtikalOsnovnoDto>();
		
		for (Artikal a : artikli) {
			ArtikalOsnovnoDto art = new ArtikalOsnovnoDto();
			if(a.isAktivan() == true) {
				art.setId(a.getId());
				art.setNaziv(a.getNaziv());
				art.setCena(a.getCena());
				art.setKategorija(a.getKategorija());
				art.setSlika(a.getSlika());
				art.setKolicina(a.getKolicina());
				lista.add(art);	
			}
		}
		return lista;
	}
	
	
	
	
	@RequestMapping(path = "/artikli/osnovno/istaknuti", method = RequestMethod.GET)
	public List<ArtikalOsnovnoDto> vratiOsnovneInformacijeOIstaknutimArtiklima() throws IOException {
		List<Artikal> artikli = artikalService.vratiSve();
		List<ArtikalOsnovnoDto> lista = new ArrayList<ArtikalOsnovnoDto>();
		
		for (Artikal a : artikli) {
			ArtikalOsnovnoDto art = new ArtikalOsnovnoDto();
			if(a.isAktivan() == true && a.isZaPromovisanje()) {
				art.setId(a.getId());
				art.setNaziv(a.getNaziv());
				art.setCena(a.getCena());
				art.setKategorija(a.getKategorija());
				art.setSlika(a.getSlika());
				art.setKolicina(a.getKolicina());
				lista.add(art);	
			}
		}
		return lista;
	}
	@RequestMapping(path = "/{artikalId}/sacuvajSliku", method = RequestMethod.POST)
	public ServerResponse sacuvajSlikuArtikla (@PathVariable Long artikalId, @RequestParam("imageFile") MultipartFile file) {
		Slika img;
		ServerResponse r = new ServerResponse();
		System.out.println("Stigao zahtev za cuvanje slike artikle!");
		//BodyBuilder response = (BodyBuilder) ResponseEntity.status(HttpStatus.OK).build();
		try {
			img = new Slika(file.getOriginalFilename(), file.getContentType(),
					Utility.compressBytes(file.getBytes()),"artikal");
			imageRepository.save(img);
			Integer id = (int) (long) artikalId;
			Artikal a;
			try {
				a = artikalService.vratiPoId(id);
				a.setSlika(img);
				artikalService.sacuvaj(a);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				r.setStatus("ERROR");
				r.setPoruka("Slika je loseg formata, pokusajte sa nekom drugom. ");
			}
			
//			
//				Artikal artikal = a.get();
//				artikal.setSlika(img);
//				artikalService.sacuvaj(artikal);
			
			r.setStatus("OK");
		} catch (IOException e) {
			//response = (BodyBuilder) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
			e.printStackTrace();
			r.setStatus("ERROR");
			r.setPoruka("Slika je loseg formata, pokusajte sa nekom drugom. ");
		}
		
		return r;
	}
	@RequestMapping(path = "/{artikalId}", method = RequestMethod.GET)
	public ArtikalDetaljiDto vratiDetaljeOArtiklu (@PathVariable Integer artikalId) {
		ArtikalDetaljiDto artDto = new ArtikalDetaljiDto();
		try {
			Artikal a = artikalService.vratiPoId(artikalId);
			KategorijaBezSlikeDto kbs = new KategorijaBezSlikeDto();
			kbs.setId(a.getKategorija().getId());
			kbs.setNaziv(a.getKategorija().getNaziv());
			artDto.setCena(a.getCena());
			artDto.setOpis(a.getOpis());
			artDto.setNaziv(a.getNaziv());
			artDto.setSlanjeKuriromMoguce(a.isSlanjeKuriromMoguce());
			artDto.setKategorija(kbs);
			artDto.setSlika(new Slika(a.getSlika().getNaziv(), a.getSlika().getTip(),Utility.getInstance().decompressBytes(a.getSlika().getPicByte()), null));
			artDto.setZaliha(a.getZaliha());
			artDto.setAktivan(a.isAktivan());
			ProdavacOsnovnoDto pod = new ProdavacOsnovnoDto();
			pod.setIme(a.getProdavac().getIme());
			pod.setPrezime(a.getProdavac().getPrezime());
			pod.setId(a.getProdavac().getId());
			pod.setGazdinstvo(a.getProdavac().getNazivGazdinstva());
			pod.setDirektnaIsporuka(a.getProdavac().isDirektnaIsporukaMoguca());
			pod.setKurirskaIsporuka(a.getProdavac().isKurirskaIsporukaMoguca());
			pod.setEmail(a.getProdavac().getEmail());
			artDto.setProdavac(pod);
			artDto.setKolicina(a.getKolicina());
			artDto.setPoreklo(a.getPoreklo());
			artDto.setId(a.getId());
			artDto.setIstaknut(a.isZaPromovisanje());
			List<ArtikalOsnovnoDto> slicniArtikli = artikalService.vratiArtiklePremaKategoriji(a.getKategorija().getId());
			
			lazniArt.setId(a.getId());
			if(slicniArtikli.contains(lazniArt)) {
				System.out.println("ART DTO SE NALAZI");
				int index = slicniArtikli.indexOf(lazniArt);
				slicniArtikli.remove(index);		
			}else {
				System.out.println("Ne nalazi se");
			}
			System.out.println("duzina slicnih"+slicniArtikli.size());
			artDto.setSlicniArtikli(slicniArtikli);
			int brojKomentara = a.getKomentari().size();
			artDto.setBrojKomentara(brojKomentara);
			return artDto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path = "/{artikalId}/deaktiviraj", method = RequestMethod.GET)
	public ServerResponse deaktivirajArtikal (@PathVariable Long artikalId) {
		ServerResponse r = new ServerResponse();
		Integer id = (int) (long) artikalId;
		Artikal a;
		try {
			a = artikalService.vratiPoId(id);
			a.setAktivan(false);
			artikalService.sacuvaj(a);
			r.setPoruka("Artikal uspesno deaktiviran.");
			r.setStatus("USPESNO");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r.setPoruka("Artikal nije pronadjen.");
			r.setStatus("USPESNO");
		}
		
		
		
		return r;
	}
	@RequestMapping(path = "/{artikalId}/aktiviraj", method = RequestMethod.GET)
	public ServerResponse aktivirajArtikal (@PathVariable Long artikalId) {
		ServerResponse r = new ServerResponse();
		Integer id = (int) (long) artikalId;
		Artikal a;
		try {
			a = artikalService.vratiPoId(id);
			a.setAktivan(true);
			artikalService.sacuvaj(a);
			r.setPoruka("Artikal uspesno aktiviran.");
			r.setStatus("USPESNO");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r.setPoruka("Artikal nije pronadjen.");
			r.setStatus("USPESNO");
		}
		
		
		
		return r;
	}
	
	@RequestMapping(path = "/{artikalId}/izmeni", method = RequestMethod.POST)
	public ServerResponse izmeniArtikal (@RequestBody ArtikalDetaljiDto artikalDto, @PathVariable Long artikalId) {
		ServerResponse r = new ServerResponse();
		Integer id = (int) (long) artikalId;
		Artikal artikal;
		try {
			artikal = artikalService.vratiPoId(id);
			if(!artikal.getKolicina().equals(artikalDto.getKolicina())) {
				artikal.setKolicina(artikalDto.getKolicina());
			}
			if(!artikal.getNaziv().equals(artikalDto.getNaziv())) {
				artikal.setNaziv(artikalDto.getNaziv());
			}
			if(!artikal.getOpis().equals(artikalDto.getOpis())) {
				artikal.setOpis(artikalDto.getOpis());
			}
			if(!artikal.getPoreklo().equals(artikalDto.getPoreklo())) {
				artikal.setPoreklo(artikalDto.getPoreklo());
			}
			if(artikal.getCena() !=artikalDto.getCena()) {
				artikal.setCena(artikalDto.getCena());
			}
			if(artikal.getZaliha() !=artikalDto.getZaliha()) {
				artikal.setZaliha(artikalDto.getZaliha());
			}
			if(artikal.getKategorija().getId() != artikalDto.getKategorija().getId()) {
				Kategorija kategorija = kategorijaService.vratiPoId(artikalDto.getKategorija().getId());
				artikal.setKategorija(kategorija);
			}
			artikalService.sacuvaj(artikal);
			r.setStatus("USPESNO");
			r.setObject(artikal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r.setStatus("GRESKA");
			r.setObject("Nije moguce naci trazeni artikal.");
		}
		return r;
	}
	
	@RequestMapping(path = "/id/{artikalId}", method = RequestMethod.GET)
	public ServerResponse vratiArtikal (@PathVariable Long artikalId) {
		ServerResponse r = new ServerResponse();
		Integer id = (int) (long) artikalId;
		Artikal artikal;
		try {
			artikal = artikalService.vratiPoId(id);
			artikal.getKomentari();
			r.setStatus("USPESNO");
			r.setObject(artikal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r.setStatus("GRESKA");
			r.setObject("Nije moguce naci trazeni artikal.");
		}
		return r;
	}
	
	@RequestMapping(path = "/artikli/prodavacId/{prodavacId}", method = RequestMethod.GET)
	public ServerResponse vratiArtikal (@PathVariable Integer prodavacId) {
		ServerResponse r = new ServerResponse();

		List<ArtikalOsnovnoDto> artikli = artikalService.vratiArtiklePremaKorisniku(prodavacId);
		r.setStatus("USPESNO");
		r.setObject(artikli);
		
		return r;
	}
	
	@RequestMapping(path = "/komentari/{artikalId}", method = RequestMethod.GET)
	public ServerResponse vratiKomentareZaArtikal (@PathVariable Integer artikalId) {
		ServerResponse r = new ServerResponse();
		Artikal a;
		try {
			a = artikalService.vratiPoId(artikalId);
			List<Komentar> komentari = komentarService.vratiKomentareArtikla(a);
			r.setStatus("USPESNO");
			r.setObject(komentari);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r.setStatus("GRESKA");
			r.setObject(null);	
		}
		
		return r;
	}
	@RequestMapping(path = "/{artikalId}/istakni", method = RequestMethod.GET)
	public ServerResponse promeniUPromovisan (@PathVariable Long artikalId) {
		ServerResponse r = new ServerResponse();
		Integer id = (int) (long) artikalId;
		Artikal artikal;
		try {
			artikal = artikalService.vratiPoId(id);
			artikal.setZaPromovisanje(true);
			artikal.setAktivan(true);
			artikalService.sacuvaj(artikal);
			r.setPoruka("Artikal uspesno postavljen kao istaknuti artikal");
			r.setStatus("USPESNO");
			r.setObject(artikal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r.setPoruka("Sistem ne moze da pronadje artikal.");
			r.setStatus("GRESKA");
		}	
		return r;
	}
	
	@RequestMapping(path = "/artikli/informacije", method = RequestMethod.GET)
	public ServerResponse vratiInformacije () {
		ServerResponse r = new ServerResponse();

		int aktivni = artikalService.vratiBrojAktivnih();
		int neaktivni = artikalService.vratiBrojNeAktivnih();
		r.setStatus("USPESNO");
		AdminPanelInfoDto apid = new AdminPanelInfoDto();
		apid.setBrojAktivnihArtikala(aktivni);
		apid.setBrojNeaktivnihArtikala(neaktivni);
		r.setObject(apid);
		
		return r;
	}
	@RequestMapping(path = "/artikli/dodajKomentar/{artikalId}", method = RequestMethod.POST)
	public ServerResponse dodajKomentar (@RequestBody NoviKomentarDto komentar, @PathVariable Integer artikalId) {
		ServerResponse r = new ServerResponse();
		Komentar kom = new Komentar();
		String email = komentar.getUsername();
		System.out.println("Mail: "+email);
		Korisnik k = korisnikService.vratiProdavcaPoEmail(email);
		try {
			Artikal a = artikalService.vratiPoId(artikalId);
			kom.setKorisnik(k);
			kom.setDatumUnosa(new Date());
			kom.setPoruka(komentar.getKomentar());
			kom.setArtikal(a);
			kom = artikalService.sacuvajKomentar(kom);
			r.setStatus("USPESNO");
			r.setObject(kom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			r.setStatus("GRESKA");
			r.setObject(kom);
		}

		return r;
	}
	
}
