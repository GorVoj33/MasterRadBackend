package fon.elab.prodavnica.rep;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fon.elab.prodavnica.domain.Korisnik;
import fon.elab.prodavnica.domain.Korpa;
import fon.elab.prodavnica.domain.Narudzbina;
import fon.elab.prodavnica.domain.Slika;
import fon.elab.prodavnica.dtos.ArtikalOsnovnoDto;
import fon.elab.prodavnica.dtos.MesecnaStatistikaDto;

public interface NarudzbenicaRepository extends JpaRepository<Narudzbina, Integer> {
	 	
	List<Narudzbina> findByKupac(Korisnik korisnik);
	List<Narudzbina> findByProdavac(Korisnik korisnik);
	List<Narudzbina> findByOdobrena(boolean odobrena);
	@Query(value = "SELECT sum(n.ukupnaVrednost) FROM Narudzbina n WHERE n.datumOdobrenja != null")
    Double sumUkupnaVrednost();
	@Query(value = "SELECT count(*) FROM narudzbina WHERE grad_id = ?1", nativeQuery = true)
	int vratiUkupanBrojZaGrad(Integer ptt);
	@Query(value="SELECT sum(n.ukupna_vrednost) FROM Narudzbina n WHERE n.odobrena=TRUE AND Month(n.datum_odobrenja)=?1 ", nativeQuery = true)
	Double vratiMesecneStatistike(int mesec);
	@Query(value="SELECT count(n.ukupna_vrednost) FROM Narudzbina n WHERE n.odobrena=TRUE AND Month(n.datum_odobrenja)=?1 ", nativeQuery = true)
	Integer vratiMesecniBrojNarudzbi(int mesec);
//	@Query(value="SELECT new fon.elab.prodavnica.dtos.MesecnaStatistikaDto(n.godina,n.mesec,n.broj, n.suma) FROM Narudzbina n WHERE n.odobrena=TRUE GROUP BY EXTRACT(MONTH FROM n.datum_odobrenja)", nativeQuery = true)
//	public List<MesecnaStatistikaDto> vratiMesecneStatistike();
	
	// SELECT EXTRACT(YEAR FROM datum_odobrenja) AS godina, EXTRACT(MONTH FROM datum_odobrenja) AS mesec, COUNT(ukupna_vrednost) FROM narudzbina WHERE odobrena=TRUE GROUP BY EXTRACT(MONTH FROM datum_odobrenja)
//	@Query("SELECT new fon.elab.prodavnica.dtos.MesecnaStatistikaDto(godina,mesec,broj, suma) FROM Narudzbina n WHERE odobrena=TRUE GROUP BY EXTRACT(MONTH FROM datum_odobrenja)")
//	public List<MesecnaStatistikaDto> vratiMesecneStatistike();
	
//	@Query(value = "SELECT EXTRACT(YEAR FROM datum_odobrenja) AS godina, EXTRACT(MONTH FROM datum_odobrenja) AS mesec, COUNT(ukupna_vrednost) FROM narudzbina WHERE odobrena=TRUE GROUP BY EXTRACT(MONTH FROM datum_odobrenja)", nativeQuery = true)
//	public List<MesecnaStatistikaDto> vratiMesecneStatistike();
	
//	default List<MesecnaStatistikaDto> ucitajStatistikeMesecne() {
//		
//		Transaction tx = session.beginTransaction();
//		SQLQuery query = getSessionFactory().createSQLQuery("select emp_id, emp_name, emp_salary from Employee");
//		List<Object[]> rows = query.list();
//		for(Object[] row : rows) {
//			row[0].toString();
//			
//		}
//	}
}
