package fon.elab.prodavnica.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fon.elab.prodavnica.dao.KategorijaService;
import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.dtos.KategorijaBezSlikeDto;
import fon.elab.prodavnica.rep.KategorijaRepository;
@Component
public class KategorijaServiceImpl implements KategorijaService {
	@Autowired
	KategorijaRepository kategorijaRepository;
	@Override
	public List<Kategorija> vratiSve() {
		return kategorijaRepository.findAll();
	}

	@Override
	public Kategorija vratiPoId(Integer id) throws Exception {
		Optional<Kategorija> kat = kategorijaRepository.findById(id);
		if(kat.isPresent()) {
			return kat.get();
		}
		throw new Exception("Kategorija sa unetim ID ne postoji.");
	}

	@Override
	public void sacuvaj(Kategorija kategorija) {
		kategorijaRepository.save(kategorija);	
	}

	@Override
	public List<KategorijaBezSlikeDto> vratiOsnovneInformacije() {
		return kategorijaRepository.vratiOsnovneInformacije();
	}
	
}
