package proyecto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Cuestionario extends JFrame implements Datos, intrucciones{    
    private static final int ANCHO = 600;
    private static final int ALTO = 400;
    private JPanel seccionI, seccionD; 
    private JButton enviar, mColor, mColor2;
    private JComboBox color1, color2, modo;
    private JTextField nombre1, nombre2;
    
    public Cuestionario() {
        setTitle("Ingrese Datos");
        setSize(ANCHO, ALTO);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createContents();    
        setVisible(true);
    }
    public void createContents() {
        //creando la seccion izquieda del cuestionario
    	JLabel aux= new JLabel("Ingrese su nombre de usuario");
    	aux.setFont(fuenteCuestionario);
        seccionI = new JPanel(new GridLayout(7, 1));
        seccionI.add(new JLabel(" "));
        seccionI.add(aux);
        seccionI.add(nombre1 = new JTextField(10));
        aux= new JLabel("Seleccione un color");
        aux.setFont(fuenteCuestionario);
        seccionI.add(aux);
        color1 = new JComboBox(colores);
        color1.setFont(fuenteCuestionario);
        color1.setSelectedIndex(0);
        color1.addActionListener(new MColor());
        mColor= new JButton("");
        mColor.setBackground(Color.YELLOW);
        JPanel selecColor= new JPanel(new GridLayout(1, 2));
        selecColor.add(color1);
        selecColor.add(mColor);
        seccionI.add(selecColor); 
        
        //creando la seccion derecha del cuestionario
        seccionD = new JPanel(new GridLayout(7, 1));
        seccionD.add(new JLabel(" "));
        aux= new JLabel("Ingrese su nombre de usuario");
        aux.setFont(fuenteCuestionario);
        seccionD.add(aux);
        seccionD.add(nombre2 = new JTextField(10));
        aux=new JLabel("Seleccione un color");
        aux.setFont(fuenteCuestionario);
        seccionD.add(aux);       
        color2= new JComboBox(colores);
        color2.setFont(fuenteCuestionario);
        color2.addActionListener(new MColor());
        color1.setSelectedIndex(0);
        mColor2 = new JButton("");
        mColor2.setBackground(Color.YELLOW);
        JPanel selecColor2 = new JPanel(new GridLayout(1, 2));
        selecColor2.add(color2);
        selecColor2.add(mColor2);
        seccionD.add(selecColor2);
        
        //sección de modos de juego y el botón de enviar
        enviar = new JButton("Enviar");
        enviar.setFont(fuenteCuestionario);
        JPanel sup = new JPanel(new GridLayout(1,3));
        JPanel inf = new JPanel(new GridLayout(5,3));
        aux= new JLabel("Modo de Juego  ", SwingConstants.RIGHT);
        aux.setFont(fuenteCuestionario);
        inf.add(aux);
        modo = new JComboBox(modos);
        modo.setFont(fuenteCuestionario);
        inf.add(modo);
        JPanel ayudaPanel= new JPanel(new GridLayout(1,5));
        JButton ayuda = new JButton("?");
        for(int i=1; i<= 4; i++) {
            if(i == 2)
        	ayudaPanel.add(ayuda);
            else
        	ayudaPanel.add(new JLabel(""));
        }
        ayuda.addActionListener(new MostrarAyuda());
        inf.add(ayudaPanel);
        for(int i= 1; i<= 4; i++)
            inf.add(new JLabel(""));
        inf.add(enviar);
        for(int i= 1; i<= 4; i++)
            inf.add(new JLabel(""));
        JLabel espacio = new JLabel("");
        sup.add(seccionI);
        sup.add(espacio);
        sup.add(seccionD);
        JPanel titulo = new JPanel();
        aux= new JLabel("INGRESE DATOS DE LOS JUGADORES");
        aux.setFont(fuenteCuestionario);
        titulo.add(aux);
        add(titulo,BorderLayout.NORTH);
        add(sup,BorderLayout.CENTER);
        add(new JLabel("     "),BorderLayout.WEST);
        add(new JLabel("     "),BorderLayout.EAST);
        add(inf, BorderLayout.SOUTH);
        enviar.addActionListener(new Enviar());
    }
   
    private class MColor implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == color1)
                mColor.setBackground(COLORES[color1.getSelectedIndex()]);
            else
                mColor2.setBackground(COLORES[color2.getSelectedIndex()]);
        }
    }
    private class Enviar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int c1 = color1.getSelectedIndex();
            int c2 = color2.getSelectedIndex();
            boolean condicion = (!(nombre1.getText().equals("")) && !(nombre2.getText().equals("")));
            if ((c1 != c2) && condicion) {
                JOptionPane.showMessageDialog(Cuestionario.this, "Datos Enviados");   
                setVisible(false);
                new Game(COLORES[c1], COLORES[c2], nombre1.getText(), nombre2.getText(), modo.getSelectedIndex());
            }
            else 
                JOptionPane.showMessageDialog(Cuestionario.this, "Ingrese y/o seleccione datos correctos");           
        }
    }   
    private class MostrarAyuda implements ActionListener {
        public void actionPerformed(ActionEvent e) {            
            JOptionPane.showMessageDialog(Cuestionario.this, ayudas[modo.getSelectedIndex()]);           
        }
    }   
}