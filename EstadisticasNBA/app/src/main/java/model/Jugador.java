package model;

public class Jugador {

	private int idJugador;
	private int equipo;
	private String nombreJugador;
	private int edad;
	private float altura;
	private float ppp;
	private float app;
	private float rpp;

	public Jugador(int idJugador, int equipo, String nombreJugador, int edad, float altura, float ppp, float app,
			float rpp) {
		this.idJugador = idJugador;
		this.equipo = equipo;
		this.nombreJugador = nombreJugador;
		this.edad = edad;
		this.altura = altura;
		this.ppp = ppp;
		this.app = app;
		this.rpp = rpp;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public int getEquipo() {
		return equipo;
	}

	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPpp() {
		return ppp;
	}

	public void setPpp(float ppp) {
		this.ppp = ppp;
	}

	public float getApp() {
		return app;
	}

	public void setApp(float app) {
		this.app = app;
	}

	public float getRpp() {
		return rpp;
	}

	public void setRpp(float rpp) {
		this.rpp = rpp;
	}

	@Override
	public String toString() {
		return "Jugador{" +
				"idJugador=" + idJugador +
				", equipo=" + equipo +
				", nombreJugador='" + nombreJugador + '\'' +
				", edad=" + edad +
				", altura=" + altura +
				", ppp=" + ppp +
				", app=" + app +
				", rpp=" + rpp +
				'}';
	}
}
