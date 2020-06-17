package fon.elab.prodavnica.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Kupac;

public interface KupacRepository extends JpaRepository<Kupac, Integer>{
	List<Kupac> findByOdobren(boolean odobren);
}
