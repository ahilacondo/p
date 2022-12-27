package proyecto;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;


public class Menu extends JFrame implements intrucciones{
    private static final int ANCHO = 850;
    private static final int ALTO = 600;
    private JPanel botones, datosDePartidas, textGuia;
    private JButton start, creditos, historial, guia, regresar;
    private JLabel imagen;
    private boolean viendoHistorial;
    private Detective jug1, jug2;
    public Menu() {
        setTitle("Bienvenido a Duelo de Detectives");
        setSize(ANCHO, ALTO);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createContents();    
        setVisible(true);
    } 
    
    public void createContents() {
        imagen = new JLabel();
        imagen.setBounds(0,0,ANCHO,ALTO);
        botones = new JPanel(new GridLayout(2, 2, 2, 2)); 
        botones.setBackground(Color.BLACK);
        start = new JButton("Nuevo Juego");
        cambios(start);
        creditos = new JButton("Créditos");
        cambios(creditos);
        historial = new JButton("Historial de Juego");
        cambios(historial);
        guia = new JButton("Como jugar");
        cambios(guia);
        regresar = new JButton("Regresar");
        cambios(regresar);
        
        JTextArea instrucciones = new JTextArea(INSTRUCCIONES);
        instrucciones.setFont(fuenteCuestionario);
        instrucciones.setBackground(new Color(162, 150, 133));
        
        instrucciones.setEditable(false);
        textGuia= new JPanel(new BorderLayout());
        /*extra*/
        JPanel extra= new JPanel();
        extra.setBackground(instruccionesColor);
        /*espacio del marco de las intrucciones*/
        textGuia.add(extra, BorderLayout.WEST);
        /*ingresa INSTRUCCIONES*/
        textGuia.add(instrucciones);
        /*extra*/
        extra= new JPanel();
        extra.setBackground(instruccionesColor);
        /*espacio del marco de las intrucciones*/
        textGuia.add(extra, BorderLayout.EAST);
        
        botones.add(start);      
        botones.add(historial);
        botones.add(guia);
        botones.add(creditos);
        
        start.addActionListener(new Accion());
        creditos.addActionListener(new Creditos());
        historial.addActionListener(new Accion());
        guia.addActionListener(new Accion());
        regresar.addActionListener(new Regresar());
        botones.setPreferredSize(new Dimension(ANCHO, 100));
        
        ImageIcon juego= new ImageIcon("juego.png");
        imagen.setIcon(new ImageIcon(juego.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH)));
        add(imagen, BorderLayout.NORTH);
        add(botones, BorderLayout.SOUTH);      
    }
    
    private void cambios(JButton x) {
		x.setForeground(colorBoton);
		x.setBackground(fondoBoton);
		x.setFont(fuente);
	}

	private class Accion implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            imagen.setVisible(false);
            botones.setVisible(false);
            viendoHistorial = false;     
            if (e.getSource() == start) {
                setVisible(false);
                new Cuestionario();
            }
           // else if (e.getSource() == historial) {}
            	
            else {
            	JLabel i= new JLabel("   INSTRUCCIONES:");
            	i.setForeground(Color.WHITE);
            	i.setFont(fuente);
            	textGuia.add(i, BorderLayout.NORTH);
            	textGuia.setBackground(instruccionesColor);
                add(textGuia, BorderLayout.CENTER);
                add(regresar, BorderLayout.SOUTH);
                textGuia.setVisible(true);                
                regresar.setVisible(true);              
            }
        }
    }
    private class Creditos implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(Menu.this, CREDITOS);
        }
    }
    private class Regresar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	setSize(ANCHO, ALTO);
            if (viendoHistorial)
                datosDePartidas.setVisible(false);            
            else 
                textGuia.setVisible(false);              
            regresar.setVisible(false);
            imagen.setVisible(true);
            botones.setVisible(true);
        }
    }
    
    public static void main(String args[]) {
        new Menu();
    }


}


