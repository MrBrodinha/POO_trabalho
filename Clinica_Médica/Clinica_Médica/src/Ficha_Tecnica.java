import java.io.Serializable;
import java.util.ArrayList;

public class Ficha_Tecnica implements Serializable {
	ArrayList<String> alergias;
	ArrayList<Consulta> consultas;
	ArrayList<String> medicamentos;
	long NUS;
	String nome;
	
	public Ficha_Tecnica(String nome, long NUS) {
		this.NUS = NUS;
		this.nome = nome;
		alergias = new ArrayList<>();
		consultas = new ArrayList<>();
		medicamentos = new ArrayList<>();
	}
	
	public long getNUS() {
		return NUS;
	}

	public void setNUS(long NUS) {
		this.NUS = NUS;
	}
	
	public void addConsulta(Consulta c) {
		consultas.add(c);
	}
	
	public void addMedicamentos(String m) {
		if(!medicamentos.contains(m.toUpperCase())) {
			medicamentos.add(m.toUpperCase());
		}
	}
	
	public void addAlergias(String a) {
		if(!alergias.contains(a.toUpperCase())) {
			alergias.add(a.toUpperCase());
		}
	}
	
	public void removeConsulta(Consulta c) {
		consultas.add(c);
	}
	
	public void removeMedicamentos(String m) {
		if(medicamentos.contains(m.toUpperCase())) {
			medicamentos.remove(m.toUpperCase());
		}
		else {
			System.out.println("Medicamento não se encontra na lista");
		}
	}
	
	public void removeAlergias(String a) {
		if(alergias.contains(a.toUpperCase())) {
			alergias.remove(a.toUpperCase());
		}
		else {
			System.out.println("Alergia não se encontra na lista");
		}
	}
	
	public ArrayList<String> getAlergias() {
		return alergias;
	}

	@SuppressWarnings("unchecked")
	public void setAlergias(ArrayList<String> alergias) {
		this.alergias = (ArrayList<String>) alergias.clone();
	}

	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}

	@SuppressWarnings("unchecked")
	public void setConsultas(ArrayList<Consulta> consultas) {
		this.consultas = (ArrayList<Consulta>) consultas.clone();
	}

	public ArrayList<String> getMedicamentos() {
		return medicamentos;
	}

	@SuppressWarnings("unchecked")
	public void setMedicamentos(ArrayList<String> medicamentos) {
		this.medicamentos = (ArrayList<String>) medicamentos.clone();
	};
	
	public String toString() {
		return "NUS: " + NUS + ", Utente: " + nome + "\nAlergias: " + alergias.toString() + "\nMedicamentos: " + medicamentos.toString() + "\nConsultas: " + consultas.toString();
	}
	
	public boolean equals(Object obj) {
		if(obj != null && obj.getClass() == this.getClass()) {
			Ficha_Tecnica temp = (Ficha_Tecnica) obj;
			return alergias.equals(temp.alergias) && consultas.equals(temp.consultas) && medicamentos.equals(temp.medicamentos) && NUS == temp.NUS && nome.equals(temp.nome);
		}
		return false;
	}
	
	public Object clone() {
		Ficha_Tecnica temp = new Ficha_Tecnica(nome, NUS); 
		temp.alergias = (ArrayList<String>) alergias.clone();
		temp.consultas = (ArrayList<Consulta>) consultas.clone();
		temp.medicamentos = (ArrayList<String>) medicamentos.clone();
		return temp;
	}
	
	
}
