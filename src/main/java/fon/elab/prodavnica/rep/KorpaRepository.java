package fon.elab.prodavnica.rep;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fon.elab.prodavnica.domain.Korpa;
import fon.elab.prodavnica.dtos.KategorijaBezSlikeDto;

public interface KorpaRepository extends JpaRepository<Korpa, Integer> {

}
