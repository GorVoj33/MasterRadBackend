package fon.elab.prodavnica.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fon.elab.prodavnica.dao.SlikaService;
import fon.elab.prodavnica.domain.Slika;
import fon.elab.prodavnica.rep.SlikaRepository;
@Component
public class SlikaServiceImpl implements SlikaService {
	@Autowired
	SlikaRepository slikaRepository;

	@Override
	public void sacuvaj(Slika slika) {
		slikaRepository.save(slika);
	}
	
	
}
