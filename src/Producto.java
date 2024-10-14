public class Producto {
    private final int numArticulo;
    private final String nombre;
    private final double valor;
    private final int cantidad;

    public Producto(int numArticulo, String nombre, double valor, int cantidad) {
        this.numArticulo = numArticulo;
        this.nombre = nombre;
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public int getNumArticulo() {
        return numArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valor;
    }

    public int getCantidad() {
        return cantidad;
    }
}
