package proyecto;
import java.awt.*;

interface intrucciones {
	String INSTRUCCIONES= """
			    'Duelo de Detectives'\n
			  --> Juego de 2 jugadores competitivo, en el que tendran que tomar el rol de un detective
			  --> Una persona fue acusa de un delito, tanto los agraviados y los padres del acusado contratan un detective
			  --> con la intenci�n de demostrar la culpa e inocencia respectivamente, para ello tienen 7 d�as.
			  --> Cada jugador posee 2 fichas de accion con las que podr� visitar a los personajes y activar sus habilidades.
			  --> Cada personaje posee una habilidad diferente que se le presentara al colocar su ficha de accion sobre �l, depende del jugador si activar la habilidad del personaje.
			  --> Los personajes iran apareciendo aleatoriamente (uno) por cada d�a que pase, excepto el primer d�a que se generan 3 personajes automaticamente
			  --> Pasado la semana, gana el Detective que posea m�s y mejores pistas.
			  --> Si tiene alguna duda puede encontrar nuestro correo en los cr�ditos.
			              """;
	Color instruccionesColor= new Color(32, 26, 21);
	String CREDITOS="""
			Duelo de Detectives v1.0\n
			Curso: Fundamentos de Programaci�n 2\n
			Desarrolladores:
			- Andre Hilacondo Begazo (ahilacondo@unsa.edu.pe)
			- Daniel Marron Carcausto (dmarronc@unsa.edu.pe)\n
			Agradecimientos a: Marco Aedo (docente del curso)
			En caso de alg�n error cont�ctese con cualquiera de nosotros
			Esperamos y lo disfruten.
			""";
	Color colorBoton= Color.BLACK;
	Color fondoBoton= new Color(221, 175, 39);
	Font fuente= new Font("Exotc350 DmBd BT",1,20);
	Font fuenteCuestionario= new Font("Roboto 12 Bold",1,15);
	Color cambioLugar= new Color(130, 130, 130);
}
