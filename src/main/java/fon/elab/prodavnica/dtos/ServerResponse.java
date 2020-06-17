package fon.elab.prodavnica.dtos;

public class ServerResponse {
	String poruka;
	String status;
	Object object;
	public ServerResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ServerResponse(String poruka, String status) {
		super();
		this.poruka = poruka;
		this.status = status;
	}

	public String getPoruka() {
		return poruka;
	}
	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
}
