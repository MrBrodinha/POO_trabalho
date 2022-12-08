import java.io.Serializable;
import java.util.ArrayList;

public class Ficha_Tecnica extends Utente implements Serializable {
	ArrayList<String> alergias;
	ArrayList<Consulta> consultas;
	ArrayList<String> medicamentos;
	long NUS;
	
	public Ficha_Tecnica(Utente u) {
		super(u.getNome(), u.getNUS(), u.getGenero());
		this.NUS = u.getNUS();
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
		medicamentos.add(m);
	}
	
	public void addAlergias(String a) {
		alergias.add(a);
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
		return "NUS: " + NUS + ", Utente: " + super.getNome() + "\nAlergias: " + alergias.toString() + "\nMedicamentos: " + medicamentos.toString() + "\nConsultas: " + consultas.toString();
	}
	
	
}
