import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Profissional extends Pessoa implements Serializable {
	private static int ultimo = 0; // número profissional
	private long numero;
	private float salario;
	private ArrayList<String> hab; // Habilitações
	private ArrayList<Consulta> consultas;

	public Profissional() {
		super();
		numero = 0;
		salario = 0;
		hab = new ArrayList<>();
		consultas = new ArrayList<>();
	}
	
	public Profissional(Pessoa p, float salario) {
		super(p.nome, p.genero);
		numero = ++ultimo;
		this.salario = salario;
		hab = new ArrayList<>();
		consultas = new ArrayList<>();
	}

	//Adicionar consultas
	public void addConsulta(Consulta c) {
		consultas.add(c);

		//Reordenar por ordem da data
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
	
	//Remover consultas
	public void removeConsulta(int i) {
		consultas.remove(i);
	}

	//Adicionar Habilitações
	public void addHab(String h) {
		if (!hab.contains(h)) {
			hab.add(h);
		}
	}
	
	//Remover Habilitações
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
		return "Número: " + numero + ", Nome: " + nome + ", Género: " + genero +  "\nSalário: " + salario + "\nHabilitações: " + hab.toString();
	}

	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Profissional temp = (Profissional) obj;
			return super.equals(temp) && hab.equals(temp.hab)
					&& consultas.equals(temp.consultas) && salario == temp.salario && numero == temp.numero;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Object clone() {
		//Guardamos a variável static Ultimo porque quando criamos um clone, incremente sempre um valor
		int ultimo = Profissional.getUltimo();
		Profissional temp = new Profissional(new Pessoa(super.nome, super.genero), salario);
		temp.hab = (ArrayList<String>) hab.clone();
		temp.consultas = (ArrayList<Consulta>) consultas.clone();
		temp.numero = numero;
		temp.salario = salario;
		Profissional.setUltimo(ultimo); //Metemos a variável static outra vez a "ultimo" para os números seguirem naturalmente
		return temp;
	}
}