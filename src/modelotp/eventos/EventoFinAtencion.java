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

    public EventoFinAtencion(double tiempoOcurrencia, Empleada empleada) {
        super(tiempoOcurrencia);
        this.empleada = empleada;
    }

    @Override
    public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos listaDeEventos, LibreriaDeRutinas libreria) {
        
        ContadoresEstadisticosTP contadorTP = (ContadoresEstadisticosTP) contadores;
        LibreriaDeRutinasTP rutinasTP = (LibreriaDeRutinasTP) libreria;
        ModeloKiosco modeloKiosco = (ModeloKiosco) modelo;

        contadorTP.sumarClienteAtendido(empleada.getId());
        contadorTP.sumarTiempoEnSistema(empleada.getClienteAtendido().tiempoDeArribo, reloj.getValor());
        contadorTP.sumarUnidadesVendidas(empleada.getClienteAtendido().tipoServicio(), empleada.getClienteAtendido().unidades());
        contadorTP.sumarTiempoOcupada(empleada.getId(),reloj.getValor()-empleada.getTiempoInicioAtencion());

        // desocupar empleada
        modeloKiosco.setEstadoDesocupada(empleada.getId());

        if (modeloKiosco.tieneClientes()) {

            Cliente cliente = modeloKiosco.desencolar();
            contadorTP.agregarCantidadDeClientesEnCola(modeloKiosco.cantidadDeClientesEnCola());

            Empleada posiblementeOtraEmpleada = modeloKiosco.atenderCliente(cliente);
            double tiempoServicio = rutinasTP.tiempoServicioEmpleada(cliente.tipoServicio(), cliente.unidades());
            EventoFinAtencion eventoFinAtencion = new EventoFinAtencion(tiempoServicio, posiblementeOtraEmpleada);
            listaDeEventos.agregar(eventoFinAtencion);
        }
        
    }
}
