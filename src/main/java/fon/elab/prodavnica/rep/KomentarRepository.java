package fon.elab.prodavnica.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fon.elab.prodavnica.domain.Artikal;
import fon.elab.prodavnica.domain.Komentar;

public interface KomentarRepository extends JpaRepository<Komentar, Integer>{
	List<Komentar> findByArtikal(Artikal artikal);
}
