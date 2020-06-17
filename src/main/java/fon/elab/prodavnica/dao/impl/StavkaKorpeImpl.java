package fon.elab.prodavnica.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fon.elab.prodavnica.dao.StavkaKorpeService;
import fon.elab.prodavnica.domain.StavkaKorpe;
import fon.elab.prodavnica.rep.StavkaKorpeRepository;
@Component
public class StavkaKorpeImpl implements StavkaKorpeService {
	@Autowired
	StavkaKorpeRepository stavkaKorpeRepository;

	@Override
	public void sacuvaj(StavkaKorpe sk) {
		stavkaKorpeRepository.save(sk);		
	}

	@Override
	public StavkaKorpe vratiPoId(Integer id) throws Exception {
		Optional<StavkaKorpe> sk = stavkaKorpeRepository.findById(id);
		if(sk.isPresent()) return sk.get();
		throw new Exception("Stavka korpe sa unetim ID ne postoji.");
	}

	@Override
	public StavkaKorpe vratiStavkuPoIdArtiklaIKorpe(Integer artikalId, Integer korpaId) throws Exception {
//		return stavkaKorpeRepository.vratiStavkuPremaIDArtiklaIKorpe(artikalId, korpaId);
		return null;
	}

	@Override
	public void obrisiPoId(Integer id) {
		stavkaKorpeRepository.deleteById(id);	
	}
	
}
