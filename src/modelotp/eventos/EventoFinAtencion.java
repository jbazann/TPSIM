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

public class EventoFinAtencion extends Evento {

    private Empleada empleada;
    private String producto;
    private int cantidad;

    public EventoFinAtencion(double tiempoOcurrencia, Empleada empleada, String producto, int cantidad) {
        super(tiempoOcurrencia);
        this.empleada = empleada;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    @Override
    public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos listaDeEventos, LibreriaDeRutinas libreria) {
        
        ContadoresEstadisticosTP contadorTP = (ContadoresEstadisticosTP) contadores;
        LibreriaDeRutinasTP rutinasTP = (LibreriaDeRutinasTP) libreria;
        ModeloKiosco modeloKiosco = (ModeloKiosco) modelo;

        contadorTP.sumarClienteAtendido(empleada.getId());
        contadorTP.sumarTiempoEnSistema(empleada.getClienteAtendido().tiempoDeArribo, reloj.getValor());
        contadorTP.sumarUnidadesVendidas(producto, cantidad);
        contadorTP.sumarTiempoOcupada(empleada.getId(),reloj.getValor()-empleada.getTiempoInicioAtencion());

        // desocupar empleada
        modeloKiosco.setEstadoDesocupada(empleada.getId());

        if (modeloKiosco.tieneClientes()) {

            Cliente cliente = modeloKiosco.desencolar();
            contadorTP.agregarCantidadDeClientesEnCola(modeloKiosco.cantidadDeClientesEnCola());

            Empleada posiblementeOtraEmpleada = modeloKiosco.atenderCliente(cliente);
            String producto = rutinasTP.tipoDeProducto();
            int cantidad = rutinasTP.cantidadProducto(producto);
            double tiempoServicio = rutinasTP.tiempoServicioEmpleada(producto, cantidad);
            EventoFinAtencion eventoFinAtencion = new EventoFinAtencion(tiempoServicio, posiblementeOtraEmpleada, producto, cantidad);
            listaDeEventos.agregar(eventoFinAtencion);
        }
        
    }
}
