package modeloejemplo.eventos;

import des.*;
import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;

public class EventoTerminaProcesamiento extends Evento {

	public EventoTerminaProcesamiento(double tiempoDeOcurrencia) {
		super(tiempoDeOcurrencia);
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinas libreriaAbstract) {

		LibreriaDeRutinasEjemplo libreria = (LibreriaDeRutinasEjemplo) libreriaAbstract;
		ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
		contadoresEjemplo.actualizarCantProcesadas();
		
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;
		
		if(modeloActual.haySolicitudesEnEspera()) {
			Solicitud solicitudAProcesar = modeloActual.obtenerSolicitudPrioritaria();
			modeloActual.atenderSolicitud(solicitudAProcesar);
			int duracionDelProcesamiento = libreria.tiempoDeProcesamiento();
			EventoTerminaProcesamiento nuevoEvento = new EventoTerminaProcesamiento(duracionDelProcesamiento);	
			eventos.agregar(nuevoEvento);	
		}
		else {

			modeloActual.actualizarServidorDisponible();
		}

	}

}
