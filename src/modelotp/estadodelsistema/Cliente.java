package modelotp.estadodelsistema;

public class Cliente {

    public double tiempoDeArribo;

    public Cliente(double tiempoDeArribo) {
        super();
        this.tiempoDeArribo = tiempoDeArribo;
    }

    public double getTiempoDeArribo() {
        return tiempoDeArribo;
    }

    public void setTiempoDeArribo(double tiempoDeArribo) {
        this.tiempoDeArribo = tiempoDeArribo;
    }

}
