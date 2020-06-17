package fon.elab.prodavnica.dao;

import fon.elab.prodavnica.domain.StavkaKorpe;

public interface StavkaKorpeService {
	void sacuvaj(StavkaKorpe sk);
	StavkaKorpe vratiPoId(Integer id) throws Exception;
	StavkaKorpe vratiStavkuPoIdArtiklaIKorpe(Integer artikalId, Integer korpaId) throws Exception;
	void obrisiPoId(Integer id);
}
