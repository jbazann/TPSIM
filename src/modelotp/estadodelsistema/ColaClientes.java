package modelotp.estadodelsistema;

import java.util.LinkedList;
import java.util.Queue;

public class ColaClientes {
    private Queue<Cliente> colaCliente;

    public ColaClientes() {
        super();
        colaCliente = new LinkedList<Cliente>();
    }   

    /* encolar un cliente en la cola */
    public void encolarCliente(Cliente cliente) {
        colaCliente.add(cliente);
    }

    /* desencolar un cliente */
    public Cliente desencolar() {
        Cliente c;
        c = colaCliente.remove();
        return c;
    }
}
