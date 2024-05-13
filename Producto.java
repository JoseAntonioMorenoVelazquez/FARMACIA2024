package Farmacia;

import java.util.*;

public abstract class Producto {
	private String codigo;
	private String nombre;
	private String descripcion;
	private float precio;
	private Integer unidades;
	private boolean baja;

	private static Scanner entrada = new Scanner(System.in);

	// Constructor vacio

	public Producto() {
	}

	// Constructor con parametros

	public Producto(String codigo, String nombre, String descripcion, float precio, Integer unidades) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.unidades = unidades;
		this.baja = false;
	}

	// Constructor copia

	public Producto(Producto copiaProducto) {
		this.codigo = copiaProducto.codigo;
		this.nombre = copiaProducto.nombre;
		this.descripcion = copiaProducto.descripcion;
		this.precio = copiaProducto.precio;
		this.unidades = copiaProducto.unidades;
		this.baja = false;
	}

	// getters y Setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public boolean getBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}
	// Metodos

	public  boolean comprobarCodigos(String codigo) {

		return this.codigo.equals(codigo);
	}

	public boolean anadirUnidades(Integer cantidad) {

		if (cantidad > 0) {
			this.unidades += cantidad;
			return true;
		} else {
			System.out.println("La cantidad a añadir debe ser mayor que 0.");
			return false;
		}
	}

	public boolean quitarUnidades(Integer cantidad) {

		if (cantidad > 0 && this.unidades >= cantidad) {
			this.unidades -= cantidad;
			return true;
		} else if (cantidad <= 0) {
			System.out.println("La cantidad a quitar debe ser mayor que 0.");
			return false;
		} else {
			System.out.println("No hay suficientes unidades disponibles para quitar.");
			return false;
		}
	}

	@Override
	public String toString() {		
		return "Codigo: " + codigo + "\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\nPrecio: " + precio
				+ "\nUnidades: " + unidades;
	}

	// Menu principal
	public static void menuSeleccionProducto() {
		int seleccion;
		do {
		System.out.println("\n--- MENU PRINCIPAL DE PRODUCTO ---");
		System.out.println("");
		System.out.println("     --- TIPO DE PRODUCTO ---");
		System.out.println("");
		System.out.println("1. Medicamento");
		System.out.println("2. Producto de Parafarmacia");
		System.out.println("3. Buscar Productos");
		System.out.println("4. Listar Productos");
		System.out.println("5. Borrar Productos");
		System.out.println("6. Volver al menu de Inicio");
		while (!entrada.hasNextInt()) {
			System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
			entrada.next();
		}
		seleccion = entrada.nextInt();
		entrada.nextLine();		

			switch (seleccion) {
			case 1:
				Medicamento.gestionarMedicamento();				
				break;
			case 2:
				ParaFarmacia.gestionarParaFarmacia();				
				break;
			case 3:
				buscarProducto();				
				break;
			case 4:
				listarProductos();				
				break;
			case 5:
				borrarProducto();				
				break;			
			case 6:
				System.out.println("Volviendo al menú principal...");
				Principal.mostrarMenuPrincipal();				
				break;
			default:
				System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
			}
		} while (seleccion != 6);
	}

	// Menu para Buscar Productos
	public static void buscarProducto() {
		System.out.println("\n--- Menú Principal para Buscar un Producto ---");
		System.out.println("Inserte el Codigo del Producto");
		String codigo = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(codigo);
		Producto productoEncontrado = null;
		for (Producto p : Principal.productos) {
			if (p.getCodigo().equals(codigo) && p.getBaja() == false) {
				productoEncontrado = p;
			}
		}

		if (productoEncontrado != null) {
			System.out.println("Producto encontrado:");
			System.out.println(productoEncontrado);

		} else {
			System.out.println("No se encontró ningún producto con ese codigo.");
		}
		menuSeleccionProducto();
		
	}

	public static void borrarProducto() {
		System.out.println("\n--- MENU PRINCIPAL PARA BORRAR PRODUCTO ---");
		System.out.println("Inserte el Codigo del Producto");
		String codigo = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(codigo);
		Producto productoABorrar = null;
		for (Producto p : Principal.productos) {
			if (p.getCodigo().equals(codigo) && p.getBaja() == false) {
				productoABorrar = p;
				productoABorrar.setBaja(true);				
				System.out.println("");
				System.out.println("Producto borrado exitosamente.");
			} else {
				System.out.println("No se encontró ningún producto con ese Codigo.");
			}
		}
		menuSeleccionProducto();
	}

	public static void listarProductos() {
		System.out.println("\n--- MENU PRINCIPAL DE LISTADO DE PRODUCTOS ---");
		if (Principal.productos.isEmpty()) {
			System.out.println("El listado esta vacio");
		} else {
			for (Producto p : Principal.productos) {
				if (p.getBaja() == false) {
					System.out.println(p);
				}
			}
		}
		menuSeleccionProducto();
	}

	protected static boolean comprobarSiExisteProducto(String codigo) {
		boolean productoExistente = false;
		for (Producto producto : Principal.productos) {
			if (producto.getCodigo().equals(codigo)) {
				productoExistente = true;
			}
		}
		return productoExistente;
	}

}
