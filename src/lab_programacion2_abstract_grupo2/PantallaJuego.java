
package lab_programacion2_abstract_grupo2;

import javax.swing.*;
import java.awt.*;


public class PantallaJuego extends JPanel{
    private JLabel labelP1;
    private JLabel labelP2;
    
    public PantallaJuego(){
        setLayout(new BorderLayout());
        
        JPanel panelSuperior = new JPanel(new GridLayout(1,2));
        labelP1 = new JLabel("Jugador 1: ", SwingConstants.CENTER);
        labelP2 = new JLabel("Jugador 2: ", SwingConstants.CENTER);
        panelSuperior.add(labelP1);
        panelSuperior.add(labelP2);
        add(panelSuperior, BorderLayout.NORTH);
    }
    
    public void configurarJugadores(String nombreJugador1, String nombreJugador2) {
        labelP1.setText("Jugador 1: " + nombreJugador1);
        labelP2.setText("Jugador 2: " + nombreJugador2);
    }
}
