package des;

import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.GeneradorDeReportesEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.componentespropios.ListaDeEventosEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modelotp.componentespropios.ContadoresEstadisticosTP;
import modelotp.componentespropios.GeneradorDeReportesTP;
import modelotp.componentespropios.LibreriaDeRutinasTP;
import modelotp.componentespropios.ListaDeEventosTP;
import modelotp.estadodelsistema.ModeloKiosco;

/* Subprograma que invoca a la Rutina de Tiempo para determinar evento inminente, 
 * transfiriendo el control a la Rutina de Evento asociada para que actualice el
 * Estado del Sistema. */

public class ProgramaPrincipal {

	//Creación de los componentes propios del ejemplo.
	private static EstadoDelSistema modelo;		
	private static ContadoresEstadisticos contadores;
	private static GeneradorDeReportes reporte;
	private static LibreriaDeRutinas libreria;
	private static ListaDeEventos eventos;
		
	public static void main(String[] args) {

		//Creación de los componentes propios del ejemplo a ejecutar.
		crearComponentesDependientes();
		
		//Creación de los componentes generales.
		RutinaDeInicializacion inicializacion = new RutinaDeInicializacion();
		RutinaDeTiempo manejoDeTiempo = new RutinaDeTiempo();
		RelojDeSimulacion reloj = new RelojDeSimulacion();

		System.out.println("------------------------------------------------------");
		System.out.println("***INICIALIZACION");
		System.out.println("------------------------------------------------------");
		
		//Flujo de control
		inicializacion.run(reloj,modelo,contadores,eventos,libreria);
		
		do { 
			
			System.out.println("------------------------------------------------------");
			System.out.println("***PROGRAMA PRINCIPAL *** t=" + reloj.getValor());
			System.out.println("------------------------------------------------------");
						
			//Invocar a la rutina de tiempo.
			Evento eventoPorEjecutar = manejoDeTiempo.run(eventos,reloj);
			
			System.out.println("\t\t-- El SIMULADOR determina que el EVENTO MAS INMINENTE se dará en " + eventoPorEjecutar.getTiempoDeOcurrencia() + " unidades de tiempo.");
			System.out.println("\t\t-- El SIMULADOR actualiza el RELOJ para ejecutar el EVENTO MAS INMINENTE del tipo " + eventoPorEjecutar.getClass().getSimpleName());
			
			//Invocar a la rutina de evento.
			eventoPorEjecutar.rutinaDeEvento(modelo,contadores,eventos,libreria);
			
		}while(!terminoLaSimulacion(reloj,contadores));
		
		reporte.run(contadores);

	}

	private static void crearComponentesDependientes() {
		//TODO Aca se crean los componentes propios del modelo a ejecutar.
		modelo = new ModeloKiosco();		
		contadores = new ContadoresEstadisticosTP();
		reporte = new GeneradorDeReportesTP();
		libreria = new LibreriaDeRutinasTP();
		eventos = new ListaDeEventosTP();
	}

	private static boolean terminoLaSimulacion(RelojDeSimulacion reloj, ContadoresEstadisticos contadores) {
		//TODO Aca se debe programar según el fin sea por tiempo o cantidad.
		
		//Ejemplo por tiempo
		int tiempoDeSimulacion = 60*8;
		if(reloj.getValor() >= tiempoDeSimulacion) return true;
		return false;

		//Ejemplo por cantidad: "Que se hayan procesado 15 solicitudes."
//		ContadoresEstadisticosTP contador = (ContadoresEstadisticosTP) contadores;
//		int cantidadDeSimulacion = contador.tiemposEnSistema().size(), topeDeSimulacion=10;
//		if(cantidadDeSimulacion >= topeDeSimulacion) return true;
//		return false;
		
	}

}
