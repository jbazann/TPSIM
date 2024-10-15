package modelotp.componentespropios;

import des.LibreriaDeRutinas;
import modelotp.estadodelsistema.ModeloKiosco;

import static modelotp.estadodelsistema.ModeloKiosco.Servicios.BEBIDA;
import static modelotp.estadodelsistema.ModeloKiosco.Servicios.PANADERIA;

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
    public ModeloKiosco.Servicios tipoDeProducto() {
        double r = aleatorio();
        ModeloKiosco.Servicios p;

        if (r <= 0.7) {
            p = BEBIDA;
        } else {
            p = PANADERIA;
        }
        System.out.println("\t\t--TIPO PRODUCTO con aleatorio: " + r + " ; " + p);
        return p;
    }

    /* calcula la cantidad de producto que llevara */
    public int cantidadProducto(ModeloKiosco.Servicios producto) {
        double r = aleatorio();
        int c = 0;

        if (producto.equals(BEBIDA)) {
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
    public double tiempoServicioEmpleada(ModeloKiosco.Servicios articulo, int cantidad) {
        double r = aleatorio();
        double t = 0d;
        double t_adicional = 0d;

        if (articulo.equals(BEBIDA)) {
            t = -(1/lambdaServicioBebida) * Math.log(r);
            t_adicional = ((cantidad == 2) ? 0.1*t : ((cantidad == 3) ? 0.13*t : 0));
            t += t_adicional; ;
        } else {
            t = -(1/lambdaServicioPanaderia) * Math.log(r);
            t_adicional = ((cantidad == 2) ? 0.12*t : ((cantidad == 3) ? 0.15*t : ((cantidad == 4) ? 0.2*t : 0)));
            t += t_adicional; ;
        }

        System.out.println("\t\t--TIEMPO SERVICIO con aleatorio: " + r + " y adicional "+t_adicional +" por cantidad "+ cantidad+ " ; " + t);
        return t;
    }
}
