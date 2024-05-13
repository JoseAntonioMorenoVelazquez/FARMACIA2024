package Farmacia;

import java.util.*;

public class Medicamento extends Producto {
	private String tipo="MEDICAMENTO.";
	private TipoMedicamento tipoMedicamento;
	private String comoTomar;
	private String efectosAdversos;
	private static Scanner entrada = new Scanner(System.in);

	// Constructor vacio
	public Medicamento() {
		super();
	}

	// Constructor
	public Medicamento(String tipo,String codigo, String nombre, String descripcion, float precio, Integer unidades,
			TipoMedicamento tipoMedicamento, String comoTomar, String efectosAdversos) {
		super(codigo, nombre, descripcion, precio, unidades);
		this.tipo=tipo;
		this.tipoMedicamento = tipoMedicamento;
		this.comoTomar = comoTomar;
		this.efectosAdversos = efectosAdversos;
	}
	// Constructor copia

	public Medicamento(Medicamento copiaProducto) {
		super(copiaProducto);
		this.tipo=copiaProducto.tipo;
		this.tipoMedicamento = copiaProducto.tipoMedicamento;
		this.comoTomar = copiaProducto.comoTomar;
		this.efectosAdversos = copiaProducto.efectosAdversos;

	}

	// Getters y Setters
	public String getTipo() {
		return tipo;
	}
	public String getComoTomar() {
		return comoTomar;
	}

	public void setComoTomar(String comoTomar) {
		this.comoTomar = comoTomar;
	}

	public String getEfectosAdversos() {
		return efectosAdversos;
	}

