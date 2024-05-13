package Farmacia;

import java.util.*;

public class ParaFarmacia extends Producto {
	private String tipo="PARAFARMACIA.";
	private Categoria categoria;
	private Integer dosisUnidades;
	private float descuento;
	private static Scanner entrada = new Scanner(System.in);

	// Constructor vacio
	public ParaFarmacia() {
		super();
	}

	// Constructor con parametros
	public ParaFarmacia(String tipo,String codigo, String nombre, String descripcion, float precio, Integer unidades,
			Categoria categoria, Integer dosisUnidades, float descuento) {
		super(codigo, nombre, descripcion, precio, unidades);
		this.tipo=tipo;
		this.categoria = categoria;
		this.dosisUnidades = dosisUnidades;
		this.descuento = descuento;
	}

	// Constructor copia
	public ParaFarmacia(ParaFarmacia copiaProducto) {
		super(copiaProducto);
		this.tipo=copiaProducto.tipo;
		this.categoria = copiaProducto.categoria;
		this.dosisUnidades = copiaProducto.dosisUnidades;
		this.descuento = copiaProducto.descuento;

	}

	// Getters y Setters
	public String getTipo() {
		return tipo;
	}
	public Integer getDosisUnidades() {
		return dosisUnidades;
	}

	public void setDosisUnidades(Integer dosisUnidades) {
		this.dosisUnidades = dosisUnidades;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		String estado = getBaja() ? "baja" : "alta";
		return "PARAFARMACIA.: " + super.toString() + "\nDosis de Unidades: " + dosisUnidades + "\nDescuento: "
				+ descuento + "\nEstado.: " + estado+"\n";
	}
	//Menu principal de Parafarmacia
	public static void gestionarParaFarmacia() {
		int opcion;
		do {
			System.out.println("\n--- GESTIONAR PRODUCTOS DE PARAFARMACIA ---");
			System.out.println("1. Dar de alta un Producto de Parafarmacia ");			
			System.out.println("2. Modificar datos del Producto de Parafarmacia");			
			System.out.println("3. Volver al menú principal de Productos");
			System.out.print("Selecciona una opción: ");
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			opcion = entrada.nextInt();
			entrada.nextLine();

			switch (opcion) {
			case 1:
				altaParaFarmacia();
				break;			
			case 2:
				modificarParaFarmacia();
				break;			
			case 3:
				System.out.println("Volviendo al menú del principal de Productos...");
				menuSeleccionProducto();
				entrada.nextLine();
				break;
			default:
				System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
				
			}
		} while (opcion != 3);

	}
	//Metodo para dar de alta
	public static void altaParaFarmacia() {
		System.out.println("\n--- MENU PRINCIPAL DE PRODUCTO DE PARAFARMACIA ---");
		System.out.println("");
		System.out.println("Inserte el Codigo del Nuevo Producto de Parafarmacia");
		String codigo = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(codigo);
		String tipo="PARAFARMACIA.";
		boolean productoExistente = Producto.comprobarSiExisteProducto(codigo);
		if (productoExistente) {
			System.out.println("El producto de Parafarmacia con el CODIGO " + codigo + " ya existe.");
			System.out.println("Volvemos al menu principal");
			gestionarParaFarmacia();

		} else {
			System.out.println("Inserte el Nombre del Nuevo Producto de Parafarmacia");
			String nombre = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nombre);
			System.out.println("Inserte la Descripcion del Nuevo Producto de Parafarmacia");
			String descripcion = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(descripcion);
			System.out.println("Inserte el Precio del Nuevo Producto de Parafarmacia");
			float precio;
			while (!entrada.hasNextFloat()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			precio = entrada.nextFloat();
			System.out.println("Inserte la Cantidad del Nuevo Producto de Parafarmacia");
			int cantidad;
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			cantidad = entrada.nextInt();
			System.out.println("Inserte la Categoria del Producto de Parafarmacia");
			System.out.println("Seleccione la Categoría del Producto de Parafarmacia:");
			System.out.println("1.  DENTAL");
			System.out.println("2.  FACIAL");
			System.out.println("3.  GELES");
			System.out.println("4.  CORPORAL");
			System.out.println("5.  CABELLO");
			System.out.println("6.  ANTIMOSQUITOS");
			System.out.println("7.  INTIMA");
			System.out.println("8.  NASAL");
			System.out.println("9.  OCULAR");
			System.out.println("10. BOTIQUIN");
			System.out.println("11. OIDOS");
			System.out.println("12. TOALLITAS");
			System.out.println("13. LIMPIEZA");
			System.out.println("14. HOGAR");
			System.out.println("15. MASCARILLAS");
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			int seleccionCategoria = entrada.nextInt();
			Categoria categoria;
			switch (seleccionCategoria) {
			case 1:
				categoria = Categoria.DENTAL;
				break;
			case 2:
				categoria = Categoria.FACIAL;
				break;
			case 3:
				categoria = Categoria.GELES;
				break;
			case 4:
				categoria = Categoria.CORPORAL;
				break;
			case 5:
				categoria = Categoria.CABELLO;
				break;
			case 6:
				categoria = Categoria.ANTIMOSQUITOS;
				break;
			case 7:
				categoria = Categoria.INTIMA;
				break;
			case 8:
				categoria = Categoria.NASAL;
				break;
			case 9:
				categoria = Categoria.OCULAR;
				break;
			case 10:
				categoria = Categoria.BOTIQUIN;
				break;
			case 11:
				categoria = Categoria.OIDOS;
				break;
			case 12:
				categoria = Categoria.TOALLITAS;
				break;
			case 13:
				categoria = Categoria.LIMPIEZA;
				break;
			case 14:
				categoria = Categoria.HOGAR;
				break;
			case 15:
				categoria = Categoria.MASCARILLAS;
				break;
			default:
				System.out.println("Opción inválida. Seleccionando tipo por defecto (DENTAL).");
				categoria = Categoria.DENTAL;
				
				break;
			}

			System.out.println("Inserte las Dosis del Producto de Parafarmacia");
			int dosisUnidades;
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			dosisUnidades = entrada.nextInt();
			System.out.println("Inserte el Descuento del Producto de Parafarmacia");
			float descuento;
			while (!entrada.hasNextFloat()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			descuento = entrada.nextFloat();

			Producto paraF = new ParaFarmacia(tipo,codigo, nombre, descripcion, precio, cantidad, categoria, dosisUnidades,
					descuento);
			Principal.productos.add(paraF);
			// ArchivoTexto.escribirEnArchivoProductos(paraF);
			System.out.println("Los Datos del Nuevo Producto de Parafarmacia son.:");
			System.out.println(paraF);
		}
	}
	//Metodo para modificar
	public static void modificarParaFarmacia() {
		System.out.println("\n--- ,MENU PRINCIPAL PARA MODIFICAR UN PRODUCTO DE PARAFARMACIA ---");
		System.out.println("Inserte el Codigo del Producto de Parafarmacia que Desea Modificar");
		String codigo = entrada.nextLine().toUpperCase();
		ValidarDatos.stringNoVacio(codigo);
		ParaFarmacia paraFarmaciaAModificar = null;
		for (Producto pf : Principal.productos) {
			if (pf instanceof ParaFarmacia && pf.getCodigo().equals(codigo)) {
				paraFarmaciaAModificar = (ParaFarmacia) pf;

			}
		}
		if (paraFarmaciaAModificar == null) {
			System.out.println("No se encontró ningún Producto de Parafarmacia con ese Codigo.");
			gestionarParaFarmacia();
		}

		System.out.println("Indique qué desea modificar:");
		System.out.println("1. Código");
		System.out.println("2. Nombre");
		System.out.println("3. Descripción");
		System.out.println("4. Precio");
		System.out.println("5. Unidades");
		System.out.println("6. Categoría");
		System.out.println("7. Dosis unidades");
		System.out.println("8. Descuento");
		while (!entrada.hasNextInt()) {
			System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
			entrada.next();
		}
		int opcion = entrada.nextInt();
		entrada.nextLine();
		switch (opcion) {
		case 1:
			System.out.println("Inserte el nuevo código del Producto de Parafarmacia");
			String codigoNuevo = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(codigoNuevo);
			paraFarmaciaAModificar.setCodigo(codigoNuevo);
			break;
		case 2:
			System.out.println("Inserte el nuevo nombre del Producto de Parafarmacia");
			String nombreNuevo = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(nombreNuevo);
			paraFarmaciaAModificar.setNombre(nombreNuevo);
			break;
		case 3:
			System.out.println("Inserte la nueva descripción del Producto de Parafarmacia");
			String descripcionNueva = entrada.nextLine().toUpperCase();
			ValidarDatos.stringNoVacio(descripcionNueva);
			paraFarmaciaAModificar.setDescripcion(descripcionNueva);
			break;
		case 4:
			System.out.println("Inserte el nuevo precio del producto de Parafarmacia");
			float precioNuevo;
			while (!entrada.hasNextFloat()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			precioNuevo = entrada.nextFloat();
			paraFarmaciaAModificar.setPrecio(precioNuevo);
			break;
		case 5:
			System.out.println("Inserte la nueva cantidad del Producto de Parafarmacia");
			int unidadesNueva;
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			unidadesNueva = entrada.nextInt();
			paraFarmaciaAModificar.setUnidades(unidadesNueva);
			break;
		case 6:
			System.out.println("Seleccione la nueva categoría del Producto de Parafarmacia:");
			System.out.println("Inserte la Categoria del Producto de Parafarmacia");
			System.out.println("Seleccione la Categoría del Producto de Parafarmacia:");
			System.out.println("1.  DENTAL");
			System.out.println("2.  FACIAL");
			System.out.println("3.  GELES");
			System.out.println("4.  CORPORAL");
			System.out.println("5.  CABELLO");
			System.out.println("6.  ANTIMOSQUITOS");
			System.out.println("7.  INTIMA");
			System.out.println("8.  NASAL");
			System.out.println("9.  OCULAR");
			System.out.println("10. BOTIQUIN");
			System.out.println("11. OIDOS");
			System.out.println("12. TOALLITAS");
			System.out.println("13. LIMPIEZA");
			System.out.println("14. HOGAR");
			System.out.println("15. MASCARILLAS");
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			int seleccionCategoria = entrada.nextInt();
			Categoria categoria;
			switch (seleccionCategoria) {
			case 1:
				categoria = Categoria.DENTAL;
				break;
			case 2:
				categoria = Categoria.FACIAL;
				break;
			case 3:
				categoria = Categoria.GELES;
				break;
			case 4:
				categoria = Categoria.CORPORAL;
				break;
			case 5:
				categoria = Categoria.CABELLO;
				break;
			case 6:
				categoria = Categoria.ANTIMOSQUITOS;
				break;
			case 7:
				categoria = Categoria.INTIMA;
				break;
			case 8:
				categoria = Categoria.NASAL;
				break;
			case 9:
				categoria = Categoria.OCULAR;
				break;
			case 10:
				categoria = Categoria.BOTIQUIN;
				break;
			case 11:
				categoria = Categoria.OIDOS;
				break;
			case 12:
				categoria = Categoria.TOALLITAS;
				break;
			case 13:
				categoria = Categoria.LIMPIEZA;
				break;
			case 14:
				categoria = Categoria.HOGAR;
				break;
			case 15:
				categoria = Categoria.MASCARILLAS;
				break;
			default:
				System.out.println("Opción inválida. Seleccionando categoría por defecto (DENTAL).");
				categoria = Categoria.DENTAL;

				if (categoria != null) {
					paraFarmaciaAModificar.setCategoria(categoria);
				}
				break;
			}
		case 7:
			System.out.println("Inserte la nueva dosis de unidades del Producto de Parafarmacia");
			int dosisUnidadesNueva;
			while (!entrada.hasNextInt()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			dosisUnidadesNueva = entrada.nextInt();
			paraFarmaciaAModificar.setDosisUnidades(dosisUnidadesNueva);
			break;
		case 8:
			System.out.println("Inserte el nuevo descuento del Producto de Parafarmacia");
			float descuentoNuevo;
			while (!entrada.hasNextFloat()) {
				System.out.println("Valor incorrecto, por favor ingrese un valor válido:");
				entrada.next();
			}
			descuentoNuevo = entrada.nextFloat();
			paraFarmaciaAModificar.setDescuento(descuentoNuevo);
			break;
		default:
			System.out.println("Opción no válida.");
			modificarParaFarmacia();
			return;
		}
		System.out.println("Producto de Parafarmacia modificado exitosamente.");		
		gestionarParaFarmacia();
	}

}
