import java.io.Serializable;

@SuppressWarnings("serial")
public class Pessoa implements Serializable{
	protected String nome;
	protected char genero;
	
	public Pessoa(String nome, char genero) {
		this.nome = nome;
		this.genero = genero;
	}
	
	public Pessoa() {
		nome = "";
		genero = ' ';
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

	public String toString() {
		return "Nome: " + nome + ", Género: " + genero;
	}
	
	public boolean equals(Object o) {
		if(o != null && o.getClass() == this.getClass()) {
			Pessoa temp = (Pessoa) o;
			return nome.equals(temp.nome) && genero == temp.genero;
		}
		return false;
	}
	
	public Object clone() {
		Pessoa clone = new Pessoa(nome, genero);
		return clone;
	}
	
}
