package fon.elab.prodavnica.dao;

import java.util.List;

import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Komentar;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.dtos.ArtikalOsnovnoDto;

public interface ArtikalService {
	List<Artikal> vratiSve();
	List<Artikal> vratiSveAktivne();
	List<Artikal> vratiSveArtikleProdavca(Prodavac prodavac);
	Integer vratiBrojAktivnih();
	Integer vratiBrojNeAktivnih();
	List<Artikal> vratiSvePoIdKategorije(Integer kategorijaId);
	Artikal vratiPoId(Integer id) throws Exception;
	Artikal sacuvaj(Artikal artikal);
	List<ArtikalOsnovnoDto> vratiArtiklePremaKorisniku(Integer id);
	List<ArtikalOsnovnoDto> vratiArtiklePremaKategoriji(Integer id);
	Komentar sacuvajKomentar(Komentar kom);
}
