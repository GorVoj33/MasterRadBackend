package fon.elab.prodavnica.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fon.elab.prodavnica.domain.Slika;
import fon.elab.prodavnica.dtos.KategorijaBezSlikeDto;
import fon.elab.prodavnica.domain.Kategorija;
import fon.elab.prodavnica.domain.Korpa;

public interface KategorijaRepository extends JpaRepository<Kategorija, Integer> {
	@Query("SELECT new fon.elab.prodavnica.dtos.KategorijaBezSlikeDto(k.id,k.naziv) FROM Kategorija k")
	public List<KategorijaBezSlikeDto> vratiOsnovneInformacije();
}
