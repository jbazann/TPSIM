package modelotp.estadodelsistema;

public class Cliente {

    public double tiempoDeArribo;
    private int unidades;
    private ModeloKiosco.Servicios tipoServicio;

    public Cliente(double tiempoDeArribo, int unidades, ModeloKiosco.Servicios tipoServicio) {
        super();
        this.tiempoDeArribo = tiempoDeArribo;
        this.unidades = unidades;
        this.tipoServicio = tipoServicio;
    }

    public double getTiempoDeArribo() {
        return tiempoDeArribo;
    }

    public void setTiempoDeArribo(double tiempoDeArribo) {
        this.tiempoDeArribo = tiempoDeArribo;
    }

    public ModeloKiosco.Servicios tipoServicio() {
        return tipoServicio;
    }

    public Cliente setTipoServicio(ModeloKiosco.Servicios tipoServicio) {
        this.tipoServicio = tipoServicio;
        return this;
    }

    public int unidades() {
        return unidades;
    }

    public Cliente setUnidades(int unidades) {
        this.unidades = unidades;
        return this;
    }
}
