package modelotp.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.LibreriaDeRutinas;
import des.ListaDeEventos;
import modelotp.componentespropios.ContadoresEstadisticosTP;
import modelotp.componentespropios.LibreriaDeRutinasTP;
import modelotp.estadodelsistema.Cliente;
import modelotp.estadodelsistema.Empleada;
import modelotp.estadodelsistema.ModeloKiosco;

import static modelotp.estadodelsistema.ModeloKiosco.reloj;


public class EventoArribaACola extends Evento {

    public EventoArribaACola(double tiempoOcurrencia) {
        super(tiempoOcurrencia);
    }

    @Override
    public void rutinaDeEvento(EstadoDelSistema estado, ContadoresEstadisticos contadores, ListaDeEventos listaEventos, LibreriaDeRutinas libreria) {
        
        ModeloKiosco modeloKiosco = (ModeloKiosco) estado;
        ContadoresEstadisticosTP contadoresTP = (ContadoresEstadisticosTP) contadores;
        LibreriaDeRutinasTP rutinasTP = (LibreriaDeRutinasTP) libreria;
        
        /* guardar el proximo arribo de un cliente */
        EventoArribaACola nuevoEventoArribo = new EventoArribaACola(rutinasTP.tiempoEntreArriboCliente());
        listaEventos.agregar(nuevoEventoArribo);

        /* procesar cliente actual */
        Cliente cliente = modeloKiosco.generarCliente(reloj.getValor(), rutinasTP);

        /* si todas las empleadas estan ocupadas se pone el cliente en la cola */
        if (!modeloKiosco.isDesocupada()) {
            modeloKiosco.encolar(cliente);
            contadoresTP.agregarCantidadDeClientesEnCola(modeloKiosco.cantidadDeClientesEnCola());
        } else { /* si alguna empleadas esta desocupada se atiende el cliente */
            Empleada empleada = modeloKiosco.atenderCliente(cliente);
            contadoresTP.agregarCantidadDeClientesEnCola(0);// muestreo cola vacia

            double tiempoServicio = rutinasTP.tiempoServicioEmpleada(cliente.tipoServicio(), cliente.unidades());
            EventoFinAtencion eventoFinAtencion = new EventoFinAtencion(tiempoServicio, empleada);
            listaEventos.agregar(eventoFinAtencion);
        }
    }
}
