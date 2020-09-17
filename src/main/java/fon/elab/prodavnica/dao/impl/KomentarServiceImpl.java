package fon.elab.prodavnica.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fon.elab.prodavnica.dao.KomentarService;
import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Komentar;
import fon.elab.prodavnica.rep.KomentarRepository;

@Service
public class KomentarServiceImpl implements KomentarService {
	@Autowired
	KomentarRepository komentarRepository;
	@Override
	public List<Komentar> vratiKomentareArtikla(Artikal a) {
		return komentarRepository.findByArtikal(a);
	}

	@Override
	public void sacuvaj(Komentar komentar) {
		komentarRepository.save(komentar);
	}

}
