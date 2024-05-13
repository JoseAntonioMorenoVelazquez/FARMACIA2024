package Farmacia;

import java.util.*;

public class Principal {
	public static Scanner entrada = new Scanner(System.in);
	public static String archivoClientes = "Clientes.txt";
	public static ArrayList<Cliente> clientes = ArchivoTexto.cargarClientesDesdeArchivo(archivoClientes);
	public static String archivoProductos = "Productos.txt";
	public static ArrayList<Producto> productos = ArchivoTexto.cargarProductosDesdeArchivo(archivoProductos);

	public static void main(String[] args) {
		ArchivoTexto.crearArchivoDeTexto();
		mostrarMenuPrincipal();
	}

	//Muestra el menu principal
	public static void mostrarMenuPrincipal() {
		int opcion;
		System.out.println("\n- GESTION DE FARMACIA -");
		System.out.println("");
		System.out.println("--- MENU PRINCIPAL ---");
		System.out.println("");
		System.out.println("1. Gestionar Clientes");
		System.out.println("2. Gestionar Productos");
		System.out.println("3. Guardar y Salir");
		System.out.print("Selecciona una opción: ");

		do {
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			opcion = entrada.nextInt();

			switch (opcion) {
			case 1:
				Cliente.gestionarClientes();
				break;
			case 2:
				Producto.menuSeleccionProducto();
				break;
			case 3:
				System.out.println("Saliendo de la aplicación...");
				ArchivoTexto.escribirEnArchivoClientes(clientes);
				ArchivoTexto.escribirEnArchivoProductos(productos);
				System.exit(0);
				break;
			default:
				System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
				break;
			}
		} while (opcion != 3);

	}

}
