package proyecto;


public class Ataque extends Cuadrante {
	public static final int cantAtaques = 20;
	
    public Ataque(int f, int c) {
    	super(f, c);
        posibleAtaque = true;
	}
}
