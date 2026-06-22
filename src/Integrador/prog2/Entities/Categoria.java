package Integrador.prog2.Entities;

import java.util.ArrayList;

public class Categoria extends Base {

    private String nombre;
    private String descripcion;
    private ArrayList<Producto> productos;

    public Categoria(String nombre, String descripcion, Long id) {
        super(id);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }

    // Getters y Setters
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

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto p) {
        if (p != null && !p.isEliminado()) {
            this.productos.add(p);
        }
    }

    @Override
    public String toString() {
        return String.format("Categoría #%d: %s | %s | Cantidad de productos: %d",
                getId(), nombre, descripcion, productos.size());
    }
}
