package modelotp.estadodelsistema;

import des.EstadoDelSistema;

public class ModeloKiosco extends EstadoDelSistema {

    private ColaClientes cola;
    private ListaEmpleadas empleadas;

    public static final String panaderia = "Panaderia";
    public static final String bebida = "Bebida";


    public void inicializar() {
        cola = new ColaClientes();
        empleadas = new ListaEmpleadas();
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
    public int atenderCliente(Cliente cliente) {
        int idEmpleada;
        System.out.println("\t\tEl modelo esta atendiendo un Cliente.");
        idEmpleada = empleadas.atenderCliente(cliente);
        return idEmpleada;
    }

    /* empleada pasa a desocupada */
    public void setEstadoDesocupada(int id) {
        empleadas.setEstadoDesocupada(id);
    }


}
