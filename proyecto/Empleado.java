package proyecto;

public class Empleado {
    private boolean tienePista;    
    private String nombre, ubicacion;
    private int team;
	
    public Empleado(int team, int num, String ubicacion) {
	nombre= "Empleado" + team + "-" + num;
	this.team= team;
	this.ubicacion= ubicacion;
    }
	
    public void setUbicacion(String ub) {
        ubicacion= ub;
    }
	
    public void setPista() {
	tienePista = true;
    }
	
    public void perderPista() {
	tienePista = false;
    }
	
    public boolean tienePista() {
	return tienePista;
    }
	
    public String getNombre() {
	return nombre;
    }
	
    public String getName() {
	return "E - " + getNumero();
    }
	
    public int getTeam() {
	return team;
    }
	
    public int getNfila() {
	return Integer.parseInt(ubicacion.substring(0, ubicacion.length() - 1));
    }
	
    public int getNcolumna() {
	String letras= "ABCDEFGHIJKL";
	int large= ubicacion.length();
	String columna = ubicacion.substring(large - 1, large);
	return letras.indexOf(columna) + 1;		
    }
	
    public int getNumero() {
	int i = nombre.indexOf("-") + 1;
	int num = Integer.parseInt(nombre.substring(i, nombre.length()));
	return num;
    }
	
    public String getUbicacion() {
        return ubicacion;
    }
}

