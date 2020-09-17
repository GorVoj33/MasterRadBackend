package fon.elab.prodavnica.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fon.elab.prodavnica.domain.Grad;
import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Korpa;

public interface GradRepository extends JpaRepository<Grad, Integer>{
	@Modifying
	@Query("UPDATE Grad g SET g.brojKorisnika=?1 WHERE g.ptt=?2")
	public void updateBrojKorisnika(Integer noviBrojKorisnika, Integer gradId);
}
