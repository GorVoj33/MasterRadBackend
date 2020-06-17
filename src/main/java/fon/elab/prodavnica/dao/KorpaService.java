package fon.elab.prodavnica.dao;

import fon.elab.prodavnica.domain.Korpa;

public interface KorpaService {
	Korpa vratiPoID(Integer id) throws Exception;
	void sacuvaj(Korpa k);
}
