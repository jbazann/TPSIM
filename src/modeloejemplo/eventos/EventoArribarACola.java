package modeloejemplo.eventos;

import des.*;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;

public class EventoArribarACola extends Evento {

	public EventoArribarACola(double tiempoDeOcurrencia) {
		super(tiempoDeOcurrencia);
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
			LibreriaDeRutinas libreriaAbstract) {
				
		LibreriaDeRutinasEjemplo libreria = (LibreriaDeRutinasEjemplo) libreriaAbstract;
		ModeloDelEjemplo modeloActual = (ModeloDelEjemplo) modelo;
		
		//Agendar el próximo arribo de solicitud.
		EventoArribarACola nuevoEvento = new EventoArribarACola(libreria.tiempoEntreArribosSolicitudes());	
		eventos.agregar(nuevoEvento);
		
		//Procesar este arribo, para lo cual es necesario generar la solicitud que acaba de arribar.
		Solicitud solicitudParaAgregar = new Solicitud();
		
		if(modeloActual.estaServidorOcupado()) {
			modeloActual.encolarSolicitud(solicitudParaAgregar);
		}
		else {
			modeloActual.atenderSolicitud(solicitudParaAgregar);
			int duracionDelProcesamiento = libreria.tiempoDeProcesamiento();
			EventoTerminaProcesamiento nuevoEventoAdicional = new EventoTerminaProcesamiento(duracionDelProcesamiento);	
			eventos.agregar(nuevoEventoAdicional);
		}
	}

}
