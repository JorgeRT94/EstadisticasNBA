package model;


public class Equipo {

	private int id_equipo;
	private int conferencia;
	private String nombre_equipo;
	private String abreviatura;
	private String arena;

	public Equipo() {

	}

	
	public Equipo(int id_equipo, int conferencia, String nombre_equipo, String abreviatura, String arena) {
		this.id_equipo = id_equipo;
		this.conferencia = conferencia;
		this.nombre_equipo = nombre_equipo;
		this.abreviatura = abreviatura;
		this.arena = arena;
	}

	public int getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}

	public String getNombre_equipo() {
		return nombre_equipo;
	}

	public void setNombre_equipo(String nombre_equipo) {
		this.nombre_equipo = nombre_equipo;
	}

	public int getConferencia() {
		return conferencia;
	}

	public void setConferencia(int conferencia) {
		this.conferencia = conferencia;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getArena() {
		return arena;
	}

	public void setArena(String arena) {
		this.arena = arena;
	}

	@Override
	public String toString() {
		return "Equipo [id_equipo=" + id_equipo + ", conferencia=" + conferencia
				+ ", nombre_equipo=" + nombre_equipo + ", abreviatura=" + abreviatura + ", arena=" + arena + "]";
	}



}
