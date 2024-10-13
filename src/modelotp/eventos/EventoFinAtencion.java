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

public class EventoFinAtencion extends Evento {

    private int idEmpleada;

    public EventoFinAtencion(double tiempoOcurrencia, int empleada) {
        super(tiempoOcurrencia);
        this.idEmpleada = empleada;
    }

    @Override
    public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos listaDeEventos, LibreriaDeRutinas libreria) {
        
        ContadoresEstadisticosTP contadorTP = (ContadoresEstadisticosTP) contadores;
        LibreriaDeRutinasTP rutinasTP = (LibreriaDeRutinasTP) libreria;
        ModeloKiosco modeloKiosco = (ModeloKiosco) modelo;

        if (modeloKiosco.tieneClientes()) {

            Cliente cliente = modeloKiosco.desencolar();

            int idEmpleada = modeloKiosco.atenderCliente(cliente);
            String producto = rutinasTP.tipoDeProducto();
            int cantidad = rutinasTP.cantidadProducto(producto);
            double tiempoServicio = rutinasTP.tiempoServicioEmpleada(producto, cantidad);
            EventoFinAtencion eventoFinAtencion = new EventoFinAtencion(tiempoServicio, idEmpleada);
            listaDeEventos.agregar(eventoFinAtencion);
        } else {
            modeloKiosco.setEstadoDesocupada(idEmpleada);
        }
        
    }
}
