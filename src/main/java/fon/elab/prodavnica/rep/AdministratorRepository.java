package fon.elab.prodavnica.rep;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fon.elab.prodavnica.domain.Administrator;
import fon.elab.prodavnica.domain.Artikal;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{
	Optional<Administrator> findByKorisnickoIme(String korisnickoIme);
}
