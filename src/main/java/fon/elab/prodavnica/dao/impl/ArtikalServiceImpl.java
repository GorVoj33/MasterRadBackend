package fon.elab.prodavnica.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fon.elab.prodavnica.dao.ArtikalService;
import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.dtos.ArtikalOsnovnoDto;
import fon.elab.prodavnica.rep.ArtikalRepository;
@Component
public class ArtikalServiceImpl implements ArtikalService{
	@Autowired
	ArtikalRepository artikalRepository;
	@Override
	public List<Artikal> vratiSve() {
		return artikalRepository.findAll();
	}

	@Override
	public List<Artikal> vratiSvePoIdKategorije(Integer kategorijaId) {
		return null;
//		return artikalRepository.vratiArtikleIDKategorije(kategorijaId);
	}

	@Override
	public Artikal vratiPoId(Integer id) throws Exception {
		Optional<Artikal> art = artikalRepository.findById(id);
		if(art.isPresent()) {
			return art.get();
		}
		throw new Exception("Artikal sa unetim ID ne postoji.");
	}

	@Override
	public Artikal sacuvaj(Artikal artikal) {
		return artikalRepository.save(artikal);	
	}

	@Override
	public List<ArtikalOsnovnoDto> vratiArtiklePremaKorisniku(Integer id) {
		return artikalRepository.vratiOsnovneInformacijePremaProdavacID(id);
	}

	@Override
	public List<Artikal> vratiSveAktivne() {
		List<Artikal> aktivni = new ArrayList<Artikal>();
		for(Artikal a: artikalRepository.findAll()) {
			aktivni.add(a);
		}
		return aktivni;
	}

	@Override
	public List<Artikal> vratiSveArtikleProdavca(Prodavac prodavac) {
		return artikalRepository.findByProdavac(prodavac);
	}

	@Override
	public Integer vratiBrojAktivnih() {
		return artikalRepository.vratiBrojAktivnihINeaktivnih(true);
	}

	@Override
	public Integer vratiBrojNeAktivnih() {
		return artikalRepository.vratiBrojAktivnihINeaktivnih(false);
	}


}
