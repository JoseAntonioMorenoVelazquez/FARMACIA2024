package Farmacia;

import java.util.*;

public class Cliente {
	private String id;
	private String nombre;
	private String direccion;
	private String telefono;
	private boolean baja;

	private static Scanner entrada = new Scanner(System.in);

	// Constructor vacio
	public Cliente() {

	}

	// Constructor con valores
	public Cliente(String id, String nombre, String direccion, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.baja = false;
	}

	// Constructor copia
	public Cliente(Cliente copiaCliente) {
		this.id = copiaCliente.id;
		this.nombre = copiaCliente.nombre;
		this.direccion = copiaCliente.direccion;
		this.telefono = copiaCliente.telefono;
		this.baja = false;

	}

	// Setters y Getters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean getBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	@Override
	public String toString() {
		String estado = getBaja() ? "baja" : "alta";
		return "CLIENTE.: " + "Id: " + id + "\nNombre: " + nombre + "\nDireccion: " + direccion + "\nTelefono: "
				+ telefono + "\nEstado: " + estado + "\n";
	}
	//Menu principal de Clientes
	public static void gestionarClientes() {
		int opcion;
		do {
			System.out.println("\n--- GESTIONAR CLIENTES ---");
			System.out.println("");
			System.out.println("1. Dar de alta un cliente nuevo");
			System.out.println("2. Buscar cliente por DNI");
			System.out.println("3. Dar de baja un cliente");
			System.out.println("4. Modificar datos del cliente");
			System.out.println("5. Listar clientes");
			System.out.println("6. Volver al menú principal");
			System.out.print("Selecciona una opción: ");
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			opcion = entrada.nextInt();
			entrada.nextLine();

			switch (opcion) {
			case 1:
				altaCliente();
				break;
			case 2:
				buscarCliente();
				break;
			case 3:
				borrarCliente();
				break;
			case 4:
				modificarCliente();
				break;
			case 5:
				listarClientes();
				break;
			case 6:
				System.out.println("Volviendo al menú principal...");
				Principal.mostrarMenuPrincipal();				
				break;
			default:
				System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
			}
		} while (opcion != 6);
	}
	//Metodo para dar de alta un Cliente
	public static void altaCliente() {
		System.out.println("\n--- MENU PRINCIPAL ALTA DE CLIENTE ---");
		System.out.println("");
		System.out.println("Inserte el Id del Nuevo Cliente");
		String id = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(id);
		/*
		 * boolean nifCorrecto = ValidarDatos.validarNIF(id); if
		 * (!ValidarDatos.validarNIF(id)) {
		 * System.out.println("El NIF introducido no es válido."); do { id =
		 * entrada.nextLine().toUpperCase(); ValidarDatos.stringNoVacio(id); } while
		 * (nifCorrecto); }
		 */
		boolean clienteExistente = comprobarSiExisteCliente(id);
		if (clienteExistente) {
			System.out.println("El cliente con el ID " + id + " ya existe.");
			System.out.println("Volvemos al menu principal");
			altaCliente();

		} else {

			System.out.println("Inserte el Nombre del Nuevo Cliente");
			String nombre = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nombre);
			System.out.println("Inserte la Direccion del Nuevo Cliente");
			String direccion = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(direccion);
			System.out.println("Inserte el Telefono del Nuevo Cliente");
			String telefono = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(telefono);			
			Cliente cli = new Cliente(id, nombre, direccion, telefono);
			Principal.clientes.add(cli);
			System.out.println("Los Datos del Nuevo Cliente son.:");
			System.out.println(cli);
			gestionarClientes();
		}
	}
	//Comprueba si existe el cliente
	private static boolean comprobarSiExisteCliente(String id) {
		boolean clienteExistente = false;
		for (Cliente cliente : Principal.clientes) {
			if (cliente.getId().equals(id)) {
				clienteExistente = true;
			}
		}
		return clienteExistente;
	}
	//Menu para Buscar un Cliente
	public static void buscarCliente() {
		System.out.println("\n--- MENU PRINCIPAL PARA BUSCAR CLIENTE ---");
		System.out.println("");
		System.out.println("Inserte el Id del Cliente");
		String id = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(id);
		Cliente clienteEncontrado = null;
		for (Cliente c : Principal.clientes) {
			if (c.getId().equals(id) && c.getBaja() == false) {
				clienteEncontrado = c;
			}
		}

		if (clienteEncontrado != null) {
			System.out.println("Cliente encontrado:");
			System.out.println(clienteEncontrado);

		} else {
			System.out.println("No se encontró ningún cliente con ese Id.");
		}
		gestionarClientes();
		entrada.nextLine();
	}
	//Menu para Borrar un Cliente
	public static void borrarCliente() {
		System.out.println("\n--- MENU PRINCIPAL PARA BORRAR CLIENTE ---");
		System.out.println("");
		System.out.println("Inserte el Id del Cliente");
		String id = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(id);
		Cliente clienteABorrar = null;
		for (Cliente c : Principal.clientes) {
			if (c.getId().equals(id)) {
				clienteABorrar = c;
				clienteABorrar.setBaja(true);			
				System.out.println("");
				System.out.println("Cliente borrado exitosamente.");
			} else {
				System.out.println("No se encontró ningún cliente con ese Id.");
			}
		}
		gestionarClientes();		
	}
	//Menu para Modificar un Cliente
	public static void modificarCliente() {
		System.out.println("\n--- MENU PRINCIPAL PARA MODIFICAR CLIENTE ---");
		System.out.println("");
		System.out.println("Inserte el Id del Cliente que Desea Modificar");
		String id = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(id);
		Cliente clienteAModificar = null;
		for (Cliente c : Principal.clientes) {
			if (c.getId().equals(id)) {
				clienteAModificar = c;
				System.out.println(clienteAModificar);
			}
		}
		if (clienteAModificar == null) {
			System.out.println("No se encontró ningún cliente con ese Id.");
			gestionarClientes();
		}
		System.out.println("\n--- TIPO DE MODIFICACION ---");
		System.out.println("");
		System.out.println("1. Modificar Id");
		System.out.println("2. Modificar Nombre");
		System.out.println("3. Modificar Direccion");
		System.out.println("4. Modificar Telefono");
		while (!entrada.hasNextInt()) {
			System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
			entrada.next();
		}
		int opcion = entrada.nextInt();
		entrada.nextLine();

		switch (opcion) {
		case 1:
			System.out.println("Inserte el Nuevo Id del Cliente");
			String idNuevo = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(idNuevo);
			clienteAModificar.setId(idNuevo);
			System.out.println(clienteAModificar);
			break;
		case 2:
			System.out.println("Inserte el Nuevo Nombre del Cliente");
			String nombreNuevo = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nombreNuevo);
			clienteAModificar.setNombre(nombreNuevo);
			System.out.println(clienteAModificar);
			break;
		case 3:
			System.out.println("Inserte la Nueva Dirección del Cliente");
			String direccionNueva = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(direccionNueva);
			clienteAModificar.setDireccion(direccionNueva);
			System.out.println(clienteAModificar);
			break;
		case 4:
			System.out.println("Inserte el Nuevo Teléfono del Cliente");
			String telefonoNuevo = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(telefonoNuevo);
			clienteAModificar.setTelefono(telefonoNuevo);
			System.out.println(clienteAModificar);
			break;
		default:
			System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
			gestionarClientes();
		}
		System.out.println("Cliente modificado exitosamente.");

		gestionarClientes();
	}
	//Menu para listar los Clientes
	public static void listarClientes() {
		System.out.println("\n--- MENU PRINCIPAL DE LISTADO DE CLIENTES ---");
		if (Principal.clientes.isEmpty()) {
			System.out.println("El listado esta vacio");
		} else {
			for (Cliente c : Principal.clientes) {
				if (c.getBaja() == false) {
					System.out.println(c);
				}
			}
		}
		gestionarClientes();
	}

}
