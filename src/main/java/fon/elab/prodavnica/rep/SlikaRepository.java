package fon.elab.prodavnica.rep;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fon.elab.prodavnica.domain.Slika;


public interface SlikaRepository extends JpaRepository<Slika, Integer> {

	Optional<Slika> findByNaziv(String naziv);
}
