import javax.swing.JFrame;
import javax.swing.JFrame;

package lab_programacion2_abstract_grupo2;

/**
 *
 * @author Rafa
 */
public class Lab_Programacion2_abstract_Grupo2 {

    
    public static void main(String[] args) {

javax.swing.SwingUtilities.invokeLater(() -> {
        JFrame frame = new JFrame("Juego de Memoria Pokémon - Configuración");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PantallaConfiguracion pantalla = new PantallaConfiguracion(null);
        
        frame.add(pantalla);
        frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    });        
    
    }
    
}
