package fon.elab.prodavnica.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fon.elab.prodavnica.domain.Kupac;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.domain.Slika;

public interface ProdavacRepository extends JpaRepository<Prodavac, Integer>{
	List<Prodavac> findByOdobren(boolean odobren);
}
