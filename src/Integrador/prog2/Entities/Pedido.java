package Integrador.prog2.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import Integrador.prog2.Enums.Estado;
import Integrador.prog2.Enums.FormaPago;
import Integrador.prog2.Interfaces.Calculable;
import Integrador.prog2.Exception.DatoInvalidoException;
import Integrador.prog2.Exception.EntidadNoEncontradaException;
import Integrador.prog2.Exception.StockInvalidoException;

public class Pedido extends Base implements Calculable {

    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private ArrayList<DetallePedido> detalles;
    private Usuario usuario;
    private Long ultimoIdDetalle;

    public Pedido(LocalDate fecha, Estado estado, FormaPago formaPago, Usuario usuario, Long id) {
        super(id);

        if (usuario == null || usuario.isEliminado()) {
            throw new EntidadNoEncontradaException("✘ El pedido debe tener un usuario válido");
        }

        this.fecha = fecha;
        this.estado = estado;
        this.formaPago = formaPago;
        this.detalles = new ArrayList<>();
        this.usuario = usuario;
        this.ultimoIdDetalle = 0L;
        this.total = 0;
    }

    @Override
    public void calcularTotal() {
        double suma = 0;
        for (DetallePedido detalle : detalles) {
            suma += detalle.getSubtotal();
        }
        this.total = suma;
    }

    public void addDetallePedido(int cantidad, Producto p) {
        if (cantidad <= 0) {
            throw new DatoInvalidoException("❌ La cantidad debe ser mayor a 0");
        }
        if (p == null || p.isEliminado()) {
            throw new EntidadNoEncontradaException("❌ El producto no es válido");
        }
        if (p.getStock() < cantidad) {
            throw new StockInvalidoException("⚠️ Stock insuficiente para el producto " + p.getNombre() + " *");
        }

        ultimoIdDetalle++;
        DetallePedido detalle = new DetallePedido(cantidad, p, ultimoIdDetalle);
        detalles.add(detalle);
        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(Producto p) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().getId().equals(p.getId())) {
                return detalle;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto p) {
        detalles.removeIf(detalle -> detalle.getProducto().getId().equals(p.getId()));
        calcularTotal();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {

        String salida = String.format(
                "Pedido #%d | Usuario: %s %s | Fecha: %s | Estado: %s | FormaPago: %s\n",
                getId(), usuario.getNombre(), usuario.getApellido(), fecha, estado, formaPago);
        salida += "-----------------------------------------------------------\n";
        for (DetallePedido d : detalles) {
            salida += "- " + d + "\n";
        }
        salida += String.format("TOTAL DEL PEDIDO: $%.2f\n", total);
        salida += "-----------------------------------------------------------\n";
        return salida;
    }
}
