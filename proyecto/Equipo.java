package proyecto;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Equipo {
    private HashMap<String, Empleado> empleados = new HashMap<String, Empleado>();
    private Base miBase;
    private int team;
	
    public Equipo(int c, int team) {
        miBase = new Base();
        this.team= team;
	String ub;
	for(int f= 0; f< 10; f++) {
            ub= Tablero.toKey(f+1, c);
            empleados.put(ub, new Empleado(team, f + 1, ub));
        }
    }
	
    public int getTeam(){
        return team;
    }
    public Base getBase() {
        return miBase;
    }
    public HashMap<String, Empleado> getEmpleados() {
        return empleados;
    }
    
    public ArrayList<Empleado> getLstOrdenada() {
        ArrayList<Empleado> lst= new ArrayList<Empleado>(); 
	for (Empleado s: empleados.values())
            lst.add(s);
        for(int i = 0; i< lst.size()- 1; i++) {
            for(int j= 0; j< lst.size()- 1; j++) {
                if(lst.get(j).getNumero() > lst.get(j + 1).getNumero()){
                    Empleado aux= lst.get(j);
                    lst.set(j, lst.get(j + 1));
                    lst.set(j + 1, aux);
                }
            }
        }
	return lst;		
    }
    
    public void retirarEmpleado(String k) {
        empleados.remove(k);
    }  
    public void moverEmpleado(String k1, String k2) {
        empleados.get(k1).setUbicacion(k2);
        empleados.put(k2, empleados.remove(k1));
    }    
}

