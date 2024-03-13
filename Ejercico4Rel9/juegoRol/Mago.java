package com.monroy.juegoRol;

public class Mago extends Personaje{
	
	//Constantes
	private static final int ATAQUE_MAGO = 10;
	private static final int INTELIGENCIA_MAX_MAGO = 17;
	private static final int FUERZA_MAX_MAGO = 15;
	private static final int NUMERO_HECHIZOS= 4;
	String[] vectorHechizos;
	
	//Constructor
	public Mago(String nombre, TRaza raza,int fuerza, int inteligencia,int vidaMax) throws PersonajeException {
		super(nombre,raza,fuerza,inteligencia,vidaMax);
		setFuerza(fuerza);
		setInteligencia(inteligencia);
		String[] vectorHechizos = new String[NUMERO_HECHIZOS];
	}
	
	//Sets Sobreescritos
	@Override
	public void setFuerza(int fuerza) throws PersonajeException {
		if (fuerza<=0 || fuerza>FUERZA_MAX_MAGO){
			throw new PersonajeException("La fuerza tiene que estar entre 0 y " + FUERZA_MAX_MAGO);
		}
		
		super.setFuerza(fuerza);
	}
	
	@Override
	public void setInteligencia(int inteligencia) throws PersonajeException {
		if (inteligencia < 0 || inteligencia>INTELIGENCIA_MAX_MAGO){
			throw new PersonajeException("La inteligencia no puede superar "+ INTELIGENCIA_MAX_MAGO);
		}
		super.setInteligencia(inteligencia);
	}
	
	//Metodos de la clase Mago
	
	/**
	 * Metodo para aprender un hechizo
	 * @param nombreHechizo
	 * @throws PersonajeException
	 */
	public void aprendeHechizo(String nombreHechizo) throws PersonajeException {
		
		boolean huecoLibre=false;
		int posicionHueco=0;
		
		//Este bucle anidado recorre vectorHechizos en busca del hechizo, 
		//en caso de que no este repetido, busca una posicion vacia y lo implementa.
		
		for (int i = 0; i < vectorHechizos.length && !huecoLibre; i++) {
			for (int j = 0; j < vectorHechizos.length; j++) {
				if (vectorHechizos[j].equalsIgnoreCase(nombreHechizo)) {
					throw new PersonajeException("Este hechizo ya existe!");
				}
			}
			
			if (vectorHechizos[i].equals(null)) {
				huecoLibre=true;
				posicionHueco= i;
			}
		}
		
		if (!huecoLibre) {
			throw new PersonajeException("No puedes aprender un nuevo hechizo.");
		}
		
		vectorHechizos[posicionHueco]= nombreHechizo;
		
	}
	
	/**
	 * Metodo para lanzar el hechizo contra otro personaje
	 * @param nombrePersonaje
	 * @param nombreHechizo
	 * @throws PersonajeException
	 */
	public void lanzaHechizo(Personaje nombrePersonaje, String nombreHechizo) throws PersonajeException {
		
		int vidaOponente;
		boolean hechizoExistente=false;
		
		//Este bucle comprueba que el nombre del hechizo existe,
		//En caso de que no exista tira excepcion y pierde turno
		
		for (int i = 0; i < vectorHechizos.length && !hechizoExistente; i++) {
			if (vectorHechizos[i].equalsIgnoreCase(nombreHechizo)) {
				hechizoExistente=true;
			}
		}
		
		if (!hechizoExistente) {
			throw new PersonajeException("El hechizo no existe! Pierdes el turno.");
		}
		
		//Si el hechizo existe, se obtendria la vida actual del personaje
		//y con el metodo quitarVidaOponente, 
		//se le restaria la cantidad de vida del ataque.
		
		vidaOponente=nombrePersonaje.getVidaActual();
		
		nombrePersonaje.setVidaActual(quitarVidaOponente(vidaOponente));
		
		System.out.println("El mago ha lanzado "+nombreHechizo+" contra "+nombrePersonaje.getNombre()+" y ha perdido 10 puntos de vida!");
		
		
	}
	
	/**
	 * Metodo para quitar la vida del oponente.
	 * @param vidaOponente
	 * @return
	 */
	public int quitarVidaOponente(int vidaOponente) {
		int vidaActualizada;
		
		vidaActualizada= vidaOponente - ATAQUE_MAGO;
		
		return vidaActualizada;
	}
	
}
