package fon.elab.prodavnica.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fon.elab.prodavnica.dao.KorpaService;
import fon.elab.prodavnica.domain.Korpa;
import fon.elab.prodavnica.rep.KorpaRepository;
@Component
public class KorpaServiceImpl implements KorpaService{
	@Autowired
	KorpaRepository korpaRepository;
	
	@Override
	public Korpa vratiPoID(Integer id) throws Exception {
		Optional<Korpa> k = korpaRepository.findById(id);
		if(k.isPresent()) {
			return k.get();
		}
		throw new Exception("Korpa sa unetim ID ne postoji.");
	}

	@Override
	public void sacuvaj(Korpa k) {
		korpaRepository.save(k);
	}

}
