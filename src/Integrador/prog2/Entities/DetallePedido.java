package Integrador.prog2.Entities;

import Integrador.prog2.Exception.DatoInvalidoException;

public class DetallePedido extends Base {

    private int cantidad;
    private double subtotal;
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto, Long id) {
        super(id);

        if (cantidad <= 0) {
            throw new DatoInvalidoException("❌ La cantidad debe ser mayor a 0");
        }
        if (producto == null || producto.isEliminado()) {
            throw new DatoInvalidoException("❌ El producto no es válido");
        }

        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = calcularSubtotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double calcularSubtotal() {
        return cantidad * producto.getPrecio();
    }

    @Override
    public String toString() {
        return String.format("DetallePedido #%d: %s x %d => Subtotal: $%.2f",
                getId(), producto.getNombre(), cantidad, subtotal);

    }
}
