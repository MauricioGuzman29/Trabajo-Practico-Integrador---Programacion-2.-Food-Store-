package Integrador.prog2.Services;

import java.util.ArrayList;
import java.util.List;
import Integrador.prog2.Entities.Pedido;
import Integrador.prog2.Entities.Usuario;
import Integrador.prog2.Entities.Producto;
import Integrador.prog2.Enums.Estado;
import Integrador.prog2.Enums.FormaPago;
import Integrador.prog2.Exception.EntidadNoEncontradaException;
import Integrador.prog2.Exception.StockInvalidoException;

public class PedidoService {

    private List<Pedido> pedidos = new ArrayList<>();
    private Long ultimoId = 0L;

    public Pedido create(Usuario usuario, Estado estado, FormaPago formaPago) {
        ultimoId++;
        Pedido p = new Pedido(java.time.LocalDate.now(), estado, formaPago, usuario, ultimoId);
        pedidos.add(p);
        usuario.agregarPedido(p);
        return p;
    }

    public List<Pedido> listar() {
        return pedidos.stream().filter(p -> !p.isEliminado()).toList();
    }

    public Pedido findById(Long id) {
        return pedidos.stream().filter(p -> p.getId().equals(id) && !p.isEliminado()).findFirst().orElse(null);
    }

    public void update(Long id, Estado estado, FormaPago formaPago) {
        Pedido p = findById(id);
        if (p == null || p.isEliminado()) {
            throw new EntidadNoEncontradaException("⚠️ Pedido no encontrado o eliminado ");
        }
        if (estado != null) {
            p.setEstado(estado);
        }
        if (formaPago != null) {
            p.setFormaPago(formaPago);
        }
    }

    public void delete(Long id) {
        Pedido p = findById(id);
        if (p == null) {
            throw new EntidadNoEncontradaException("⚠️ Pedido no encontrado");
        }
        p.setEliminado(true);
    }

    public void addDetalle(Long idPedido, int cantidad, Producto producto) {
        Pedido p = findById(idPedido);
        if (p == null) {
            throw new EntidadNoEncontradaException("⚠️ Pedido no encontrado");
        }
        if (cantidad <= 0) {
            throw new StockInvalidoException("❌ La cantidad debe ser mayor a 0");
        }
        p.addDetallePedido(cantidad, producto);
    }

    public void cancelarPedido(Long id) {
        pedidos.removeIf(p -> p.getId().equals(id));
    }

}
