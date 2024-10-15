package modelotp.componentespropios;

import des.ContadoresEstadisticos;
import modelotp.estadodelsistema.ModeloKiosco;

import java.util.ArrayList;
import java.util.HashMap;

public class ContadoresEstadisticosTP extends ContadoresEstadisticos {

    private final HashMap<Integer, Double> tiempoTotalOcupada = new HashMap<>();
    private final HashMap<String, Integer> unidadesVendidasPorProducto = new HashMap<>();
    private final HashMap<Integer, Integer> clientesAtendidosPorEmpleada = new HashMap<>();
    private final ArrayList<Integer> clientesEnCola = new ArrayList<>();
    private final ArrayList<Double> tiempoDeClienteEnKiosco = new ArrayList<>();

    public void inicializar() {
        for (int i = 0; i < ModeloKiosco.cantidadEmpleadas; i++) {
            tiempoTotalOcupada.put(i+1,0d);
            clientesAtendidosPorEmpleada.put(i+1,0);
        }
        ModeloKiosco.productos.forEach(p -> unidadesVendidasPorProducto.put(p,0));
    }

    public void sumarTiempoOcupada(int idEmpleada, double tiempo) {
        tiempoTotalOcupada.merge(idEmpleada,tiempo,Double::sum);// suma tiempo al valor actual
    }

    public void sumarUnidadesVendidas(String producto, int unidades) {
        unidadesVendidasPorProducto.merge(producto,unidades,Integer::sum);// suma unidades al valor actual
    }

    public void sumarClienteAtendido(int idEmpleada) {
        clientesAtendidosPorEmpleada.merge(idEmpleada, 1, Integer::sum);// lo de arriba
    }


    public Double tiempoTotalOcupada(int idEmpleada) {
        return tiempoTotalOcupada.get(idEmpleada);
    }

    public Integer unidadesVendidasPorProducto(String producto) {
        return unidadesVendidasPorProducto.get(producto);
    }

    public Integer clientesAtendidosPorEmpleada(int idEmpleada) {
        return clientesAtendidosPorEmpleada.get(idEmpleada);
    }

    public void agregarCantidadDeClientesEnCola(int cantidadClientes) {
        clientesEnCola.add(cantidadClientes);
    }

    public void agregarTiempoDeClienteEnKiosco(double cantidadDeTiempo) {
        tiempoDeClienteEnKiosco.add(cantidadDeTiempo);
    }

    public ArrayList<Integer> getClientesEnCola() {
        return this.clientesEnCola;
    }

    public ArrayList<Double> getTiempoDeClienteEnKiosco() {
        return this.tiempoDeClienteEnKiosco;
    }

}
