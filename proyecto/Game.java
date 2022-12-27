package proyecto;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame implements Datos, intrucciones{
	private static final int ANCHO = 1270;
    private static final int ALTO = 600;
    private Tablero miTablero;
    private Equipo eq1, eq2;
    private String [] datos = new String[3];
    private HashMap<String, JLabel> etiquetas;
    private Color[] misColores = new Color[3];
    private JButton[][] botonesAccion;
    private String texto = "";
    private JPanel turno1,turno2, tablero, estadisticas, cabecera, ocurrencia;
    private int fAux, cAux, turno = 1;
    private boolean hacerMovimiento, hayPistaCerca;
    private Detective[] detectives;
    
    public Game(Color c1, Color c2, String n1, String n2, int modoIndice) {
        miTablero = new Tablero();
        eq1 = new Equipo(1, 1);
        eq2 = new Equipo(12, 2);
        
        if (modoIndice == 0) {
            miTablero.generarPistas();
        }
        else if (modoIndice == 1) {
            miTablero.generarPistas();
            eq1.getBase().setRealista();
            eq2.getBase().setRealista();
        }
        //declarando variables
        etiquetas = new HashMap<String, JLabel>();
        detectives = new Detective[2];
        detectives[0] = new Detective(c1, n1);
        detectives[1] = new Detective(c2, n2);
        
        
        //Jugador.actualizarDatos(jugadores[0], jugadores[1]);
        
        
        datos[0] = "Ciudad de Londres";
        datos[1] = n1;
        datos[2] = n2;              
        misColores[0] = new Color(205, 192, 175);
        misColores[1] = c1;
        misColores[2] = c2;  
        
        //GUI
        setTitle("Duelo de Detectives");
        setSize(ANCHO, ALTO);
        setLayout(new BorderLayout());   
        createContents();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
    }
    public void cambios(JButton x) {
    	x.setFont(fuenteCuestionario);
    }
    public void cambios(JLabel x) {
    	x.setFont(fuenteCuestionario);
    }
    //creando el contenido para los botones
    public void createContents() {     
        //creando y editando los paneles
        tablero = new JPanel(new GridLayout(10, 12));		
        tablero.setPreferredSize(new Dimension(650, 600));
        estadisticas = new JPanel(new GridLayout(0, 1));
        estadisticas.setPreferredSize(new Dimension(310, 600));   
        cabecera = new JPanel(new GridLayout(1, 0));
        cabecera.setPreferredSize(new Dimension(1050, 30));
        
        //creando los botones del tablero
        botonesAccion = new JButton[10][12];
        for (int i=0; i<10; i++){
            for (int j=0; j<12; j++) {             
                botonesAccion[i][j] = new JButton("");
                if (j == 0 || j == 11) {      
                    botonesAccion[i][j].setBackground(misColores[2]);
                    if(j == 0)
                        botonesAccion[i][j].setBackground(misColores[1]);                  
                    botonesAccion[i][j].setForeground(Color.WHITE);
                    botonesAccion[i][j].setText(getLista().get(Tablero.toKey(i + 1,j + 1)).getName());
                }
                else 
                    botonesAccion[i][j].setBackground(misColores[0]);                                                                                                
                botonesAccion[i][j].addActionListener(new Acciones());
                cambios(botonesAccion[i][j]);//cambio de fuente
                tablero.add(botonesAccion[i][j]);
            }
        }
        botonesAccion[0][0].setForeground(negativo(misColores[1]));
        botonesAccion[0][11].setForeground(negativo(misColores[2]));
        
        //añadiendo los elementos a top
        ocurrencia= getPanel("Pista");  
        etiquetas.get("Pista").setText("   ¡Hay pista cerca!   ");
        etiquetas.get("Pista").setForeground(new Color(255, 77, 6));// COLOR NARANJA "Hay una pista cerca"
        ocurrencia.setVisible(false);
        
        cabecera.add(turno1 = getPanel("Turno"));//agregamos el nombre del primer detetctive (cabecerea)
        cabecera.add(getPanel("Ataque"));//agregamos las advertencias
        cabecera.add(turno2 = getPanel("Turno2"));//Agregamos el nombre del segundo detective (cabecera)
        cabecera.add(ocurrencia);//agregamos "Hay una pista cerca" (cabecera)
        
        etiquetas.get("Turno").setText(datos[1]);//agregamos nombre de primer detetctive (lateral)
        etiquetas.get("Turno2").setText(datos[2]);//agregamos nombre de segundo detetctive (lateral)
        
        turno1.setBackground(misColores[1]);//fondo primer detetctive    
        turno2.setBackground(misColores[2]);//fondo segundo detective
        turno2.setVisible(false);
       
        add(cabecera, BorderLayout.NORTH);
        add(tablero, BorderLayout.CENTER);
        add(estadisticas, BorderLayout.EAST); //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        addLabel(estadisticas);
   }
   //permite seleccionar un empleado y tambien moverlo a traves del tablero
    private class Acciones implements ActionListener {
        public void actionPerformed(ActionEvent e) {              
            for (int f = 0; f< botonesAccion.length; f++) {
                for(int c = 0; c< botonesAccion[0].length; c++) {
                    if(e.getSource() == botonesAccion[f][c]) {        	
                        if (hacerMovimiento) {
                            if (botonesAccion[f][c].getBackground().equals(cambioLugar))
                            	movimiento(botonesAccion[f][c], f, c);
                            else
                                JOptionPane.showMessageDialog(Game.this, "¡Movimiento Inválido!");  
                            
                            actDatos();//actualizamos datos
                        }
                        else
                            seleccionarEmpleado(f, c);
                    }
                }
            }
        }
    }
    //Listener que permite regresar al menu
    private class Salir implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new Menu();
        }
    }
    //permite seleccionar un empleado del Detective correspondiente
    public void seleccionarEmpleado(int f, int c) {        
        int team = (turno + 1) % 2 + 1;
        detectives[team - 1].aumentarTurno();
        Detective.actualizarDatos(detectives[0], detectives[1]);
        Empleado selec = getLista().get(Tablero.toKey(f + 1, c + 1));
        if(selec != null && getLstTeam(team).get(0) == selec) {	
            etiquetas.get("Ataque").setText("Posibles Ataques alrededor: " + miTablero.getCuadrantes()[f][c].getNumero());  
            cambios( etiquetas.get("Ataque"));//Cambio de fuente
            texto = botonesAccion[f][c].getText();
	        fAux = f;
	        cAux = c;
	        cambiarColor(f, c, cambioLugar);
            if (hayPistaCerca) 
                ocurrencia.setVisible(true);           
	         hacerMovimiento = true; 
        }
        else
            JOptionPane.showMessageDialog(Game.this, "¡Seleccione su empleado!");  
    }     
    //muestra los mensajes de la barra lateral derecha
    public void addLabel(JPanel t) {
    	/*Barra Lateral*/
    	JLabel x= new JLabel("ESTADÍSTICAS", SwingConstants.CENTER); cambios(x); //creamos Jlabel y cambiamos la fuente
        t.add(x);        //agregamos el JLabel de estadistica
        x= new JLabel("Lugar: " + datos[0], SwingConstants.CENTER); cambios(x); //creamos Jlabel y cambiamos la fuente
        t.add(x);        //agregamos el Jlabel de lugar "Ciudad de Londres"
	    t.add(getPanel("D1"));//agregamos el panel del detective 1
	    t.add(getPanel("D2"));//agregamos el panle del detective 2
        t.add(getPanel("Mensaje"));//agregamos el panel del consejo
        x= new JLabel("Empleados Restantes:", SwingConstants.CENTER); cambios(x); //creamos JLAbel y cambiamos fuente
        t.add(x);//agregamos el dato de empleados restantes
        t.add(getPanel("d1"));//agregamos los datos del detective 1
        t.add(getPanel("d2"));//agregamos los datos del detective 2 
	    t.add(getPanel("Base1")); //agregamos cantidad de bases de detctive 1     
        t.add(getPanel("Base2")); //agregamos cantidad de bases de detctive 2  
        JButton salir =  new JButton("Salir"); //Boton de salir
	    etiquetas.get("D1").setText(datos[1]); cambios(etiquetas.get("D1")); 
        etiquetas.get("D2").setText(datos[2]); cambios(etiquetas.get("D2"));
        etiquetas.get("D1").setBackground(misColores[1]);
        etiquetas.get("D1").setForeground(negativo(misColores[1]));
        etiquetas.get("D1").setPreferredSize(new Dimension(20,20));
        etiquetas.get("D2").setBackground(misColores[2]);
        etiquetas.get("D2").setForeground(negativo(misColores[2]));
        salir.addActionListener(new Salir()); cambios(salir);//Accion del boton salir y cambio de fuente
        salir.setBackground(fondoBoton); //Cambio al color de fondo
        t.add(salir);
        actDatos();
    }  
    
    //metodo que retorna un panel con la etiqueta correspondiente ya dentro del el
    public JPanel getPanel(String k) {
        JPanel panel = new JPanel(new BorderLayout());
	    JLabel label = new JLabel("", SwingConstants.CENTER);
        etiquetas.put(k, label);

        if(k.equals("D1"))
            panel.setBackground(misColores[1]);
        else if(k.equals("D2"))
            panel.setBackground(misColores[2]);       
        panel.add(label);
        return panel;
    }
    /*metodo que retorna el negativo de un color
     * lo usamos para saber el empleado en movimiento
     * */
	public Color negativo(Color c) { 
        int red = c.getRed();
        int blue = c.getBlue();
        int green = c.getGreen();
        c = new Color(255 - red, 255 - green, 255 - blue);
        return c;
    }
    //actualiza los datos mostrados en la barra lateral derecha
    public void actDatos() {
        int team = (turno + 1) % 2 + 1;    
	    etiquetas.get("Turno").setText(datos[team]);
	    if (team == 1) {
		    turno1.setVisible(true);
		    turno2.setVisible(false);
		}
		else {
		    turno2.setVisible(true);
		    turno1.setVisible(false);
		}
		etiquetas.get("Turno").setForeground(negativo(misColores[team]));
		cambios(etiquetas.get("Turno")); //cambio de fuente
		cambios(etiquetas.get("Turno2")); //cambio de fuente a los detetctives (cabecera y barra lateral)
		etiquetas.get("d1").setText(datos[1] + ": " + getLstTeam(1).size() + " empleados");//Cantidad de empleados restantes
		cambios(etiquetas.get("d1")); //Cambio de fuente a los datos de la barra lateral
		etiquetas.get("d2").setText(datos[2] + ": " + getLstTeam(2).size() + " empleados");//camtidad de empleados restantes
		cambios(etiquetas.get("d2"));//Cambio de fuente a los datos de la barra lateral
		etiquetas.get("Base1").setText("Bases de "+ datos[1]+": " + eq1.getBase().getVidaBase());
		cambios(etiquetas.get("Base1"));//Cambio de fuente a los datos de la barra lateral
		etiquetas.get("Base2").setText("Bases de "+ datos[2]+": " + eq2.getBase().getVidaBase());
		cambios(etiquetas.get("Base2"));//Cambio de fuente a los datos de la barra lateral
		actMensaje();
	}   
    //actualiza los consejos a lo largo de la partida
    public void actMensaje() {
    	etiquetas.get("Mensaje").setText(consejos[(int)(Math.random()*consejos.length)]);
    	cambios(etiquetas.get("Mensaje"));//Cambio de fuente
    }   
    //obtiene el boton correspondiente segun la ubicacion
    public JButton boton(String ubicacion) {
        int large = ubicacion.length();
        String colum = ubicacion.substring(large - 1);
        int columna = colum.compareTo("A");
        int fila = Integer.parseInt(ubicacion.substring(0, ubicacion.length() - 1)) -1;
        return botonesAccion[fila][columna];
    }    
    //cambia la ubicacion del empleado, tanto en el tablero, como en el HashMap
    public void movimiento(JButton b, int f, int c) {
        hayPistaCerca = false;
        ocurrencia.setVisible(false); // no se muestra el mensaje de "Hay una pista cerca"
        int team = (turno + 1) % 2 + 1; //calcula el tema segun el numero de turno
        cambiarColor(fAux, cAux, misColores[0]);
        if (miTablero.getCuadrantes()[f][c] instanceof Libre) {    
            if(getLista().containsKey(Tablero.toKey(f + 1, c + 1)))              
                enfrentamiento(Tablero.toKey(fAux + 1, cAux + 1), Tablero.toKey(f + 1, c + 1));            
            else {                
                accionMovimiento(f, c, team);
                if (miTablero.getCuadrantes()[f][c].tienePista()) {
                    getLista().get(Tablero.toKey(f +1 , c + 1)).setPista();
                    JOptionPane.showMessageDialog(Game.this, "¡Su personaje obtuvo una pista!");
                    miTablero.getCuadrantes()[f][c]= new Libre(f , c);
                } 
                descubrirNumeros(f, c);    
            }
        }
        else if (miTablero.getCuadrantes()[f][c] instanceof Ataque) {
            accionMovimiento(f, c, team);
            roboDePista(f, c, team);        
        }
        if (c == 0 || c == 11) 
            atacarBase(f, c, team);//llego a la base enemiga
             
        turno++;
        hacerMovimiento = false;        
    }
    //se ejecuta tras iniciarse el movimiento
    public void atacarBase(int f, int c, int team) {
        JOptionPane.showMessageDialog(Game.this, "Base " + (team%2+1) + " Atacada");
        getEquipo(team).getEmpleados().remove(Tablero.toKey(f + 1, c + 1));
        boton(Tablero.toKey(f + 1, c + 1)).setBackground(misColores[0]);
        boton(Tablero.toKey(f + 1, c + 1)).setText("");
        getEquipo(team%2+1).getBase().baseAtacada();
        if (!getEquipo(team%2+1).getBase().enPie()) {
            detectives[team-1].haGanado();
            Detective.actualizarDatos(detectives[0], detectives[1]);
            JOptionPane.showMessageDialog(Game.this, "¡Felicidades "+detectives[team-1].getNombre()+", ha ganado el juego!");
            setVisible(false);
            new Menu();
        }
    }
    //Metodo que cambia de lugar incluye fondo de color
    public void accionMovimiento(int f, int c, int team) {
        botonesAccion[fAux][cAux].setText("");               
        botonesAccion[f][c].setBackground(botonesAccion[fAux][cAux].getBackground());
        botonesAccion[f][c].setForeground(botonesAccion[fAux][cAux].getForeground());
        botonesAccion[fAux][cAux].setBackground(misColores[0]);//Color de fondo
        botonesAccion[fAux][cAux].setForeground(Color.BLACK);
        botonesAccion[f][c].setText(texto); 
        cambios(botonesAccion[f][c]);
        getLista().get(Tablero.toKey(fAux + 1,cAux + 1)).setUbicacion(Tablero.toKey(f + 1, c +1));	                
        getEquipo(team).moverEmpleado(Tablero.toKey(fAux + 1, cAux + 1),Tablero.toKey(f + 1, c + 1));
    }
    //cambia el color de las posiciones seleccionables
    public void cambiarColor(int f, int c, Color color) {
        int i = 1;
        if (turno % 2 == 0)
            i = -1;
        for(int j= -1; j<= 1; j++) {
            if (f + j < 0 || f + j > 9) continue;
            String k = Tablero.toKey(f + j + 1, c  + i + 1);
            if (miTablero.getCuadrantes()[f + j][c + i].tienePista()) {
                hayPistaCerca = true;
            }
            botonesAccion[f + j][c + i].setBackground(color);
            if(!botonesAccion[f + j][c + i].getText().equals("") && hacerMovimiento)
                botonesAccion[f + j][c + i].setBackground(new Color(195, 183, 204));//Color rosa oscuro
            if(getLista().containsKey(k) && color.equals(misColores[0]))
                botonesAccion[f + j][c + i].setBackground(misColores[turno % 2 + 1]);
        }
    }
    //metodo elimina al empleado al ser atacado y "esclarece" las posiciones circundantes
    public void roboDePista(int f, int c, int team) {
        JOptionPane.showMessageDialog(Game.this, "¡Te han atacado, más cuidado en la próxima!");
        miTablero.getCuadrantes()[f][c] = new Libre(f, c);
        if (getLista().get(Tablero.toKey(f + 1, c + 1)).tienePista()) {
            JOptionPane.showMessageDialog(Game.this, "Le han robado su pista"); 
            getLista().get(Tablero.toKey(f + 1, c + 1)).perderPista();
        }
        
        else {      
            detectives[team-1].eliminarEmpleado();
            botonesAccion[f][c].setText("");
            botonesAccion[f][c].setBackground(misColores[0]);
            getEquipo(team).getEmpleados().remove(Tablero.toKey(f + 1, c + 1));
            boton(getLstTeam(team).get(0).getUbicacion()).setForeground(negativo(misColores[team]));           
        }
        for (int i = f - 1; i <= f + 1; i++){
                if (i < 0 || i > 9) continue;
                for (int j = c - 1; j <= c + 1; j++){
                    if (j < 0 || j > 11) continue;
                    if (i == f && j == c) continue;
                    if (miTablero.getCuadrantes()[i][j] instanceof Libre){
                        Libre cNum = (Libre) miTablero.getCuadrantes()[i][j];
                        cNum.disminuirCantidad();
                        if(getLista().containsKey(Tablero.toKey(i + 1, j + 1))) continue;
                        if (cNum.getNumero() <= 0) {
                            botonesAccion[i][j].setText("");
                            botonesAccion[i][j].setBackground(misColores[0]);
                        }
                        else {
                            botonesAccion[i][j].setText(""+miTablero.getCuadrantes()[i][j].getNumero());
                            cambios(botonesAccion[i][j]);//Cambio de fuente
                            botonesAccion[i][j].setBackground(new Color(195, 183, 204));//Color osa oscuro
                        }
                    }
                }   
            }
    }    
    //descubre los numeros que se ubican alrededor de una mina
    public void descubrirNumeros(int f, int c) {
        miTablero.getCuadrantes()[f][c].cambiarEstado();
        if (miTablero.getCuadrantes()[f][c].getNumero() == 0) {
            for (int i = f - 1; i <= f + 1; i++){
                if (i < 0 || i > 9) continue;
                for (int j = c - 1; j <= c + 1; j++){
                    if (j <= 0 || j >= 11) continue;   
                    if(getLista().containsKey(Tablero.toKey(i + 1, j + 1))) continue;
                    if (miTablero.getCuadrantes()[i][j] instanceof Ataque || !miTablero.getCuadrantes()[i][j].getEstado()) 
                    	continue;   
                    if (miTablero.getCuadrantes()[i][j].getNumero() > 0 ) {
                        botonesAccion[i][j].setText(""+miTablero.getCuadrantes()[i][j].getNumero());     
                        cambios(botonesAccion[i][j]);
                        miTablero.getCuadrantes()[i][j].cambiarEstado();
                        botonesAccion[i][j].setBackground(new Color(195, 183, 204));
                    }
                    else 
                        descubrirNumeros(i, j);
                }
            }
        }
        else {
            if ((cAux != 0 && cAux != 11) && miTablero.getCuadrantes()[fAux][cAux].getNumero() > 0) {
                botonesAccion[fAux][cAux].setText(""+miTablero.getCuadrantes()[fAux][cAux].getNumero());  
                cambios(botonesAccion[fAux][cAux]);
                miTablero.getCuadrantes()[fAux][cAux].cambiarEstado();
                botonesAccion[fAux][cAux].setBackground(new Color(195, 183, 204));      
            }
        }
    }   
    //elimina al soldado que pierda tras una lucha
    public void enfrentamiento(String ub1, String ub2) {
        int num = (int)(Math.random() * 2);
        int team1 = getLista().get(ub1).getTeam();
        int team2 = getLista().get(ub2).getTeam();
        if (num == 0) {
            JOptionPane.showMessageDialog(Game.this, "¡Su empleado ganó el duelo!");
            detectives[1].eliminarEmpleado();
            getEquipo(team2).retirarEmpleado(ub2);
            getEquipo(team1).moverEmpleado(ub1, ub2);
            boton(ub2).setText(boton(ub1).getText());
            boton(ub2).setBackground(boton(ub1).getBackground());
        }
        else {
            JOptionPane.showMessageDialog(Game.this,"Su empleado perdió el duelo");
            detectives[0].eliminarEmpleado();
            getEquipo(team1).retirarEmpleado(ub1); 
        }
        boton(ub1).setText("");
        boton(ub1).setBackground(misColores[0]);
    }

    //devuelve la lista ordenada segÃºn el equipo
    public ArrayList<Empleado> getLstTeam(int team){
        if (team == 1)
            return eq1.getLstOrdenada();
        else
            return eq2.getLstOrdenada();
    }    
    //devuelve la lista completa de soldados de ambos ejercitos
    public HashMap<String, Empleado> getLista(){
        HashMap<String, Empleado> lst = new HashMap<String, Empleado>();
        for (Empleado s: eq1.getLstOrdenada())
            lst.put(s.getUbicacion(), s);
        for (Empleado s: eq2.getLstOrdenada())
            lst.put(s.getUbicacion(), s);
        return lst;
    }
    //devuelve el ejercito segÃºn el equipo al que corresponda
    public Equipo getEquipo(int team) {
        if (team == 1)
            return eq1;
        else
            return eq2;
    }
}
