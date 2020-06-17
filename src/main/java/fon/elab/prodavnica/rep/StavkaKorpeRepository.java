package fon.elab.prodavnica.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fon.elab.prodavnica.domain.Slika;
import fon.elab.prodavnica.domain.StavkaKorpe;
import fon.elab.prodavnica.dtos.KategorijaBezSlikeDto;

public interface StavkaKorpeRepository extends JpaRepository<StavkaKorpe, Integer> {
//	@Query("SELECT sk FROM stavka_korpe sk WHERE sk.artikal_id=:artikalId AND sk.korpa_id=:korpaId")
//	public StavkaKorpe vratiStavkuPremaIDArtiklaIKorpe(@Param("artikalId") Integer artikalId, @Param("korpaId") Integer korpaId);

//	@Query("SELECT new fon.elab.prodavnica.dtos.StavkaKorpeDto(k.id,k.naziv) FROM Kategorija k")
//	public List<KategorijaBezSlikeDto> vratiOsnovneInformacije();
}
