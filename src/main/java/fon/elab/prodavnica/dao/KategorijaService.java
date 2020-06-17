package fon.elab.prodavnica.dao;

import java.util.List;

import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.dtos.KategorijaBezSlikeDto;

public interface KategorijaService {
	List<Kategorija> vratiSve();
	Kategorija vratiPoId(Integer id) throws Exception;
	void sacuvaj (Kategorija kategorija);
	List<KategorijaBezSlikeDto>vratiOsnovneInformacije();
}
