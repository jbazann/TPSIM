package modelotp.componentespropios;

import java.util.LinkedList;

import des.Evento;
import des.LibreriaDeRutinas;
import des.ListaDeEventos;
import des.RelojDeSimulacion;
import modelotp.estadodelsistema.ModeloKiosco;
import modelotp.eventos.EventoArribaACola;

public class ListaDeEventosTP extends ListaDeEventos {

    public void inicializar(LibreriaDeRutinas libreria, RelojDeSimulacion reloj) {
        lista = new LinkedList<Evento>();
        LibreriaDeRutinasTP rutina = (LibreriaDeRutinasTP) libreria;
        Evento primerEvento = new EventoArribaACola(rutina.tiempoEntreArriboCliente());
        agregar(primerEvento);
        ModeloKiosco.reloj = reloj;
    }
}
