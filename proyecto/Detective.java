package proyecto;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Detective implements Serializable {
    private int cantEmpleados;
    private Color colorEscogido;
    private String nombreJugador;
    private boolean haGanado;
    private int turnosUtilizados;
    
    public Detective(Color c, String nombreJ){
        cantEmpleados = 10;
        haGanado = false;
        colorEscogido = c;
        nombreJugador = nombreJ;
        turnosUtilizados = 0;
    }     
    public String getNombre() {
        return nombreJugador;
    }
    public int getCantEmpleados() {
        return cantEmpleados;
    }
    public Color getColor() {
        return colorEscogido;
    }
    public String getEstado() {
        if (haGanado)
            return "Sí";
        return "No";
    }
    public int cantTurnos() {
        return turnosUtilizados;
    }
    public void eliminarEmpleado() {
        cantEmpleados--;
    }
    public void aumentarTurno() {
        turnosUtilizados++;
    }
    public void haGanado() {
        haGanado = true;
    }
    public double puntajeFinal() {
        double puntajeFinal = 0.0;
        if (haGanado) 
            puntajeFinal += 500.0;
        puntajeFinal += cantEmpleados*25.0;
        puntajeFinal = (puntajeFinal*(100.0-turnosUtilizados*(0.1)))/100.0;
        
        return puntajeFinal;
    }
    public static void actualizarDatos(Detective j1, Detective j2){
        ObjectOutputStream fileOut;
        try {
            fileOut = new ObjectOutputStream(new FileOutputStream("ultimaPartida.dat"));
            fileOut.writeObject(j1);
            fileOut.writeObject(j2);
            fileOut.close();
        } 
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }   
    public static Detective[] obtenerDatosJugador () {
        Detective[] jugadores = new Detective[2];
        ObjectInputStream fileIn;
        try {
            fileIn = new ObjectInputStream(new FileInputStream("ultimaPartida.dat"));
            jugadores[0] = (Detective) fileIn.readObject();
            jugadores[1]  = (Detective) fileIn.readObject();
            fileIn.close();
        } 
        catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound: " + e.getMessage());
        }
        return jugadores;
    }
}
