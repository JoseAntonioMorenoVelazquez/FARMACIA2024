package Farmacia;

import java.util.*;

public class ValidarDatos {
	private static Scanner entrada = new Scanner(System.in);

	public ValidarDatos() {
	}
	//Metodo para validar el formato del Nif
	public static boolean validarNIF(String nif) {

		if (nif == null || nif.length() != 9) {
			return false;
		}

		String digitos = nif.substring(0, 8);
		String letra = nif.substring(8).toUpperCase();

		int numNIF = Integer.parseInt(digitos);
		char letraCalculada = calcularLetraNIF(numNIF);
		return letraCalculada == letra.charAt(0);
	}
	//Metodo para comprobar la letra de Nif
	public static char calcularLetraNIF(int nif) {
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int indice = nif % 23;
		return letras.charAt(indice);
	}
	//Metodo para comprobar si se queda el dato sin introducir
	public static String stringNoVacio(String dato) {
		
		do {

			if (dato.isEmpty()) {
				System.out.println("Por favor, introduce un valor válido (no puede estar vacío).");
				System.out.println("Inserte el valor correcto");
				dato = entrada.nextLine().toUpperCase();
			}
		} while (dato.isEmpty());
		return dato;
	}
}