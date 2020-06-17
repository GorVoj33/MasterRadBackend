package fon.elab.prodavnica.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
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
import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.domain.Slika;
import fon.elab.prodavnica.dtos.ArtikalOsnovnoDto;
import fon.elab.prodavnica.dtos.KategorijaBezSlikeDto;
import fon.elab.prodavnica.dtos.KategorijaSaArtiklimaDto;
import fon.elab.prodavnica.dtos.NovaKategorijaDto;
import fon.elab.prodavnica.dtos.ServerResponse;
import fon.elab.prodavnica.rep.SlikaRepository;
import fon.elab.prodavnica.rep.ArtikalRepository;
import fon.elab.prodavnica.rep.KategorijaRepository;
import util.Utility;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class KategorijaController {

	@Autowired
	KategorijaService kategorijaService;
	
	@Autowired
	ArtikalService artikalService;
	
	@Autowired
	SlikaRepository slikaRepository;
	
	@RequestMapping(path="/kategorija/vratiSve", method = RequestMethod.GET)
	public List<Kategorija> vratiSve (){
		
		return kategorijaService.vratiSve();
	}
	@RequestMapping(path="/kategorija/{kategorijaId}", method = RequestMethod.GET)
	public Kategorija vratiPodatkeOKategoriji (@PathVariable Integer kategorijaId){
		
		//List<KategorijaSaArtiklimaDto> lista = kategorijaService.vratiSve();
		try {
			Kategorija kategorija = kategorijaService.vratiPoId(kategorijaId);
			final Optional<Slika> retrievedImage = slikaRepository.findByNaziv(kategorija.getNaziv());
			Slika img = new Slika(
					retrievedImage.get().getNaziv(), 
					retrievedImage.get().getTip(), 
					Utility.decompressBytes(retrievedImage.get().getPicByte()),retrievedImage.get().getNaziv_objekta());
			kategorija.setSlika(img);
			return kategorija;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	@RequestMapping(path="/kategorija/{kategorijaId}/artikli", method = RequestMethod.GET)
	public List<KategorijaBezSlikeDto> vratiSveKategorijeBezSlike (@PathVariable Integer kategorijaId){
		
		//List<KategorijaSaArtiklimaDto> lista = kategorijaService.vratiSve();
		try {
			Kategorija kategorija = kategorijaService.vratiPoId(kategorijaId);
			List<ArtikalOsnovnoDto> lista = new ArrayList<>();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<KategorijaBezSlikeDto> list = new ArrayList<KategorijaBezSlikeDto>();
		
		return list;
	}
	 
	@RequestMapping(path="/kategorija/vratiSveKategorijeBezSlike", method = RequestMethod.GET)
	public List<KategorijaBezSlikeDto> vratiSveKategorijeBezSlike (){
		
		List<Kategorija> lista = kategorijaService.vratiSve();
		List<KategorijaBezSlikeDto> list = new ArrayList<KategorijaBezSlikeDto>();
		for(Kategorija k : lista) {
			list.add(new KategorijaBezSlikeDto(k.getId(), k.getNaziv()));
			
		}
		return list;
	}
	
	
	@RequestMapping(path = "/kategorija/dodajKategoriju", method = RequestMethod.POST)
	public Kategorija dodajKategoriju (@RequestBody NovaKategorijaDto novaKategorija) {
		System.out.println("Cuvanje nova kategorija");
		System.out.println(novaKategorija);
		Kategorija kategorija = new Kategorija();
		kategorija.setNaziv(novaKategorija.getNaziv());
		kategorija.setSlika(null);
		//k.setSlika(slika);
		kategorijaService.sacuvaj(kategorija);
		System.out.println("Nova kategorija: "+kategorija);
		return kategorija;
	}
	
	
	@RequestMapping(path = "/kategorija/{katId}/sacuvajSliku", method = RequestMethod.POST)
	public ServerResponse sacuvajSlikuKategorije(@PathVariable Integer katId, @RequestParam("imageFile") MultipartFile file) {
		Slika img;
		ServerResponse r = new ServerResponse();
		System.out.println("Stigao zahtev za cuvanje slike kategorije!");
//		Kategorija kategorija = kategorijaService.vratiPoId(katId);
//		kategorija.setSlika(img);
		
		//BodyBuilder response = (BodyBuilder) ResponseEntity.status(HttpStatus.OK).build();
		try {
			//String noviNaziv = Utility.getInstance().generisiNoviNazivSlike();
//			img = new Slika(file.getOriginalFilename(), file.getContentType(),
//					Utility.getInstance().compressBytes(file.getBytes()), "kategorija");
			img = new Slika(file.getOriginalFilename(), file.getContentType(),
					file.getBytes(), "kategorija");
			slikaRepository.save(img);
			Kategorija kategorija;
			try {
				kategorija = kategorijaService.vratiPoId(katId);
				kategorija.setSlika(img);
				kategorijaService.sacuvaj(kategorija);
				//r.setObject(kategorija);
			} catch (Exception e) {
				r.setStatus("ERROR");
				r.setPoruka("Kategorija nije sacuvana, pokusajte sa nekom drugom slikom. ");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			r.setStatus("OK");
			r.setPoruka("Kategorija je uspesno sacuvana. ");
		} catch (IOException e) {
			//response = (BodyBuilder) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
			e.printStackTrace();
			r.setStatus("ERROR");
			r.setPoruka("Kategorija nije sacuvana, pokusajte sa nekom drugom slikom. ");
		}
		
		return r;
	}
	@RequestMapping(path = "/kategorija/osnovno", method = RequestMethod.GET)
	public ServerResponse vratiOsnovnoOKategorijama() {
		ServerResponse sr = new ServerResponse();
		sr.setPoruka("Uspesno");
		sr.setStatus("OK");
		sr.setObject(kategorijaService.vratiOsnovneInformacije());
		
		return sr;
	}
	
}
