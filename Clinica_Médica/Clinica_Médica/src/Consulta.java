import java.io.Serializable;
import java.time.LocalDateTime;

public class Consulta implements Serializable{
	private LocalDateTime data;
	private String nomeU;
	private String nomeP;
	
	public Consulta(String u, String p, LocalDateTime d){
		data = d;
		nomeU = u;
		nomeP = p;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getNomeU() {
		return nomeU;
	}

	public void setNomeU(String nomeU) {
		this.nomeU = nomeU;
	}

	public String getNomeP() {
		return nomeP;
	}

	public void setNomeP(String nomeP) {
		this.nomeP = nomeP;
	}
	
	public String toString() {
		return "Data da Consulta: " + data + "\nCom o Profissional: " + nomeP + "\nPara o utente: " + nomeU;
	}
	
	public boolean equals(Object obj) {
		if(obj != null && obj.getClass() == this.getClass()) {
			Consulta temp = (Consulta) obj;
			return data.equals(temp.data) &&  nomeU.equals(temp.nomeU) && nomeP.equals(temp.nomeP);
		}
		return false;
	}


	
	}
