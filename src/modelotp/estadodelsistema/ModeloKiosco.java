package modelotp.estadodelsistema;

import des.EstadoDelSistema;
import des.LibreriaDeRutinas;
import des.RelojDeSimulacion;
import modelotp.componentespropios.LibreriaDeRutinasTP;

import java.util.List;

import static modelotp.estadodelsistema.ModeloKiosco.Servicios.BEBIDA;
import static modelotp.estadodelsistema.ModeloKiosco.Servicios.PANADERIA;

public class ModeloKiosco extends EstadoDelSistema {

    private ColaClientes cola;
    private ListaEmpleadas empleadas;

    // Perdón
    // Ver ListaDeEventosTP
    public static RelojDeSimulacion reloj;

    /* Constantes de configuración del modelo, ajeno a la estructura del simulador */
    public static final List<Servicios> productos = List.of(PANADERIA,BEBIDA);
    public static final int cantidadEmpleadas = 2;
    public static final int costoBebida = 600;
    public static final int costoPanaderia = 400;
    public static final int precioBebida = 1200;
    public static final int precioPanaderia = 850;

    public enum Servicios{
        PANADERIA("Panadería"),
        BEBIDA("Bebida");

        private String str;

        private Servicios(String string) {
            this.str = string;
        }

        @Override
        public String toString() {
            return str;
        }
    }

    public void inicializar() {
        cola = new ColaClientes();
        empleadas = new ListaEmpleadas();
    }

    /* empleada pasa a desocupada */
    public int cantidadDeClientesEnCola() {
        return cola.cantidadDeClientesEnCola();
    }

    /* encolar cliente */
    public void encolar(Cliente cliente) {
        System.out.println("\t\tEl modelo encola una solicitud.");
        cola.encolarCliente(cliente);
    }

    /* desencolar cliente para atender */
    public Cliente desencolar() {
        return cola.desencolar();
    }

    /* retornar TRUE si hay clientes en la cola, de lo contrario false */
    public boolean tieneClientes() {
        return (cola.cantidadDeClientesEnCola() > 0);
    }
    /* ------------- */

    /* saber si hay alguna empleada desocupada */
    public boolean isDesocupada() {
        return empleadas.isDesocupada();
    }

    /* empleada pasa a ocupada y atiende el cliente */
    public Empleada atenderCliente(Cliente cliente) {
        Empleada empleada;
        System.out.println("\t\tEl modelo esta atendiendo un Cliente.");
        empleada = empleadas.atenderCliente(cliente);
        return empleada;
    }

    /* empleada pasa a desocupada */
    public void setEstadoDesocupada(int id) {
        empleadas.setEstadoDesocupada(id);
    }

    public Cliente generarCliente(double tiempoArribo, LibreriaDeRutinasTP libreria) {
        Servicios tipo = libreria.tipoDeProducto();
        int unidades = libreria.cantidadProducto(tipo);
        return new Cliente(tiempoArribo, unidades, tipo);
    }

}
