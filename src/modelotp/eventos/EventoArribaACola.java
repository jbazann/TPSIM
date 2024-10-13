package modelotp.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.LibreriaDeRutinas;
import des.ListaDeEventos;
import modelotp.componentespropios.ContadoresEstadisticosTP;
import modelotp.componentespropios.LibreriaDeRutinasTP;
import modelotp.estadodelsistema.Cliente;
import modelotp.estadodelsistema.ModeloKiosco;

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
        Cliente cliente = new Cliente();

        /* si todas las empleadas estan ocupadas se pone el cliente en la cola */
        if (!modeloKiosco.isDesocupada()) {
            modeloKiosco.encolar(cliente);
        } else { /* si alguna empleadas esta desocupada se atiende el cliente */
        /* estadistica? */
            int id = modeloKiosco.atenderCliente(cliente);
            
            String producto = rutinasTP.tipoDeProducto();
            int cantidad = rutinasTP.cantidadProducto(producto);
            double tiempoServicio = rutinasTP.tiempoServicioEmpleada(producto, cantidad);
            EventoFinAtencion eventoFinAtencion = new EventoFinAtencion(tiempoServicio, id);
            listaEventos.agregar(eventoFinAtencion);
        }
    }
}