	public void setEfectosAdversos(String efectosAdversos) {
		this.efectosAdversos = efectosAdversos;
	}

	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}

	public void setTipoMedicamento(TipoMedicamento tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}

	@Override
	public String toString() {
		String estado = getBaja() ? "baja" : "alta";
		return "MEDICAMENTO.: " + super.toString() + "\nComo tomar: " + comoTomar + "\nEfectos Adversos: "
				+ efectosAdversos + "\nEstado.: " + estado+"\n";
	}

	// Menu principal medicamentos
	public static void gestionarMedicamento() {
		int opcion;
		do {
			System.out.println("\n--- GESTIONAR MEDICAMENTOS ---");
			System.out.println("");
			System.out.println("1. Dar de alta un Medicamento");			
			System.out.println("2. Modificar datos del Medicamento");			
			System.out.println("3. Volver al menú principal de productos");
			System.out.print("Selecciona una opción: ");
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			opcion = entrada.nextInt();
			entrada.nextLine();

			switch (opcion) {
			case 1:
				altaMedicamento();				
				break;			
			case 2:
				modificarMedicamento();				
				break;			
			case 3:
				System.out.println("Volviendo al menú principal de productos...");
				menuSeleccionProducto();				
				break;
			default:
				System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
				
			}
		} while (opcion != 3);

	}

	// Matodo para dar de Alta
	public static void altaMedicamento() {
		System.out.println("\n--- MENU PRINCIPAL DE ALTA DE MEDICAMENTOS ---");
		System.out.println("");
		System.out.println("Inserte el Codigo del Nuevo Medicamento");
		String codigo = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(codigo);
		String tipo="MEDICAMENTO.";
		boolean productoExistente = Producto.comprobarSiExisteProducto(codigo);
		if (productoExistente) {
			System.out.println("El Medicamento con el CODIGO " + codigo + " ya existe.");
			System.out.println("Volvemos al menu principal");
			gestionarMedicamento();

		} else {
			System.out.println("Inserte el Nombre del Nuevo Medicamento");
			String nombre = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nombre);
			System.out.println("Inserte la Descripcion del Nuevo Medicamento");
			String descripcion = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(descripcion);
			System.out.println("Inserte el Precio del Nuevo Medicamento");
			float precio;
			while (!entrada.hasNextFloat()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			precio = entrada.nextFloat();
			System.out.println("Inserte la Cantidad del Nuevo Medicamento");
			int cantidad;
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			cantidad = entrada.nextInt();
			System.out.println("Inserte la Categotria del Nuevo Medicamento");
			System.out.println("Seleccione el Tipo de Medicamento:");
			System.out.println("1.  ANALGESICOS");
			System.out.println("2.  LAXANTES");
			System.out.println("3.  ANTIINFECCIOSOS");
			System.out.println("4.  ANTIDEPRESIVOS");
			System.out.println("5.  ANTITUSIVOS");
			System.out.println("6.  MUCOLITICOS");
			System.out.println("7.  ANTIACIDOS");
			System.out.println("8.  ANTIULCEROSOS");
			System.out.println("9.  ANTIALERGICOS");
			System.out.println("10. ANTIFIARREICOS");
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			int seleccionTipo = entrada.nextInt();
			TipoMedicamento tipoMedicamento;
			switch (seleccionTipo) {
			case 1:
				tipoMedicamento = TipoMedicamento.ANALGESICOS;
				break;
			case 2:
				tipoMedicamento = TipoMedicamento.LAXANTES;
				break;
			case 3:
				tipoMedicamento = TipoMedicamento.ANTIINFECCIOSOS;
				break;
			case 4:
				tipoMedicamento = TipoMedicamento.ANTIDEPRESIVOS;
				break;
			case 5:
				tipoMedicamento = TipoMedicamento.ANTITUSIVOS;
				break;
			case 6:
				tipoMedicamento = TipoMedicamento.MUCOLITICOS;
				break;
			case 7:
				tipoMedicamento = TipoMedicamento.ANTIACIDOS;
				break;
			case 8:
				tipoMedicamento = TipoMedicamento.ANTIULCEROSOS;
				break;
			case 9:
				tipoMedicamento = TipoMedicamento.ANTIALERGICOS;
				break;
			case 10:
				tipoMedicamento = TipoMedicamento.ANTIFIARREICOS;
				break;
			default:
				System.out.println("Opción inválida. Seleccionando tipo por defecto (ANALGESICOS).");
				tipoMedicamento = TipoMedicamento.ANALGESICOS;
				break;
			}

			System.out.println("Inserte como Tomar el Medicamento");
			entrada.nextLine();
			String comoTomar = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(comoTomar);

			System.out.println("Inserte los Efectos Adversos del Medicamento");
			String efectosAdversos = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(efectosAdversos);

			Producto med = new Medicamento(tipo,codigo, nombre, descripcion, precio, cantidad, tipoMedicamento, comoTomar,
					efectosAdversos);
			Principal.productos.add(med);
			System.out.println("Los Datos del Nuevo Medicamento son.:");
			System.out.println(med);
		}
	}

	// Metodo para modificar el medicamento
	public static void modificarMedicamento() {
		System.out.println("\n--- MENU PRINCIPAL PARA MODIFICAR MEDICAMENTO ---");
		System.out.println("Inserte el Codigo del Medicamento que Desea Modificar");
		String codigo = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(codigo);
		Medicamento medicamentoAModificar = null;
		for (Producto m : Principal.productos) {
			if (m instanceof Medicamento && m.getCodigo().equals(codigo)) {
				medicamentoAModificar = (Medicamento) m;
			}
		}
		if (medicamentoAModificar == null) {
			System.out.println("No se encontró ningún Medicamento con ese Codigo.");
			gestionarMedicamento();

		}
		System.out.println("\n--- TIPO DE MODIFICACION ---");
		System.out.println("1. Modificar Código");
		System.out.println("2. Modificar Nombre");
		System.out.println("3. Modificar Descripción");
		System.out.println("4. Modificar Precio");
		System.out.println("5. Modificar Unidades");
		System.out.println("6. Modificar Cómo Tomar");
		System.out.println("7. Modificar Efectos Adversos");
		System.out.println("8. Modificar Tipo de Medicamento");
		while (!entrada.hasNextInt()) {
			System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
			entrada.next();
		}
		int opcion = entrada.nextInt();
		entrada.nextLine();

		switch (opcion) {
		case 1:
			System.out.println("Inserte el Nuevo Código del Medicamento");
			String nuevoCodigo = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nuevoCodigo);
			medicamentoAModificar.setCodigo(nuevoCodigo);
			break;
		case 2:
			System.out.println("Inserte el Nuevo Nombre del Medicamento");
			String nuevoNombre = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nuevoNombre);
			medicamentoAModificar.setNombre(nuevoNombre);
			break;
		case 3:
			System.out.println("Inserte la Nueva Descripción del Medicamento");
			String nuevaDescripcion = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nuevaDescripcion);
			medicamentoAModificar.setDescripcion(nuevaDescripcion);
			break;
		case 4:
			System.out.println("Inserte el Nuevo Precio del Medicamento");
			float nuevoPrecio;
			while (!entrada.hasNextFloat()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.nextInt();
			}
			nuevoPrecio = entrada.nextFloat();
			medicamentoAModificar.setPrecio(nuevoPrecio);
			break;
		case 5:
			System.out.println("Inserte las Nuevas Unidades del Medicamento");
			int nuevasUnidades;

			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.nextInt();
			}
			nuevasUnidades = entrada.nextInt();

			medicamentoAModificar.setUnidades(nuevasUnidades);
			break;
		case 6:
			System.out.println("Inserte la Nueva Forma de Tomar el Medicamento");
			String nuevaFormaTomar = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nuevaFormaTomar);
			medicamentoAModificar.setComoTomar(nuevaFormaTomar);
			break;
		case 7:
			System.out.println("Inserte los Nuevos Efectos Adversos del Medicamento");
			String nuevosEfectos = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nuevosEfectos);
			medicamentoAModificar.setEfectosAdversos(nuevosEfectos);
			break;
		case 8:
			System.out.println("Seleccione el Nuevo Tipo de Medicamento:");
			System.out.println("1.  ANALGESICOS");
			System.out.println("2.  LAXANTES");
			System.out.println("3.  ANTIINFECCIOSOS");
			System.out.println("4.  ANTIDEPRESIVOS");
			System.out.println("5.  ANTITUSIVOS");
			System.out.println("6.  MUCOLITICOS");
			System.out.println("7.  ANTIACIDOS");
			System.out.println("8.  ANTIULCEROSOS");
			System.out.println("9.  ANTIALERGICOS");
			System.out.println("10. ANTIFIARREICOS");
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			int nuevoTipo = entrada.nextInt();
			TipoMedicamento tipoMedicamento = null;
			switch (nuevoTipo) {
			case 1:
				tipoMedicamento = TipoMedicamento.ANALGESICOS;
				break;
			case 2:
				tipoMedicamento = TipoMedicamento.LAXANTES;
				break;
			case 3:
				tipoMedicamento = TipoMedicamento.ANTIINFECCIOSOS;
				break;
			case 4:
				tipoMedicamento = TipoMedicamento.ANTIDEPRESIVOS;
				break;
			case 5:
				tipoMedicamento = TipoMedicamento.ANTITUSIVOS;
				break;
			case 6:
				tipoMedicamento = TipoMedicamento.MUCOLITICOS;
				break;
			case 7:
				tipoMedicamento = TipoMedicamento.ANTIACIDOS;
				break;
			case 8:
				tipoMedicamento = TipoMedicamento.ANTIULCEROSOS;
				break;
			case 9:
				tipoMedicamento = TipoMedicamento.ANTIALERGICOS;
				break;
			case 10:
				tipoMedicamento = TipoMedicamento.ANTIFIARREICOS;
				break;
			default:
				System.out.println("Tipo de medicamento no válido. No se realizaron cambios.");
			}
			if (tipoMedicamento != null) {
				medicamentoAModificar.setTipoMedicamento(tipoMedicamento);
			}
			break;
		default:
			System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
		}
		System.out.println("Medicamento modificado exitosamente.");
		gestionarMedicamento();

	}
}
