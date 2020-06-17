package fon.elab.prodavnica.dao;

import java.util.*;

import fon.elab.prodavnica.domain.Grad;
public interface GradService {
	List<Grad> vratiSve();
	Grad vratiPoId(Integer id);
	void sacuvaj (Grad grad);
	
}
