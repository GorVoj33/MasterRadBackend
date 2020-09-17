package fon.elab.prodavnica.dtos;

import java.util.List;

public class StatistikaNarudzbinaDto {
	List<MesecnaStatistikaDto> mesecnaStatistika;
	public StatistikaNarudzbinaDto() {
		// TODO Auto-generated constructor stub
	}
	public List<MesecnaStatistikaDto> getMesecnaStatistika() {
		return mesecnaStatistika;
	}
	public void setMesecnaStatistika(List<MesecnaStatistikaDto> mesecnaStatistika) {
		this.mesecnaStatistika = mesecnaStatistika;
	}
	@Override
	public String toString() {
		return "StatistikaNarudzbinaDto [mesecnaStatistika=" + mesecnaStatistika + "]";
	}
	
	
	
}
