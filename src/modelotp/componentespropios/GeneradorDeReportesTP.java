package modelotp.componentespropios;

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;
import modelotp.estadodelsistema.ModeloKiosco;

import java.util.List;

import static modelotp.estadodelsistema.ModeloKiosco.reloj;

public class GeneradorDeReportesTP extends GeneradorDeReportes {
    
    public void run(ContadoresEstadisticos contadores) {
        ContadoresEstadisticosTP contadoresTP = (ContadoresEstadisticosTP) contadores;

        System.out.println("------------------------------------------------------");
        System.out.println("***GENERADOR DE REPORTES *** ");
        System.out.println("------------------------------------------------------");

        System.out.println("Beneficios obtenidos: $" + String.valueOf(
                contadoresTP.unidadesVendidasPorProducto(ModeloKiosco.panaderia) * (ModeloKiosco.precioPanaderia - ModeloKiosco.costoPanaderia)
                + contadoresTP.unidadesVendidasPorProducto(ModeloKiosco.bebida) * (ModeloKiosco.precioPanaderia - ModeloKiosco.costoBebida)
                + " por " + contadoresTP.unidadesVendidasPorProducto(ModeloKiosco.panaderia) + ' ' + ModeloKiosco.panaderia
                + " y " + contadoresTP.unidadesVendidasPorProducto(ModeloKiosco.bebida) + ' ' + ModeloKiosco.bebida
        ));
        System.out.println("------------------------------------------------------");
        System.out.println("Tasa de atención por empleada:");
        int totalClientes = 0;
        for (int i = 0; i < ModeloKiosco.cantidadEmpleadas; i++) {
            int atendidos = contadoresTP.clientesAtendidosPorEmpleada(i+1);
            totalClientes += atendidos;
            System.out.println("Empleada "+(i+1)+": "+ atendidos/reloj.getValor()*60 + " clientes por hora.");
        }
        System.out.println("Sobre "+ totalClientes + " clientes totales.");
        System.out.println("-------------------------------------------------------");

        List<Double> tiempos = ((ContadoresEstadisticosTP) contadores).tiemposEnSistema();
        System.out.println("Tiempo promedio de clientes en sistema: "+
                tiempos.stream().reduce(Double::sum).get()/tiempos.size()+" minutos sobre "+tiempos.size() +" clientes atendidos.");

        System.out.println("-------------------------------------------------------");

        System.out.println("Porcentaje de tiempo libre por empleada:");
        for (int i = 0; i < ModeloKiosco.cantidadEmpleadas; i++) {
            int atendidos = contadoresTP.clientesAtendidosPorEmpleada(i+1);
            totalClientes += atendidos;
            double ocupada = contadoresTP.tiempoTotalOcupada(i+1);
            System.out.println("Empleada "+(i+1)+" estuvo libre: "+ Math.round(((reloj.getValor()-ocupada)/reloj.getValor())*100) + "% del tiempo.");
        }

    }
}
