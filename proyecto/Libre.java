package proyecto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Libre extends Cuadrante {
    private int numero;
    private boolean tienePista;
    public static final int cantPistas = 4;
    
    public Libre(int f, int c) {
        super(f, c);
        posibleAtaque = false;
    }
    public void setNumero(int num) {
        numero = num;
    }
    public void setPista(){
        tienePista = true;
    }
    public int getNumero() {
        return numero;
    }
    public void disminuirCantidad() {
        numero--;
    }
    
    public boolean tienePista() {
    	return tienePista;
    }
}

