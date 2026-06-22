package Integrador.prog2.Services;

import java.util.ArrayList;
import java.util.List;
import Integrador.prog2.Entities.Producto;
import Integrador.prog2.Entities.Categoria;
import Integrador.prog2.Exception.EntidadNoEncontradaException;
import Integrador.prog2.Exception.PrecioInvalidoException;
import Integrador.prog2.Exception.StockInvalidoException;

public class ProductoService {

    private List<Producto> productos = new ArrayList<>();
    private Long ultimoId = 0L;

    public Producto create(String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria) {
        if (precio < 0) {
            throw new PrecioInvalidoException("❌  El precio no puede ser negativo.");
        }
        if (stock < 0) {
            throw new StockInvalidoException("❌ El stock no puede ser negativo.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new RuntimeException("❌ El nombre no puede estar vacío.");
        }
        if (categoria == null || categoria.isEliminado()) {
            throw new EntidadNoEncontradaException("⚠️ La categoría no existe o está eliminada.");
        }
        if (imagen == null || imagen.isBlank()) {
            throw new RuntimeException("❌ La imagen no puede estar vacía.");
        }

        ultimoId++;
        Producto p = new Producto(nombre, precio, descripcion, stock, imagen, disponible, categoria, ultimoId);
        productos.add(p);
        categoria.agregarProducto(p);
        return p;
    }

    public List<Producto> listar() {
        return productos.stream().filter(p -> !p.isEliminado()).toList();
    }

    public Producto findById(Long id) {
        return productos.stream().filter(p -> p.getId().equals(id) && !p.isEliminado()).findFirst().orElse(null);
    }

    public void update(Long id, String nombre, String descripcion, Double precio, Integer stock, Categoria categoria) {

        Producto p = findById(id);

        if (p == null || p.isEliminado()) {
            throw new EntidadNoEncontradaException("⚠️ Producto no encontrado o eliminado");
        }

        if (nombre != null && !nombre.isBlank()) {
            p.setNombre(nombre);
        }

        if (descripcion != null && !descripcion.isBlank()) {
            p.setDescripcion(descripcion);
        }

        if (precio != null) {
            p.setPrecio(precio);
        }

        if (stock != null) {
            p.setStock(stock);
        }

        if (categoria != null) {
            p.setCategoria(categoria);
        }
    }

    public void delete(Long id) {
        Producto p = findById(id);

        if (p == null || p.isEliminado()) {
            throw new EntidadNoEncontradaException("⚠️ Producto no encontrado o ya eliminado");
        }

        p.setEliminado(true);
    }

}
