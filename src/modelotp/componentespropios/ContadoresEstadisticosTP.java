package modelotp.componentespropios;

import des.ContadoresEstadisticos;
import modelotp.estadodelsistema.ModeloKiosco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ContadoresEstadisticosTP extends ContadoresEstadisticos {

    private final HashMap<Integer, Double> tiempoTotalOcupada = new HashMap<>();
    private final HashMap<ModeloKiosco.Servicios, Integer> unidadesVendidasPorProducto = new HashMap<>();
    private final HashMap<Integer, Integer> clientesAtendidosPorEmpleada = new HashMap<>();
    private final LinkedList<Double> tiemposEnSistema = new LinkedList<>();
    private final ArrayList<Integer> clientesEnCola = new ArrayList<>();

    public void inicializar() {
        for (int i = 0; i < ModeloKiosco.cantidadEmpleadas; i++) {
            tiempoTotalOcupada.put(i+1,0d);
            clientesAtendidosPorEmpleada.put(i+1,0);
        }
        ModeloKiosco.productos.forEach(p -> unidadesVendidasPorProducto.put(p,0));
    }


    public void agregarCantidadDeClientesEnCola(int cantidadClientes) {
        clientesEnCola.add(cantidadClientes);
    }

    public ArrayList<Integer> getClientesEnCola() {
        return this.clientesEnCola;
    }

    public void sumarTiempoEnSistema(double t_arr, double t_fin) {
        tiemposEnSistema.add(t_fin - t_arr);
    }

    public void sumarTiempoOcupada(int idEmpleada, double tiempo) {
        tiempoTotalOcupada.merge(idEmpleada,tiempo,Double::sum);// suma tiempo al valor actual
    }

    public void sumarUnidadesVendidas(ModeloKiosco.Servicios producto, int unidades) {
        unidadesVendidasPorProducto.merge(producto,unidades,Integer::sum);// suma unidades al valor actual
    }

    public void sumarClienteAtendido(int idEmpleada) {
        clientesAtendidosPorEmpleada.merge(idEmpleada, 1, Integer::sum);// lo de arriba
    }

    public List<Double> tiemposEnSistema() {
        return tiemposEnSistema;
    }

    public Double tiempoTotalOcupada(int idEmpleada) {
        return tiempoTotalOcupada.get(idEmpleada);
    }

    public Integer unidadesVendidasPorProducto(ModeloKiosco.Servicios producto) {
        return unidadesVendidasPorProducto.get(producto);
    }

    public Integer clientesAtendidosPorEmpleada(int idEmpleada) {
        return clientesAtendidosPorEmpleada.get(idEmpleada);
    }

}
