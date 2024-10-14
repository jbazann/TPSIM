package modelotp.componentespropios;

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;
import modelotp.estadodelsistema.Empleada;
import modelotp.estadodelsistema.ModeloKiosco;

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
            System.out.println("Empleada "+ String.valueOf(i+1)+": "+ atendidos);// TODO falta dividir por tiempo total
        }
        System.out.println("Sobre "+ totalClientes + " clientes totales.");
        System.out.println("-------------------------------------------------------");

    }
}
