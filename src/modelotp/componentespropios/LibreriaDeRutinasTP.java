package modelotp.componentespropios;

public class LibreriaDeRutinasTP {

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
        return -(1/lambdaEntreArribo) * Math.log(aleatorio());
    }

    /* calcula el tipo de producto que llevara */
    public String tipoDeProducto() {
        if (aleatorio() <= 0.7) {
            return "Bebida";
        } else {
            return "Panaderia";
        }
    }

    /* calcula la cantidad de producto que llevara */
    public int cantidadProducto(String producto) {
        double r = aleatorio();
        if (producto == "Bebida") {
            if (r <= 0.57) return 1;
            else if (r <= 0.9) return 2;
            else return 3;
        } else {
            if (r <= 0.27) return 1;
            else if (r <= 0.52) return 2;
            else if (r <= 0.87) return 3;
            else return 4;
        }
    }

    /* calcula el tiempo de servicio de la empleada */
    public double tiempoServicioEmpleada(String articulo, int cantidad) {
        if (articulo == "Bebida") {
            return -(1/lambdaServicioBebida) * Math.log(aleatorio());
        } else {
            return -(1/lambdaServicioPanaderia) * Math.log(aleatorio());
        }
    }
}
