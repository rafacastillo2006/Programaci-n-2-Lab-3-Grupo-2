
package lab_programacion2_abstract_grupo2;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{
    
    public static final String pantalla_config = "Configuracion";
    public static final String pantalla_juego = "Juego";
    
    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private PantallaJuego pantallaJuego;
    
    public MainWindow(){
        setTitle("Juego De Memoria Pokemon");
        setBackground(Color.gray);
        setSize(700, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);
        PantallaConfiguracion pantallaConfiguracion = new PantallaConfiguracion(this);
        pantallaJuego = new PantallaJuego();
        
        panelContenedor.add(pantallaConfiguracion, pantalla_config);
        //panelContenedor.add(pantallaJuego, pantalla_juego);
        add(panelContenedor);
        cardLayout.show(panelContenedor, pantalla_config);

        setVisible(true);
    }
    
    public void mostrarPantalla(String nombrePantalla){
    cardLayout.show(panelContenedor, nombrePantalla);
    }
    
    public void iniciarJuego(String nombrep1, String nombrep2){
    pantallaJuego.configurarJugadores(nombrep1, nombrep2);
        mostrarPantalla(pantalla_juego);
    }
}
