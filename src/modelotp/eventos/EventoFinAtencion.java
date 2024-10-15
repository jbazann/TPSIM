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
    private String producto;
    private int cantidad;

    public EventoFinAtencion(double tiempoOcurrencia, int empleada, String producto, int cantidad) {
        super(tiempoOcurrencia);
        this.idEmpleada = empleada;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos listaDeEventos, LibreriaDeRutinas libreria) {
        
        ContadoresEstadisticosTP contadorTP = (ContadoresEstadisticosTP) contadores;
        LibreriaDeRutinasTP rutinasTP = (LibreriaDeRutinasTP) libreria;
        ModeloKiosco modeloKiosco = (ModeloKiosco) modelo;

        contadorTP.sumarClienteAtendido(idEmpleada);
        contadorTP.sumarUnidadesVendidas(producto, cantidad);
        contadorTP.sumarTiempoOcupada(idEmpleada,getTiempoDeOcurrencia());

        if (modeloKiosco.tieneClientes()) {

            Cliente cliente = modeloKiosco.desencolar();

            int idEmpleada = modeloKiosco.atenderCliente(cliente);
            String producto = rutinasTP.tipoDeProducto();
            int cantidad = rutinasTP.cantidadProducto(producto);
            double tiempoServicio = rutinasTP.tiempoServicioEmpleada(producto, cantidad);
            EventoFinAtencion eventoFinAtencion = new EventoFinAtencion(tiempoServicio, idEmpleada, producto, cantidad);
            listaDeEventos.agregar(eventoFinAtencion);

            contadorTP.agregarTiempoDeClienteEnKiosco(getTiempoDeOcurrencia()-cliente.getTiempoDeArribo());
        } else {
            modeloKiosco.setEstadoDesocupada(idEmpleada);
        }
        
    }
}
