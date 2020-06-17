package fon.elab.prodavnica.rep;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Korpa;
import fon.elab.prodavnica.domain.Narudzbina;
import fon.elab.prodavnica.domain.Slika;

public interface NarudzbenicaRepository extends JpaRepository<Narudzbina, Integer> {
	List<Narudzbina> findByKupac(Korisnik korisnik);
	List<Narudzbina> findByOdobrena(boolean odobrena);
	@Query(value = "SELECT sum(n.ukupnaVrednost) FROM Narudzbina n")
    Double sumUkupnaVrednost();
	
}
