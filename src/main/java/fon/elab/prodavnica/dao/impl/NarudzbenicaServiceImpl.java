package fon.elab.prodavnica.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.elab.prodavnica.dao.NarudzbenicaService;
import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Narudzbina;
import fon.elab.prodavnica.rep.NarudzbenicaRepository;
@Service
public class NarudzbenicaServiceImpl implements NarudzbenicaService{
	@Autowired
	NarudzbenicaRepository narudzbenicaRepository;
	@Override
	public void sacuvaj(Narudzbina n) {
		// TODO Auto-generated method stub
		narudzbenicaRepository.save(n);
	}
	@Override
	public List<Narudzbina> vratiPremaKupcu(Korisnik korisnik) {
		return narudzbenicaRepository.findByKupac(korisnik);
	}
	@Override
	public Narudzbina vratiPoId(Integer id) {
		Optional<Narudzbina> n = narudzbenicaRepository.findById(id);
		if(n.isPresent()) return n.get();
		else return null;
	}
	@Override
	public List<Narudzbina> vratiPremaAtributuOdobrena(boolean odobrena) {
		return narudzbenicaRepository.findByOdobrena(odobrena);
	}
	@Override
	public Double vratiSumu() {
		return narudzbenicaRepository.sumUkupnaVrednost();
	}

}
