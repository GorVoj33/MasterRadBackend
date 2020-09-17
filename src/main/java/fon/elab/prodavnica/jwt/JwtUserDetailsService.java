package fon.elab.prodavnica.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fon.elab.prodavnica.domain.Administrator;
import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Kupac;
import fon.elab.prodavnica.domain.Prodavac;
import fon.elab.prodavnica.rep.AdministratorRepository;
import fon.elab.prodavnica.rep.KorisnikRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	KorisnikRepository korisnikRepo;
	@Autowired
	AdministratorRepository adminRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	
	Optional<Korisnik> korisnikOpt = korisnikRepo.findByEmail(username);
	if(korisnikOpt.isPresent()) {
		Korisnik korisnik = korisnikOpt.get();
		System.out.println("Ovo je user: "+korisnik.getEmail() + " ime:"+korisnik.getIme());
		String role = "";
		//String role = user.isAdmin() ? "admin" : "user";
		if (korisnik.getClass() == Prodavac.class) {
			role = "prodavac";
			
		}else if (korisnik.getClass() == Kupac.class) {
			role = "kupac";
			
		}
		if(!korisnik.isOdobren()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_ENABLED '%s'.", username));
		}
		Integer cartId = korisnik.getId();
		UserDetails ud = new JwtUserDetails(korisnik.getId(), korisnik.getEmail(), korisnik.getLozinka(), role, cartId , korisnik.getIme());
	    return ud;	
	} else {
		Optional<Administrator> adminOpt = adminRepo.findByKorisnickoIme(username);
		if(adminOpt.isPresent()) {
			
			Administrator admin = adminOpt.get();
			System.out.println("Admin pronadjen: "+admin.getKorisnickoIme());
			if(admin != null) {
				String role = "admin";
				UserDetails ud = new JwtUserDetails(admin.getId(), admin.getKorisnickoIme(), admin.getLozinka(), role, 0 , admin.getKorisnickoIme());
				return ud;
			}	
		}
		throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		
	}
  }
}


