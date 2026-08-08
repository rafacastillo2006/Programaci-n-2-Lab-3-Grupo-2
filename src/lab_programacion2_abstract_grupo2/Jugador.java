
package lab_programacion2_abstract_grupo2;
/**
 *
 * @author Rafa
 */
public class Jugador {
   
    private String nombre;
    private int puntosPartida;
    private long tiempoAcumulado;
    private long inicioTurno;
    
    public Jugador(){
        this.nombre = nombre;
        this.puntosPartida = 0;
        this.tiempoAcumulado = 0;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getPuntosPartida() {
        return puntosPartida;
    }

    public void sumarAcierto() {
        this.puntosPartida++;
    }
    
    public void iniciarTurno(){
        this.inicioTurno = System.currentTimeMillis();
    }
    
    public void pausaTurno(){
        if(this.inicioTurno > 0){
            this.tiempoAcumulado += (System.currentTimeMillis() - this.inicioTurno);
        }this.inicioTurno=0;
    }
    
    public long getTiempoEnSegundos(){
        return tiempoAcumulado / 1000;
    }
}
