import java.io.Serializable;

@SuppressWarnings("serial")
public class Utente extends Pessoa implements Serializable {
	private long NUS;
	private Profissional medico_familia;
	private Ficha_Tecnica ft;

	public Utente(Pessoa p, long NUS) {
		super(p.nome, p.genero);
		medico_familia = new Profissional();
		this.NUS = NUS;
		ft = new Ficha_Tecnica(new Pessoa(p.nome, p.genero), NUS);
	}

	public long getNUS() {
		return NUS;
	}

	public void setNUS(long NUS) {
		this.NUS = NUS;
	}

	public Profissional getMedicoFamilia() {
		return medico_familia;
	}

	public void setMedicoFamilia(Profissional m_f) {
		medico_familia = (Profissional) m_f.clone();
	}

	public Ficha_Tecnica getFT() {
		return ft;
	}

	public void setFT(Ficha_Tecnica ft) {
		this.ft = (Ficha_Tecnica) ft.clone();
	}

	// mexer no final quando tiver tudo mais feito
	public String toString() {
		return "NUS: " + NUS + ", Nome: " + super.nome + ", Género: " + super.genero;
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Utente temp = (Utente) obj;
			return super.equals(temp) && NUS == temp.NUS
					&& medico_familia.equals(temp.medico_familia) && ft.equals(temp.ft);
		}
		return false;
	}

	public Object clone() {
		Utente temp = new Utente(new Pessoa(super.nome, super.genero), NUS);
		temp.medico_familia = (Profissional) medico_familia.clone();
		temp.ft = (Ficha_Tecnica) ft.clone();
		return temp;
	}

}