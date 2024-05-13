package Farmacia;

import java.io.*;
import java.util.*;

public class ArchivoTexto {
	// Crea los Archivos Clientes.txt y Productos.txt
	public static void crearArchivoDeTexto() {
		File archivoClientes = new File("Clientes.txt");
		File archivoProductos = new File("Productos.txt");

		try {
			if (!archivoClientes.exists() || !archivoProductos.exists()) {
				if (archivoClientes.createNewFile() && archivoProductos.createNewFile()) {
					System.out.println("Los archivos se han creado con éxito.");
				} else {
					System.out.println("Error en la creación de los archivos.");					
				}
			} else {
				System.out.println("Leyendo contenido...");
				lecturaDeArchivoDeTexto("Clientes.txt", "Productos.txt");				
			}
		} catch (IOException exception) {			
			System.out.println("Error de lectura.");
		}
	}

	// Lee el contenido de los archivos txt
	public static void lecturaDeArchivoDeTexto(String archivoClientes, String archivoProductos) {
		try (BufferedReader lecturaClientes = new BufferedReader(new FileReader(archivoClientes));
				BufferedReader lecturaProductos = new BufferedReader(new FileReader(archivoProductos))) {

			String lineaCliente;
			String lineaProducto;
			System.out.println("Contenido del archivo de clientes:");
			while ((lineaCliente = lecturaClientes.readLine()) != null) {
				System.out.println(lineaCliente);
			}
			System.out.println("\nContenido del archivo de productos:");
			while ((lineaProducto = lecturaProductos.readLine()) != null) {
				System.out.println(lineaProducto);
			}

		} catch (IOException exception) {			
			System.out.println("Error de lectura.");
		}
	}

	// Lee los datos del archivo txt de Clientes y los carga en el ArraList Clientes
	public static ArrayList<Cliente> cargarClientesDesdeArchivo(String nombreArchivoClientes) {
		ArrayList<Cliente> clientes = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivoClientes))) {
			String lineaCliente;
			while ((lineaCliente = br.readLine()) != null) {
				String[] datosCliente = lineaCliente.split(",");
				if (datosCliente.length == 5) {
					String id = datosCliente[0];
					String nombre = datosCliente[1];
					String direccion = datosCliente[2];
					String telefono = datosCliente[3];
					String estado = datosCliente[4];
					Cliente cliente = new Cliente(id, nombre, direccion, telefono);
					cliente.setBaja(estado.equals("baja"));
					clientes.add(cliente);
				} else {
					System.out.println("Error al leer una línea: " + lineaCliente);
				}
			}
		} catch (IOException e) {
			System.out.println("El archivo de Clientes no contiene Datos");
		}

		return clientes;
	}

	// Lee los Datos del archivos txt de Productos y los carga en el ArrayList
	// productos
	public static ArrayList<Producto> cargarProductosDesdeArchivo(String nombreArchivoProductos) {
		ArrayList<Producto> productos = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivoProductos))) {
			String lineaProducto;
			while ((lineaProducto = br.readLine()) != null) {
				String[] datosProducto = lineaProducto.split(",");
				if (datosProducto.length >= 10 && datosProducto[0].equals("MEDICAMENTO.")) {
					String tipo = datosProducto[0];
					String codigo = datosProducto[1];
					String nombre = datosProducto[2];
					String descripcion = datosProducto[3];
					float precio = Float.parseFloat(datosProducto[4]);
					int unidades = Integer.parseInt(datosProducto[5]);
					TipoMedicamento tipoMedicamento = TipoMedicamento.valueOf(datosProducto[6]);
					String comoTomar = datosProducto[7];
					String efectosAdversos = datosProducto[8];
					String estado = datosProducto[9];
					Producto medicamento = new Medicamento(tipo, codigo, nombre, descripcion, precio, unidades,
							tipoMedicamento, comoTomar, efectosAdversos);
					medicamento.setBaja(estado.equals("baja"));
					productos.add(medicamento);
				} else if (datosProducto.length >= 10 && datosProducto[0].equals("PARAFARMACIA.")) {
					String tipo = datosProducto[0];
					String codigo = datosProducto[1];
					String nombre = datosProducto[2];
					String descripcion = datosProducto[3];
					float precio = Float.parseFloat(datosProducto[4]);
					int unidades = Integer.parseInt(datosProducto[5]);
					Categoria categoria = Categoria.valueOf(datosProducto[6]);
					int dosisUnidades = Integer.parseInt(datosProducto[7]);
					float descuento = Float.parseFloat(datosProducto[8]);
					String estado = datosProducto[9];
					Producto paraFarmacia = new ParaFarmacia(tipo, codigo, nombre, descripcion, precio, unidades,
							categoria, dosisUnidades, descuento);
					paraFarmacia.setBaja(estado.equals("baja"));
					productos.add(paraFarmacia);

				} else {
					System.out.println("Error al leer una línea: " + lineaProducto);
				}
			}
		} catch (IOException e) {
			System.out.println("El archivo de Productos no contiene Datos");
		}

		return productos;
	}

	// Escribe los Datos en el archivo txt de Clientes recogidos del ArrayList de
	// clientes
	public static void escribirEnArchivoClientes(ArrayList<Cliente> clientes) {
		try (FileWriter escrituraClientes = new FileWriter("Clientes.txt")) {
			for (Cliente cliente : clientes) {
				String estado = cliente.getBaja() ? "baja" : "alta";
				escrituraClientes.write(cliente.getId() + "," + cliente.getNombre() + "," + cliente.getDireccion() + ","
						+ cliente.getTelefono() + "," + estado + "\n");
			}
			System.out.println("Datos de clientes escritos en el archivo de clientes.");
		} catch (IOException exception) {			
			System.out.println("Datos de clientes no han sido escritos en el archivo de clientes.");
		}
	}

	// Escribe los Datos en el archivo txt de Productos recogidos del ArrayList
	// productos
	public static void escribirEnArchivoProductos(ArrayList<Producto> productos) {
		try (FileWriter escrituraProductos = new FileWriter("Productos.txt")) {
			for (Producto producto : productos) {
				String estado = producto.getBaja() ? "baja" : "alta";
				if (producto instanceof Medicamento) {
					Medicamento medicamento = (Medicamento) producto;
					escrituraProductos.write(medicamento.getTipo() + "," + medicamento.getCodigo() + ","
							+ medicamento.getNombre() + "," + medicamento.getDescripcion() + ","
							+ medicamento.getPrecio() + "," + medicamento.getUnidades() + ","
							+ medicamento.getTipoMedicamento() + "," + medicamento.getComoTomar() + ","
							+ medicamento.getEfectosAdversos() + "," + estado + "\n");
				} else if (producto instanceof ParaFarmacia) {
					ParaFarmacia paraFarmacia = (ParaFarmacia) producto;
					escrituraProductos.write(paraFarmacia.getTipo() + "," + paraFarmacia.getCodigo() + ","
							+ paraFarmacia.getNombre() + "," + paraFarmacia.getDescripcion() + ","
							+ paraFarmacia.getPrecio() + "," + paraFarmacia.getUnidades() + ","
							+ paraFarmacia.getCategoria() + "," + paraFarmacia.getDosisUnidades() + ","
							+ paraFarmacia.getDescuento() + "," + estado + "\n");
				}
			}
			System.out.println("Datos de productos escritos en el archivo de productos.");
		} catch (IOException exception) {			
			System.out.println("Datos de productos no han sido escritos en el archivo de productos.");

		}
	}
}
