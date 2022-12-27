package proyecto;
import java.awt.*;
interface Datos {
	
    String[] modos = {"Clásico","Turbo"};
    Color[] COLORES = {Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE
			, Color.MAGENTA, Color.PINK, Color.ORANGE};
    String[] colores = {"Amarillo", "Rojo", "Verde", "Azul"
			, "Magenta", "Rosa", "Naranja"};
    String[] consejos = {"Observa bien la cantidad de enemigos",
			"Puede haber pistas cerca, ¡atento!",
			"¡Vamos! a por la base del otro Detective",
			"Si consiguio una pista, será inmune a los ataques",
			"Los números le indican los posibles ataques a ese cuadrante",
			"Evite las luchas con otros personajes, ¡razone!"};
	
    String[] ayudas =   {"Gana el que lleve cinco personajes a la \nbase enemiga",
			"Similar al clásico pero solo es necesario \nconquistar una base del otro detective"};
}
