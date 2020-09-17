package fon.elab.prodavnica.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Komentar;

public interface KomentarService {
	List<Komentar> vratiKomentareArtikla(Artikal a);
	void sacuvaj (Komentar komentar);
}
