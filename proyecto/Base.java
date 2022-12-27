package proyecto;

public class Base {
    private int vidaBase;
    private boolean baseEnPie;
    
    public Base() {
        vidaBase = 3;
        baseEnPie = true;
    }
    public void setRealista() {
        vidaBase = 1;
    }
    public void baseAtacada() {
        vidaBase--;
        if (vidaBase == 0 )
            baseEnPie = false;       
    }
    public int getVidaBase() {
        return vidaBase;
    }   
    public boolean enPie() {
    	return baseEnPie;
    }
}
