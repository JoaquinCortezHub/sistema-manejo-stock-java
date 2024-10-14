import java.util.ArrayList;

public class Inventario {
    private final ArrayList<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public double getValorTotal() {
        return calcularValorTotal(0, productos.size() - 1);
    }

    private double calcularValorTotal(int inicio, int fin) {
        if (inicio > fin) {
            return 0;
        }
        if (inicio == fin) { // aca, se calcula el valor de un solo producto.
            Producto p = productos.get(inicio);
            return p.getValor() * p.getCantidad();
        }
        int medio = (inicio + fin) / 2;
        return calcularValorTotal(inicio, medio) + calcularValorTotal(medio + 1, fin); //divide el arreglo en dos partes y suma los valores de cada parte.
    }

    public int getTotalStock() {
        return calcularTotalStock(0, productos.size() - 1);
    }

    private int calcularTotalStock(int inicio, int fin) {
        if (inicio > fin) {
            return 0;
        }
        if (inicio == fin) {
            return productos.get(inicio).getCantidad();
        }
        int medio = (inicio + fin) / 2;
        return calcularTotalStock(inicio, medio) + calcularTotalStock(medio + 1, fin);
    }

    public void actualizarStock(Producto producto, int cantidadVendida) {
        productos.stream().filter(p -> p.getNumArticulo() == producto.getNumArticulo())
                .findFirst().ifPresent(p -> {
                    int nuevoStock = p.getCantidad() - cantidadVendida;
                    productos.set(productos.indexOf(p), new Producto(p.getNumArticulo(), p.getNombre(), p.getValor(), nuevoStock));
                });
    }
}