package des;

import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;

/***************************************/
/* NO MODIFICAR PARA USAR EL SIMULADOR */
/***************************************/

public abstract class Evento {
	
	private double tiempoDeOcurrencia;
	
	public Evento(double tiempoDeOcurrencia) {
		super();
		this.setIntervaloDeOcurrencia(tiempoDeOcurrencia);
	}

	public double getTiempoDeOcurrencia() {
		return tiempoDeOcurrencia;
	}
	
	public void refreshTiempo(double elapsedTime) {
		this.tiempoDeOcurrencia -= elapsedTime;	
	}
	
	private void setIntervaloDeOcurrencia(double tiempoDeOcurrencia) {
		this.tiempoDeOcurrencia = tiempoDeOcurrencia;
	}
	
	/* Subprograma que actualiza el estado del sistema  cuando un tipo particular de evento tiene lugar. */

	public abstract void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos, LibreriaDeRutinas libreria);

}
