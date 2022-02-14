package test;

import java.util.Scanner;

import config.ConexionBD;

public class TestPrueba {

	public static final int[] P = { 2, 3, 5, 7, 11, 13, 17 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Ingrese el número de iteraciones: ");
		int q = sc.nextInt();
		System.out.println("Ingrese el ID de la pila que desea iterar:");
		int idPila = sc.nextInt();
		System.out.println(TestPrueba.obtenerArrayRespuesta(ConexionBD.obtenerDatosBD(idPila), q));
		sc.close();
	}

	public static String obtenerArrayRespuesta(int[] array, int iteraciones) {
		int[] b = null;
		int[] aTemporal = null;
		int[] respuesta = new int[0];

		for (int i = 0; i < iteraciones; i++) {
			b = new int[0];
			aTemporal = new int[0];
			for (int j = array.length - 1; j >= 0; j--) {
				if ((array[j] % TestPrueba.P[i]) == 0) {
					b = TestPrueba.validacionArray(b, array[j]);
					respuesta = TestPrueba.validacionArray(respuesta, array[j]);
				} else {
					aTemporal = TestPrueba.validacionArray(aTemporal, array[j]);
				}
			}
			array = aTemporal;
		}
		for (int i = 0; i < array.length; i++) {
			respuesta = TestPrueba.validacionArray(respuesta, array[i]);
		}
		StringBuilder arrayFinal = new StringBuilder("Respuesta = ");
		for (int i = 0; i < respuesta.length; i++) {
			arrayFinal.append(respuesta[i]);
			if (i + 1 != respuesta.length) {
				arrayFinal.append(",");
			}
		}
		return arrayFinal.toString();
	}

	public static int[] validacionArray(int[] array, int data) {
		int posicionFinal = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				posicionFinal = i + 1;
			} else {
				posicionFinal = i;
				break;
			}
		}
		int[] newArray = new int[posicionFinal + 1];
		for (int i = 0; i < posicionFinal; i++) {
			newArray[i] = array[i];
		}
		newArray[posicionFinal] = data;
		return (newArray);
	}

}
