package fon.elab.prodavnica.rep;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fon.elab.prodavnica.domain.Grad;
import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Korpa;
import fon.elab.prodavnica.dtos.KategorijaBezSlikeDto;
@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	@Modifying
	@Query("UPDATE Korisnik k SET k.korpa=?1 WHERE k.id=?2")
	public void korpa(Korpa korpaId, Integer korisnikId);
	List<Korisnik> findByPrezime(String prezime);

	@Query("SELECT TYPE(k) FROM Korisnik k WHERE k.id=?1")
	public Object vratiTip(Integer id);
	
	Optional<Korisnik> findByEmail(String email);
//	@Query("SELECT new fon.elab.prodavnica.Korisnik() FROM Korisnik k WHERE TYPE(k)='Prodavac'")
//	public List<Korisnik> vratiProdavce();
	@Query(value = "SELECT count(*) FROM korisnik WHERE dtype='Prodavac' AND grad_id = ?1", nativeQuery = true)
	public int vratiBrojProdavacaZaGrad(int gradId);

}
