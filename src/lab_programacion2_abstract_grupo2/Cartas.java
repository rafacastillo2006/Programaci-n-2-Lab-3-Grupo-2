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
public abstract class Cartas {
    protected String codigo;
    protected boolean descubierta;
    protected boolean emparejada;

    public Cartas(String codigo) {
        this.codigo = codigo;
        this.descubierta=false;
        this.emparejada=false;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
    }

    public boolean isEmparejada() {
        return emparejada;
    }

    public void setEmparejada(boolean emparejada) {
        this.emparejada = emparejada;
    }
    
    public abstract void mostrar();
    public abstract void ocultar();
    
}
