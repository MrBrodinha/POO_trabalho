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

	public String toString() {
		return "Data da Consulta: " + data + "\nCom o Profissional: " + p.getNome() + "\nPara o utente: " + u.getNome();
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
