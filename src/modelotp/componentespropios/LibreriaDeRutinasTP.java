package modelotp.componentespropios;

import des.LibreriaDeRutinas;
import modelotp.estadodelsistema.ModeloKiosco;

public class LibreriaDeRutinasTP extends LibreriaDeRutinas{

    double x = 45;
    double a = 1664525;
    double c = 5;
    double m = (double) 4294967296.0;

    double lambdaEntreArribo = 0.25;
    double lambdaServicioBebida = 1/2.4;
    double lambdaServicioPanaderia = 1/3.5;

    public double aleatorio() {
        x = ((a * x) + c) % m;
        return x/(m-1);
    }

    /* calcula el tiempo entre arribo de los clientes */
    public double tiempoEntreArriboCliente() {
        double r = aleatorio();
        double t = -(1/lambdaEntreArribo) * Math.log(r);
        System.out.println("\t\t--TIEMPO ENTRE ARRIBO con aleatorio:" + r + " ; " + t);
        return t;
    }

    /* calcula el tipo de producto que llevara */
    public String tipoDeProducto() {
        double r = aleatorio();
        String p = "";

        if (r <= 0.7) {
            p = ModeloKiosco.bebida;
        } else {
            p = ModeloKiosco.panaderia;
        }
        System.out.println("\t\t--TIPO PRODUCTO con aleatorio: " + r + " ; " + p);
        return p;
    }

    /* calcula la cantidad de producto que llevara */
    public int cantidadProducto(String producto) {
        double r = aleatorio();
        int c = 0;

        if (producto.equals(ModeloKiosco.bebida)) {
            if (r <= 0.57) c = 1;
            else if (r <= 0.9) c = 2;
            else c = 3;
        } else {
            if (r <= 0.27) c = 1;
            else if (r <= 0.52) c = 2;
            else if (r <= 0.87) c = 3;
            else c = 4;
        }
        System.out.println("\t\t--CANTIDAD PRODUCTO con aleatorio: " + r + " ; " + c);
        return c;
    }

    /* calcula el tiempo de servicio de la empleada */
    public double tiempoServicioEmpleada(String articulo, int cantidad) {
        double r = aleatorio();
        double t = 0d;

        if (articulo.equals(ModeloKiosco.bebida)) {
            t = -(1/lambdaServicioBebida) * Math.log(r);
        } else {
            t = -(1/lambdaServicioPanaderia) * Math.log(r);
        }

        System.out.println("\t\t--TIEMPO SERVICIO con aleatorio: " + r + " ; " + t);
        return t;
    }
}
