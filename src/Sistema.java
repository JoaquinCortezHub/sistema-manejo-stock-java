import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
	private final Inventario inventario;
	private final ArrayList<Cliente> clientes;
	private double ventasDiarias;

	public Sistema() {
		this.inventario = new Inventario();
		this.clientes = new ArrayList<>();
		this.ventasDiarias = 0;
	}

	public void iniciar() {
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;

		while (!salir) {
			System.out.println("Bienvenido a la tienda de productos");
			System.out.println("-----------------------------------------");
			System.out.println("1. Agregar producto");
			System.out.println("2. Mostrar productos disponibles");
			System.out.println("3. Registrar venta");
			System.out.println("4. Mostrar clientes y sus compras");
			System.out.println("5. Salir y mostrar resumen");
			try {
				int opcion = Integer.parseInt(scanner.nextLine());

				switch (opcion) {
					case 1:
						agregarProducto(scanner);
						break;
					case 2:
						mostrarProductos();
						break;
					case 3:
						registrarVenta(scanner);
						break;
					case 4:
						mostrarClientesYCompras();
						break;
					case 5:
						salir = true;
						mostrarResumen();
						break;
					default:
						System.out.println("Opción no válida. Intente de nuevo.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Por favor, ingrese un número válido.");
			}
		}

		scanner.close();
	}

	private void agregarProducto(Scanner scanner) {
		try {
			System.out.print("Ingrese el número de artículo: ");
			int numArticulo = Integer.parseInt(scanner.nextLine());
			System.out.print("Ingrese el nombre del producto: ");
			String nombre = scanner.nextLine();
			System.out.print("Ingrese el valor del producto: ");
			double valor = Double.parseDouble(scanner.nextLine());
			System.out.print("Ingrese la cantidad en stock: ");
			int cantidad = Integer.parseInt(scanner.nextLine());

			Producto producto = new Producto(numArticulo, nombre, valor, cantidad);
			inventario.agregarProducto(producto);
			System.out.println("Producto agregado exitosamente.");
		} catch (NumberFormatException e) {
			System.out.println("Error: Ingrese valores numéricos válidos.");
		}
	}

	private void mostrarProductos() {
		System.out.println("\nProductos disponibles:");
		for (Producto producto : inventario.getProductos()) {
			System.out.printf("ID: %d, Nombre: %s, Precio: %.2f, Stock: %d%n", producto.getNumArticulo(), producto.getNombre(), producto.getValor(), producto.getCantidad());
		}
		System.out.printf("Valor total del inventario: %.2f%n", inventario.getValorTotal()); //Formatea los valores de la lista.
		int totalStock = inventario.getTotalStock();
		System.out.printf("Cantidad total de productos: %d%n", totalStock);
	}

	private void registrarVenta(Scanner scanner) {
		try {
			System.out.print("Ingrese el nombre del cliente: ");
			String nombreCliente = scanner.nextLine();
			System.out.print("Ingrese el ID del cliente: ");
			String idCliente = scanner.nextLine();

			Cliente cliente = new Cliente(nombreCliente, idCliente);

			System.out.print("Ingrese el número de artículo a vender: ");
			int numArticulo = Integer.parseInt(scanner.nextLine());
			System.out.print("Ingrese la cantidad a vender: ");
			int cantidad = Integer.parseInt(scanner.nextLine());

			Producto productoVendido = inventario.getProductos().stream().filter(p -> p.getNumArticulo() == numArticulo).findFirst().orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

			if (productoVendido.getCantidad() < cantidad) {
				throw new IllegalArgumentException("Stock insuficiente");
			}

			Venta venta = new Venta(productoVendido, cantidad);
			cliente.agregarCompra(venta);
			clientes.add(cliente);

			inventario.actualizarStock(productoVendido, cantidad);
			ventasDiarias += venta.getPrecioTotal();

			System.out.println("Venta registrada exitosamente.");
		} catch (NumberFormatException e) {
			System.out.println("Error: Ingrese valores numéricos válidos.");
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void mostrarClientesYCompras() {
		System.out.println("\nClientes y sus compras:");
		for (Cliente cliente : clientes) {
			System.out.printf("Cliente: %s (ID: %s)%n", cliente.getNombre(), cliente.getId());
			for (Venta venta : cliente.getCompras()) {
				System.out.printf("  - Producto: %s, Cantidad: %d, Total: %.2f%n", venta.getProducto().getNombre(), venta.getCantidad(), venta.getPrecioTotal());
			}
		}
	}

	private void mostrarResumen() {
		System.out.println("\nResumen del día:");
		System.out.printf("Total de ventas: %.2f%n", ventasDiarias);
		System.out.printf("Valor actual del inventario: %.2f%n", inventario.getValorTotal());
		int totalStock = inventario.getTotalStock();
		System.out.println("Productos en stock:" + totalStock);
//        mostrarProductos();
	}

	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		sistema.iniciar();
	}
}


