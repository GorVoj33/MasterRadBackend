package fon.elab.prodavnica.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fon.elab.prodavnica.dao.KorisnikService;
import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Korpa;
import fon.elab.prodavnica.domain.Kupac;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.rep.KorisnikRepository;
import fon.elab.prodavnica.rep.KupacRepository;
import fon.elab.prodavnica.rep.ProdavacRepository;

@Service
@Transactional
public class KorisnikServiceImpl implements KorisnikService{
	@Autowired
	KorisnikRepository korisnikRepository;
	@Autowired
	ProdavacRepository prodavacRepository;
	@Autowired
	KupacRepository kupacRepo;
	@Override
	public List<Korisnik> vratiSve() {
		
		return korisnikRepository.findAll();
	}

	@Override
	public Korisnik vratiPoId(Integer id) throws Exception {
		Optional<Korisnik> k = korisnikRepository.findById(id);
		if(k.isPresent()) return k.get();
		throw new Exception("Korisnik sa unetim ID ne postoji.");
	}

	@Override
	public void sacuvaj(Korisnik k) {
		korisnikRepository.save(k);
		
	}

	@Override
	public void korpa(Korpa korpaId, int korisnikId) {
		korisnikRepository.korpa(korpaId, korisnikId);
		
	}

	@Override
	public List<Korisnik> vratiKorisnikaPoPrezimenu(String prezime) {
		return korisnikRepository.findByPrezime(prezime);
	}

	@Override
	public List<Prodavac> vratiSveProdavce() {
		return prodavacRepository.findAll();
	}

	@Override
	public Prodavac vratiProdavcaPoId(Integer id) {
		Optional<Prodavac> opt = prodavacRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	@Override
	public List<Kupac> vratiSveKupce() {
		return kupacRepo.findAll();
	}

	@Override
	public List<Kupac> vratiSveOdobreneKupce() {
		return kupacRepo.findByOdobren(true);
	}

	@Override
	public List<Kupac> vratiSveOdbijeneKupce() {
		// TODO Auto-generated method stub
		return kupacRepo.findByOdobren(false);
	}

	@Override
	public List<Prodavac> vratiSveOdobreneProdavce() {
		return prodavacRepository.findByOdobren(true);
	}

	@Override
	public List<Prodavac> vratiSveOdbijeneProdavce() {
		return prodavacRepository.findByOdobren(false);
	}

}
