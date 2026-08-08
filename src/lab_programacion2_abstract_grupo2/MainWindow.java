
package lab_programacion2_abstract_grupo2;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    
    public static final String pantalla_config = "Configuracion";
    public static final String pantalla_juego = "Juego";
    
    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private PantallaJuego pantallaJuego;
    private TableroLogica logicaJuego;
    
    public MainWindow() {
        setTitle("Juego De Memoria Pokemon");
        setBackground(Color.gray);
        setSize(700, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);
        PantallaConfiguracion pantallaConfiguracion = new PantallaConfiguracion(this);
        
        panelContenedor.add(pantallaConfiguracion, pantalla_config);
        add(panelContenedor);
        cardLayout.show(panelContenedor, pantalla_config);

        setVisible(true);
    }
    
    public void mostrarPantalla(String nombrePantalla) {
        cardLayout.show(panelContenedor, nombrePantalla);
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }
    
    public void iniciarJuego(String nombrep1, String nombrep2) {
        logicaJuego = new TableroLogica(nombrep1, nombrep2);
        logicaJuego.iniciarJuego();
        
        pantallaJuego = new PantallaJuego(this, logicaJuego);
        panelContenedor.add(pantallaJuego, pantalla_juego);
        cardLayout.show(panelContenedor, pantalla_juego);
        
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }

    public void volverAConfiguracion() {
        mostrarPantalla(pantalla_config);
    }
}