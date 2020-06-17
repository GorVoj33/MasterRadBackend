package fon.elab.prodavnica.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.domain.Slika;
import fon.elab.prodavnica.domain.StavkaKorpe;
import fon.elab.prodavnica.dtos.ArtikalOsnovnoDto;
import fon.elab.prodavnica.dtos.KategorijaBezSlikeDto;

public interface ArtikalRepository extends JpaRepository<Artikal, Integer>{
//	@Query("SELECT * FROM artikal a WHERE a.kategorija_id=:zadati_id")
//	public List<Artikal> vratiArtikleIDKategorije(@Param("zadati_id") Integer id);
	
	
	@Query("SELECT new fon.elab.prodavnica.dtos.ArtikalOsnovnoDto(a.id,a.naziv,a.cena,a.slika,a.kategorija,a.slanjeKuriromMoguce) FROM Artikal a WHERE a.prodavac.id = ?1")
	public List<ArtikalOsnovnoDto> vratiOsnovneInformacijePremaProdavacID(Integer id);
	
	@Query(value = "SELECT count(a.id) FROM Artikal a WHERE a.aktivan=?1")
    Integer vratiBrojAktivnihINeaktivnih(boolean aktivan);
	
	List<Artikal> findByProdavac(Prodavac prodavac);
	
	
}
