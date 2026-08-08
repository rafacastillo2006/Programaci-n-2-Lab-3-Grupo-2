
package lab_programacion2_abstract_grupo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class PantallaJuego extends JPanel{
    
    private final MainWindow ventanaPrincipal;
    private final TableroLogica logica;
    private JLabel labelP1;
    private JLabel labelP2;
    private JLabel turno;
    private JButton[][] botonesJuego;private Timer temporizadorOcultar;
    private boolean bloqueado;
    
    public PantallaJuego(MainWindow ventanaPrincipal, TableroLogica logica){
        this.ventanaPrincipal = ventanaPrincipal;
        this.logica = logica;
        this.botonesJuego = new JButton[6][6];
        this.bloqueado = false;
        
        setLayout(new BorderLayout(10,10));
        
        JPanel panelSuperior = new JPanel(new GridLayout(1,3));
        labelP1 = new JLabel("Jugador 1: ", SwingConstants.LEFT);
        labelP2 = new JLabel("Jugador 2: ", SwingConstants.RIGHT);
        turno = new JLabel("", SwingConstants.CENTER);
        panelSuperior.add(labelP1);
        panelSuperior.add(labelP2);
        panelSuperior.add(turno);
        turno.setForeground(Color.BLUE);
        add(panelSuperior, BorderLayout.NORTH);
        
        JPanel tablero = new JPanel(new GridLayout(6,6,5,5));
        
        for (int fila = 0; fila < 6; fila++) {
            for (int col = 0; col < 6; col++) {
                JButton boton = new JButton("?");
                boton.setFont(new Font("SansSerif", Font.BOLD, 12));

                final int f = fila;
                final int c = col;

                boton.addActionListener((ActionEvent e) -> manejarClicCarta(f, c));

                botonesJuego[fila][col] = boton;
                tablero.add(boton);
            }
        }
        add(tablero, BorderLayout.CENTER);
    
        temporizadorOcultar = new Timer(1000, (ActionEvent e) -> {
            logica.ocultarCartasFallidas();
           actualizarGUI();
            bloqueado = false;
            temporizadorOcultar.stop();
        });
                   
    actualizarGUI();
    }
    
    private void manejarClicCarta(int f, int c){
        if (bloqueado){return;}
        
        try{
            logica.seleccionarCarta(f,c);
            actualizarGUI();
            if (logica.hayDosCartasSeleccionadas()) {
                Cartas carta1 = logica.getPrimeraSeleccion();
                Cartas carta2 = logica.getSegundaSeleccion();

                if (carta1.getCodigo().equals(carta2.getCodigo())) {
                    if (logica.verificarFinPartida()) {
                        mostrarResultadoFinal();
                    }
        }
                else 
                {
            bloqueado = true;
            temporizadorOcultar.start();
                }
            }
        }
            catch(IndexOutOfBoundsException ex){
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
    }
    
    public void actualizarGUI() {
        labelP1.setText(logica.getJugador1().getNombre() + ": " + logica.getPuntosJugador1() + " pts");
        labelP2.setText(logica.getJugador2().getNombre() + ": " + logica.getPuntosJugador2() + " pts");
        turno.setText("Turno: " + logica.getJugadorActual().getNombre());

        for (int f = 0; f < 6; f++) {
            for (int c = 0; c < 6; c++) {
                Cartas carta = logica.getCartaEn(f, c);
                JButton boton = botonesJuego[f][c];

                if (carta.isEmparejada()) {
                    boton.setText(carta.getCodigo());
                    boton.setEnabled(false);
                    boton.setBackground(Color.LIGHT_GRAY);
                } else if (carta.isDescubierta()) {
                    boton.setText(carta.getCodigo());
                    boton.setEnabled(true);
                    boton.setBackground(Color.WHITE);
                } else {
                    boton.setText("?");
                    boton.setEnabled(true);
                    boton.setBackground(null);
                }
            }
        }
    }

    private void mostrarResultadoFinal() {
        String anuncio;
        if (logica.hayEmpate()) {
            anuncio = "El juego termina en empate con: " + logica.getPuntosJugador1() + " aciertos cada uno.";
        } else {
            Jugador ganador = logica.obtenerGanador();
            anuncio = "Ganador: " + ganador.getNombre() + "\n" +
                      "Aciertos: " + ganador.getPuntosPartida() + "\n" +
                      "Tiempo usado: " + ganador.getTiempoEnSegundos() + " segundos.";
        }

        JOptionPane.showMessageDialog(this, anuncio, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        ventanaPrincipal.volverAConfiguracion();
    }
    
}
