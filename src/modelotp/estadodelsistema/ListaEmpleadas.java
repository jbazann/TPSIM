package modelotp.estadodelsistema;

import java.util.LinkedList;

import static modelotp.estadodelsistema.ModeloKiosco.reloj;

public class ListaEmpleadas {
    private LinkedList<Empleada> listaEmpleadas;

    public ListaEmpleadas() {
        super();
        listaEmpleadas = new LinkedList<Empleada>();

        for (int i = 0; i < ModeloKiosco.cantidadEmpleadas; i++) {
            listaEmpleadas.add(new Empleada(i+1));
        }
    }

    public boolean isDesocupada() {
        for (Empleada empleada : listaEmpleadas) {
            if (!empleada.getEstado()) return true; /* al menos una desocupada */
        }
        return false; /* todas estan ocupadas */
    }

    public Empleada atenderCliente(Cliente cliente) {
        for (Empleada empleada : listaEmpleadas) {
            if (!empleada.getEstado()) {
                empleada.setEstado(true);
                empleada.setClienteAtendido(cliente);
                empleada.setTiempoInicioAtencion(reloj.getValor());
                return empleada;
            }
        }
        return null; /* si hubo un error */
    }

    public void setEstadoDesocupada(int id) {
        for (Empleada empleada : listaEmpleadas) {
            if (empleada.getId() == id) {
                empleada.setEstado(false);
                empleada.setClienteAtendido(null);
                break;
            }
        }
    }
}
