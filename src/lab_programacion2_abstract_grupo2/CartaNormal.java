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
public class CartaNormal extends Cartas{
    private String nombre;

    public CartaNormal(String nombre, String codigo) {
        super(codigo);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
