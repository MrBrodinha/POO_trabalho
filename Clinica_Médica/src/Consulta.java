import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class Consulta implements Serializable {
	private LocalDateTime data;
	private Profissional p;
	private Utente u;
	private String motivo;

	public Consulta(String motivo, Utente u, Profissional p, LocalDateTime data) {
		this.data = data;
		this.p = (Profissional) p.clone();
		this.u = (Utente) u.clone();
		this.motivo = motivo;
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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String toString() {
		return "\nConsulta: Dia " + data + " com o Profissional \"" + p.getNome() + "\" para o Utente \"" + u.getNome()
				+ "\"\nMotivo: \"" + motivo + "\"";
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Consulta temp = (Consulta) obj;
			return data.equals(temp.data) && p.equals(temp.p) && u.equals(temp.u) && motivo.equals(temp.motivo);
		}
		return false;
	}

	public Object clone() {
		Consulta temp = new Consulta(motivo, (Utente) u.clone(), (Profissional) p.clone(), data);
		return temp;
	}

}
