package Integrador.prog2.Services;

import java.util.ArrayList;
import java.util.List;
import Integrador.prog2.Entities.Categoria;
import Integrador.prog2.Exception.EntidadNoEncontradaException;

public class CategoriaService {

    private List<Categoria> categorias = new ArrayList<>();
    private Long ultimoId = 0L;

    public Categoria create(String nombre, String descripcion) {
        if (nombre == null || nombre.isBlank()) {
            throw new RuntimeException("❌ El nombre no puede estar vacío.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new RuntimeException("❌ La descripción no puede estar vacía.");
        }
        if (categorias.stream().anyMatch(c -> c.getNombre().equalsIgnoreCase(nombre) && !c.isEliminado())) {
            throw new RuntimeException("⚠️ Ya existe una categoría con ese nombre");
        }

        ultimoId++;
        Categoria c = new Categoria(nombre, descripcion, ultimoId);
        categorias.add(c);
        return c;
    }

    public List<Categoria> listar() {
        return categorias.stream().filter(c -> !c.isEliminado()).toList();
    }

    public Categoria findById(Long id) {
        return categorias.stream().filter(c -> c.getId().equals(id) && !c.isEliminado()).findFirst().orElse(null);
    }

    public void update(Long id, String nombre, String descripcion) {
        Categoria c = findById(id);
        if (c == null || c.isEliminado()) {
            throw new EntidadNoEncontradaException("⚠️ Categoría no encontrada o eliminada");
        }
        if (nombre != null && !nombre.isBlank()) {
            if (categorias.stream().anyMatch(cat -> !cat.getId().equals(id) && cat.getNombre().equalsIgnoreCase(nombre))) {
                throw new RuntimeException("⚠️ Ya existe una categoría con ese nombre");
            }
            c.setNombre(nombre);
        }
        if (descripcion != null && !descripcion.isBlank()) {
            c.setDescripcion(descripcion);
        }
    }

    public void delete(Long id) {
        Categoria c = findById(id);
        if (c == null) {
            throw new EntidadNoEncontradaException("⚠️ Categoría no encontrada");
        }
        c.setEliminado(true);
    }
}
