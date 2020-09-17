package fon.elab.prodavnica.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fon.elab.prodavnica.dao.GradService;
import fon.elab.prodavnica.domain.Grad;
import fon.elab.prodavnica.rep.GradRepository;
@Service
@Transactional
public class GradServiceImpl implements GradService{
	@Autowired
	GradRepository gradRepo;
	@Override
	public List<Grad> vratiSve() {
		return gradRepo.findAll();
	}

	@Override
	public Grad vratiPoId(Integer id) {
		return gradRepo.findById(id).get();
	}

	@Override
	public void sacuvaj(Grad grad) {
		gradRepo.save(grad);
		
	}

	@Override
	public void updateBrojKorisnika(Integer noviBrojKorisnika, Integer gradId) {
		gradRepo.updateBrojKorisnika(noviBrojKorisnika, gradId);
	}

}
