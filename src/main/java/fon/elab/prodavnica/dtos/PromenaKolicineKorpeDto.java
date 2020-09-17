package fon.elab.prodavnica.dtos;

public class PromenaKolicineKorpeDto {
	Integer stavkaId;
	Integer artikalId;
	Integer staraKolicina;
	Integer novaKolicina;
	
	public PromenaKolicineKorpeDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getStavkaId() {
		return stavkaId;
	}

	public void setStavkaId(Integer stavkaId) {
		this.stavkaId = stavkaId;
	}

	public Integer getArtikalId() {
		return artikalId;
	}

	public void setArtikalId(Integer artikalId) {
		this.artikalId = artikalId;
	}

	public Integer getStaraKolicina() {
		return staraKolicina;
	}

	public void setStaraKolicina(Integer staraKolicina) {
		this.staraKolicina = staraKolicina;
	}

	public Integer getNovaKolicina() {
		return novaKolicina;
	}

	public void setNovaKolicina(Integer novaKolicina) {
		this.novaKolicina = novaKolicina;
	}

	@Override
	public String toString() {
		return "PromenaKolicineKorpeDto [stavkaId=" + stavkaId + ", artikalId=" + artikalId + ", staraKolicina="
				+ staraKolicina + ", novaKolicina=" + novaKolicina + "]";
	}
	
	
}
