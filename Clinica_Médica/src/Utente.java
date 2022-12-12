import java.io.Serializable;

@SuppressWarnings("serial")
public class Utente implements Serializable {
	private String nome;
	private char genero; // F ou M
	private long NUS; // nº de utente de saúde;
	private Profissional medico_familia;
	private Ficha_Tecnica ft;

	public Utente(String nome, long NUS, char genero) {
		this.nome = nome;
		this.NUS = NUS;
		this.genero = genero;
		medico_familia = new Profissional();
		ft = new Ficha_Tecnica(nome, NUS);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
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
		return "NUS: " + NUS + ", Nome: " + nome + ", Género: " + genero;
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Utente temp = (Utente) obj;
			return nome.equals(temp.nome) && NUS == temp.NUS && genero == temp.genero
					&& medico_familia.equals(temp.medico_familia) && ft.equals(temp.ft);
		}
		return false;
	}

	public Object clone() {
		Utente temp = new Utente(nome, NUS, genero);
		temp.medico_familia = (Profissional) medico_familia.clone();
		temp.ft = (Ficha_Tecnica) ft.clone();
		return temp;
	}

}