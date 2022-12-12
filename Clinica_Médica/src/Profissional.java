import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Profissional implements Serializable {
	private static int ultimo = 0; // número profissional
	private long numero;
	private String nome;
	private float salario;
	private ArrayList<String> hab; // Habilitações
	private ArrayList<Consulta> consultas;

	public Profissional() {
		numero = 0;
		nome = "";
		hab = new ArrayList<>();
		consultas = new ArrayList<>();
		salario = 0;
	}

	public Profissional(String nome, float salario) {
		numero = ++ultimo;
		this.salario = salario;
		this.nome = nome;
		hab = new ArrayList<>();
		consultas = new ArrayList<>();
	}

	public void addConsulta(Consulta c) {
		consultas.add(c);

		// reordenar por ordem da data
		for (int i = 0; i < consultas.size(); i++) {
			for (int j = 0; j < consultas.size(); j++) {
				if (consultas.get(j).getData().isAfter(consultas.get(i).getData())) {
					Consulta temp = consultas.get(i);
					consultas.set(i, consultas.get(j));
					consultas.set(j, temp);
				}
			}
		}
	}

	public void removeConsulta(int i) {
		consultas.remove(i);
	}

	public void addHab(String h) {
		if (!hab.contains(h)) {
			hab.add(h);
		}
	}

	public void removerHab(String h) {
		for (int i = 0; i < hab.size(); i++) {
			if (hab.get(i).contains(h)) {
				hab.remove(i);
			}
		}
	}

	public static int getUltimo() {
		return ultimo;
	}

	public static void setUltimo(int ultimo) {
		Profissional.ultimo = ultimo;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
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
		return "Número: " + numero + ", Nome: " + nome + ", Salário: " + salario + "\nHabilitações: " + hab.toString();
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Profissional temp = (Profissional) obj;
			return nome.equals(temp.nome) && numero == temp.numero && hab.equals(temp.hab)
					&& consultas.equals(temp.consultas) && salario == temp.salario;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Object clone() {
		int ultimo = Profissional.getUltimo();
		Profissional temp = new Profissional(nome, salario);
		temp.hab = (ArrayList<String>) hab.clone();
		temp.consultas = (ArrayList<Consulta>) consultas.clone();
		temp.numero = numero;
		temp.salario = salario;
		Profissional.setUltimo(ultimo);
		return temp;
	}
}