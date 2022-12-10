import java.io.Serializable;

public class Utente implements Serializable {
	private String nome;
	private char genero; // F ou M
	private long NUS; //nº de utente de saúde;
	private Profissional medico_familia;
	private Ficha_Tecnica ft;
	
	public Utente(String nome, long NUS, char genero) {
		this.nome = nome;
		this.NUS = NUS;
		this.genero = genero;
		medico_familia = new Profissional();
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

	public Object getMedicoFamilia(){
		return (Profissional) medico_familia;
	}
	
	public void setMedicoFamilia(Object m_f) {
		medico_familia = (Profissional) m_f;
	}
	
	public void criarFT() {
		ft = new Ficha_Tecnica(nome, NUS);
	}
	
	public Ficha_Tecnica getFT() {
		return ft;
	}
	//mexer no final quando tiver tudo mais feito
	public String toString() {
		return "NUS: " + NUS + ", nome: " + nome + ", género: " + genero;
	}
	
	
	public boolean equals(Object obj) {
		if(obj != null && obj.getClass() == this.getClass()) {
			Utente temp = (Utente) obj;
			return nome.equals(temp.nome) && NUS == temp.NUS && genero == temp.genero && medico_familia.equals(temp.medico_familia) && ft.equals(temp.ft);
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