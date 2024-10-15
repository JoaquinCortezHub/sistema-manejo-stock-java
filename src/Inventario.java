import java.util.ArrayList;

public class Inventario {
    private final ArrayList<Producto> productos; // -→ Relación de composición con la clase Producto.

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public double getValorTotal() { // -→ Método para calcular el valor total del inventario.
        return calcularValorTotal(0, productos.size() - 1);
    }

    private double calcularValorTotal(int inicio, int fin) { // -→ Método recursivo para calcular el valor total del inventario.
        if (inicio > fin) {
            return 0; //Caso base: si el inicio es mayor que el fin, se devuelve 0.
        }
        if (inicio == fin) { // Solo hay un elemento en el arreglo, se devuelve el valor del producto en esa posición.
            Producto p = productos.get(inicio); //Creo un objeto Producto p y le asigno el valor de la posición inicio del arreglo productos.
            return p.getValor() * p.getCantidad();
        }
        int medio = (inicio + fin) / 2;
        // -→ Se divide el arreglo en dos mitades y se calcula el valor total de cada mitad utilizando recursión binaria, luego se suman los valores.
        return calcularValorTotal(inicio, medio) + calcularValorTotal(medio + 1, fin);
    }

    public int getTotalStock() {
        return calcularTotalStock(0, productos.size() - 1);
    }

    //Funciona de la misma manera que el método calcularValorTotal, pero en este caso se calcula el total de stock.
    private int calcularTotalStock(int inicio, int fin) {
        if (inicio > fin) {
            return 0;
        }
        if (inicio == fin) {
            return productos.get(inicio).getCantidad(); //Devuelve el valor de la posición inicio del arreglo productos.
        }
        int medio = (inicio + fin) / 2;
        return calcularTotalStock(inicio, medio) + calcularTotalStock(medio + 1, fin);
    }

    public void actualizarStock(Producto producto, int cantidadVendida) {
        //El método stream nos da acceso a todos los elementos del arreglo productos y nos permite filtrarlos haciendo la búsqueda de un elemento más fácil.
        productos.stream().filter(p -> p.getNumArticulo() == producto.getNumArticulo())
                .findFirst().ifPresent(p -> {
                    int nuevoStock = p.getCantidad() - cantidadVendida;
                    productos.set(productos.indexOf(p), new Producto(p.getNumArticulo(), p.getNombre(), p.getValor(), nuevoStock));
                });
    }
}