package fon.elab.prodavnica.dtos;
import java.util.*;
public class NoviKomentarDto {
	String komentar;
	Date datum;
	String username;
	
	public NoviKomentarDto() {
		// TODO Auto-generated constructor stub
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
