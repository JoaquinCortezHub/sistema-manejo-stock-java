import java.util.ArrayList;

public class Cliente {
    private final String nombre;
    private final String id;
    private final ArrayList<Venta> compras; //Relación de composición con la clase Venta.

    public Cliente(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.compras = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public void agregarCompra(Venta venta) {
        compras.add(venta);
    }

    public ArrayList<Venta> getCompras() {
        return compras;
    }
}
