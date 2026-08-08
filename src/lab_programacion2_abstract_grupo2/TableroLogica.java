package lab_programacion2_abstract_grupo2;

import java.util.ArrayList;

public class TableroLogica {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;

    private Cartas[][] tablero;

    private Cartas primeraSeleccion;
    private Cartas segundaSeleccion;

    public TableroLogica(String nombreJ1, String nombreJ2) {

        jugador1 = new Jugador(nombreJ1);
        jugador2 = new Jugador(nombreJ2);
        jugadorActual = jugador1;

        tablero = new Cartas[6][6];

        primeraSeleccion = null;
        segundaSeleccion = null;
    }


    public void iniciarJuego() {

        ArrayList<Cartas> mazo = new ArrayList<>();

        for (int i = 1; i <= 18; i++) {
            String nombreCarta = "Carta " + i; 
            String codigoCarta = String.valueOf(i);
            
            mazo.add(new CartaNormal(nombreCarta, codigoCarta));
            mazo.add(new CartaNormal(nombreCarta, codigoCarta));
        }

        for (int i = 0; i < mazo.size(); i++) {
            int posicionAleatoria = (int) (Math.random() * mazo.size());
            
            Cartas cartaTemporal = mazo.get(i);
            mazo.set(i, mazo.get(posicionAleatoria));
            mazo.set(posicionAleatoria, cartaTemporal);
        }

        int posicion = 0;

        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 6; columna++) {

                tablero[fila][columna] = mazo.get(posicion);
                posicion++;
            }
        }

        primeraSeleccion = null;
        segundaSeleccion = null;
        jugadorActual = jugador1;
        
        jugadorActual.iniciarTurno();
    }

    public void seleccionarCarta(int fila, int columna) {

        if (fila < 0 || fila >= 6 ||
            columna < 0 || columna >= 6) {

            throw new IndexOutOfBoundsException(
                "Posición fuera del tablero."
            );
        }

        if (primeraSeleccion != null && segundaSeleccion != null) {
             ocultarCartasFallidas();
        }

        Cartas carta = tablero[fila][columna];

        if (carta.isEmparejada()) {
            return;
        }

        if (carta.isDescubierta()) {
            return;
        }

        carta.mostrar();

        if (primeraSeleccion == null) {

            primeraSeleccion = carta;
            return;
        }

        segundaSeleccion = carta;

        verificarPareja(
            primeraSeleccion,
            segundaSeleccion
        );
    }


    public boolean verificarPareja(Cartas c1, Cartas c2) {
        
        if (c1 == null || c2 == null) {
             return false;
        }

        if (c1.getCodigo().equals(c2.getCodigo())) {

            c1.setEmparejada(true);
            c2.setEmparejada(true);

            jugadorActual.sumarAcierto();

            primeraSeleccion = null;
            segundaSeleccion = null;

            return true;
        }

        return false;
    }

    public void ocultarCartasFallidas() {

        if (primeraSeleccion != null && segundaSeleccion != null) {

            if (!primeraSeleccion.isEmparejada()) {
                 primeraSeleccion.ocultar();
            }
            if (!segundaSeleccion.isEmparejada()) {
                 segundaSeleccion.ocultar();
            }

            cambiarTurno();

            primeraSeleccion = null;
            segundaSeleccion = null;
        }
    }


    public void cambiarTurno() {
        
        jugadorActual.pausaTurno();

        if (jugadorActual == jugador1) {
            jugadorActual = jugador2;
        } else {
            jugadorActual = jugador1;
        }
        
        jugadorActual.iniciarTurno();
    }


    public boolean verificarFinPartida() {

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                if (!tablero[fila][columna].isEmparejada()) {
                    return false;
                }
            }
        }

        return true;
    }

    public Jugador obtenerGanador() {

        if (!verificarFinPartida()) {
            return null;
        }

        if (jugador1.getPuntosPartida() > jugador2.getPuntosPartida()) {
            return jugador1;
        }

        if (jugador2.getPuntosPartida() > jugador1.getPuntosPartida()) {
            return jugador2;
        }

        return null; // En caso de empate
    }

    public boolean hayEmpate() {

        if (!verificarFinPartida()) {
            return false;
        }

        return jugador1.getPuntosPartida() == jugador2.getPuntosPartida();
    }
    
    public Cartas getCartaEn(int fila, int columna) {
        return tablero[fila][columna];
    }

    public Cartas[][] getTablero() {
        return tablero;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public Cartas getPrimeraSeleccion() {
        return primeraSeleccion;
    }

    public Cartas getSegundaSeleccion() {
        return segundaSeleccion;
    }

    public boolean hayDosCartasSeleccionadas() {
        return primeraSeleccion != null &&
               segundaSeleccion != null;
    }

    public int getPuntosJugador1() {
        return jugador1.getPuntosPartida();
    }

    public int getPuntosJugador2() {
        return jugador2.getPuntosPartida();
    }
}