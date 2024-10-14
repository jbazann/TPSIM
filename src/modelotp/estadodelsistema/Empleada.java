package modelotp.estadodelsistema;

public class Empleada {

    private int id;
    private boolean estado; /* OCUPADO=true - DESOCUPADO=false */
    private Cliente clienteAtendido;

    public Empleada(int id) {
        super();
        estado = false;
        clienteAtendido = null;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean e) {
        this.estado = e;
    }

    public Cliente getClienteAtendido() {
        return clienteAtendido;
    }

    public void setClienteAtendido(Cliente clienteAtendido) {
        this.clienteAtendido = clienteAtendido;
    }
}
