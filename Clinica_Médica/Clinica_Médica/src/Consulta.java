import java.io.Serializable;
import java.time.LocalDateTime;

public class Consulta implements Serializable {
	private LocalDateTime data;
	private Profissional p;
	private Utente u;

	public Consulta(Utente u, Profissional p, LocalDateTime data) {
		this.data = data;
		this.p = p;
		this.u = u;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public Utente getUtente() {
		return u;
	}
	
	public void setUtente(Utente u) {
		this.u = (Utente) u.clone();
	}
	
	public Profissional getProfissional() {
		return p;
	}
	
	public void setProfissional(Profissional p) {
		this.p = (Profissional) p.clone();
	}

	public String toString() {
		return "Consulta: Dia " + data + " com o Profissional " + p.getNome() + " para o Utente " + u.getNome();
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Consulta temp = (Consulta) obj;
			return data.equals(temp.data) && p.equals(temp.p) && u.equals(temp.u);
		}
		return false;
	}

	public Object clone() {
		Consulta temp = new Consulta((Utente) u.clone(), (Profissional) p.clone(), data);
		return temp;
	}

}
