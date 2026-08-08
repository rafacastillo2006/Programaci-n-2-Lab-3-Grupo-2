/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_programacion2_abstract_grupo2;

/**
 *
 * @author Ian Suazo Palao
 */
public class CartaAbilidad extends Cartas{
   private int segundos;

    public CartaAbilidad(int segundos, String codigo) {
        super(codigo);
        this.segundos = segundos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
   
    public void aplicarHabilidad(Jugador jugador){
        if (jugador!=null){
            jugador.pausaTurno();
        }
    }
    
    public void mostrar(){
        descubierta=true;
    }
    
    public void ocultar(){
        if(emparejada==false){
            descubierta=false;
        }
    }
}
