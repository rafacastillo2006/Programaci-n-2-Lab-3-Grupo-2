package lab_programacion2_abstract_grupo2;

import javax.swing.*;
import java.awt.*;

public class PantallaConfiguracion extends JPanel {
    
    private final MainWindow ventanaPrincipal;
    
    private JTextField campoJugador1;
    private JTextField campoJugador2;
    private JButton botonJugar;
    
    public PantallaConfiguracion(MainWindow ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        configurarPanel();
    }
    
    private void configurarPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel titulo = new JLabel("Juego de Memoria Pokemon", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        
        JLabel labelJugador1 = new JLabel("Nombre del jugador 1");
        campoJugador1 = new JTextField(15);
        JLabel labelJugador2 = new JLabel("Nombre del jugador 2");
        campoJugador2 = new JTextField(15);
        botonJugar = new JButton("Jugar!");
        botonJugar.addActionListener(e -> {
            
            String nombreJugador1 = campoJugador1.getText().trim();
            String nombreJugador2 = campoJugador2.getText().trim();
            
            try {
                validarNombres(nombreJugador1, nombreJugador2);
                ventanaPrincipal.iniciarJuego(nombreJugador1, nombreJugador2);
                campoJugador1.setText("");
                campoJugador2.setText("");
                
            } catch (DatosIncompletosException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            }
            
        });

        //gbc 
        gbc.gridy = 0;
        add(titulo, gbc);
        gbc.gridy = 1;
        add(labelJugador1, gbc);
        gbc.gridy = 2;
        add(campoJugador1, gbc);
        gbc.gridy = 3;
        add(labelJugador2, gbc);
        gbc.gridy = 4;
        add(campoJugador2, gbc);
        gbc.gridy = 5;
        add(botonJugar, gbc);
        
    }

    private void validarNombres(String nombreJugador1, String nombreJugador2) 
            throws DatosIncompletosException {
        boolean faltaP1 = nombreJugador1.isEmpty();
        boolean faltaP2 = nombreJugador2.isEmpty();
        
        if (faltaP1 && faltaP2) {
            throw new DatosIncompletosException(
            "Debes ingresar el nombre de los 2 jugadores");
        } else if (faltaP1) {
            throw new DatosIncompletosException(
            "Debes ingresar el nombre de jugador 1");
        } else if (faltaP2) {
            throw new DatosIncompletosException(
            "Debes ingresar el nombre de jugador 2");
        }
    }
    
}
