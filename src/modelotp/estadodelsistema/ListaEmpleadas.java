package modelotp.estadodelsistema;

import java.util.LinkedList;

public class ListaEmpleadas {
    private LinkedList<Empleada> listaEmpleadas;

    public ListaEmpleadas() {
        super();
        listaEmpleadas = new LinkedList<Empleada>();

        for (int i = 0; i < 2; i++) {
            listaEmpleadas.add(new Empleada());
        }
    }

    public boolean isDesocupada() {
        for (Empleada empleada : listaEmpleadas) {
            if (!empleada.getEstado()) return true; /* al menos una desocupada */
        }
        return false; /* todas estan ocupadas */
    }

    public int atenderCliente(Cliente cliente) {
        int idEmpleada;
        for (Empleada empleada : listaEmpleadas) {
            if (!empleada.getEstado()) {
                empleada.setEstado(true);
                empleada.setClienteAtendido(cliente);
                idEmpleada = empleada.getId();
                return idEmpleada;
            }
        }
        return -1; /* si hubo un error */
    }

    public void setEstadoDesocupada(int id) {
        for (Empleada empleada : listaEmpleadas) {
            if (empleada.getId() == id) empleada.setEstado(false); 
            break;
        }
    }
}
