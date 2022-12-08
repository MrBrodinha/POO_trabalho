import java.io.Serializable;
import java.util.ArrayList;

public class Profissional implements Serializable{
	static int ultimo = 0; // número profissional
	private long numero;
	private String nome;
	private ArrayList<String> hab; // Habilitações
	private ArrayList<Consulta> consultas;

	public Profissional() {
		numero = 0;
		nome = "";
		hab = new ArrayList<>();
		consultas = new ArrayList<>();
	}

	public Profissional(String nome) {
		numero = ++ultimo;
		this.nome = nome;
		hab = new ArrayList<>();
		consultas = new ArrayList<>();
	}

	public void addConsulta(Consulta c) {
		for (int i = 0; i < consultas.size(); i++) {
			if (consultas.get(i).equals(c)) {
				System.out.println("Consulta já se encontra marcada!");
			} else if (consultas.get(i).getData().equals(c.getData())) {
				System.out.println("Já existe uma consulta nessa data");
			} else {
				consultas.add(c);
			}

		}
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<String> getHab() {
		return hab;
	}

	@SuppressWarnings("unchecked")
	public void setHab(ArrayList<String> hab) {
		this.hab = (ArrayList<String>) hab.clone();
	}

	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}

	@SuppressWarnings("unchecked")
	public void setConsultas(ArrayList<Consulta> hab) {
		this.consultas = (ArrayList<Consulta>) consultas.clone();
	}

	public String toString() {
		return "Nº Profissional: " + numero + "\nNome: " + nome + "\nHabilitações: " + hab.toString();
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Profissional temp = (Profissional) obj;
			return nome.equals(temp.nome) && numero == temp.numero && hab.equals(temp.hab) && consultas.equals(temp.consultas);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Object clone() {
		Profissional temp = new Profissional(nome);
		temp.hab = (ArrayList<String>) hab.clone();
		temp.consultas = (ArrayList<Consulta>) consultas.clone();
		temp.numero = numero;
		return temp;
	}

}
