package modelotp.estadodelsistema;

public class Empleada {

    private int id;
    private boolean estado; /* OCUPADO=true - DESOCUPADO=false */
    private Cliente clienteAtendido;
    private double t_inicio_atencion;

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

    public double getTiempoInicioAtencion() {
        return t_inicio_atencion;
    }

    public void setTiempoInicioAtencion(double t_inicio_atencion) {
        this.t_inicio_atencion = t_inicio_atencion;
    }
}
