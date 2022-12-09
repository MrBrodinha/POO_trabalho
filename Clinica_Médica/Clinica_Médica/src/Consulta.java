import java.io.Serializable;
import java.time.LocalDateTime;

public class Consulta implements Serializable{
	private LocalDateTime data;
	private String nomeU, nomeP;
	private long numeroU, numeroP;
	
	public Consulta(String nomeU, long numeroU, String nomeP, long numeroP, LocalDateTime d){
		data = d;
		this.nomeU = nomeU;
		this.numeroU = numeroU;
		this.nomeP = nomeP;
		this.numeroP = numeroP;
	}

	public void addConsulta(Utente u, Profissional p, LocalDateTime data) throws ConsultaInvalida{
		
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
	
	public long getNumeroU() {
		return numeroU;
	}

	public void setNumeroU(long numeroU) {
		this.numeroU = numeroU;
	}

	public long getNumeroP() {
		return numeroP;
	}

	public void setNumeroP(long numeroP) {
		this.numeroP = numeroP;
	}

	public String toString() {
		return "Data da Consulta: " + data + "\nCom o Profissional: " + nomeP + "\nPara o utente: " + nomeU;
	}
	
	public boolean equals(Object obj) {
		if(obj != null && obj.getClass() == this.getClass()) {
			Consulta temp = (Consulta) obj;
			return data.equals(temp.data) &&  nomeU.equals(temp.nomeU) && nomeP.equals(temp.nomeP) && 
					numeroP == temp.numeroP && numeroU == temp.numeroP;
		}
		return false;
	}

	public Object clone() {
		Consulta temp = new Consulta(nomeU, numeroU,nomeP,numeroP,data);
		return temp;
	}
	
	}
