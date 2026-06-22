package Integrador.prog2.Menu;

import Integrador.prog2.Entities.Pedido;
import java.util.Scanner;
import Integrador.prog2.Services.PedidoService;
import Integrador.prog2.Services.UsuarioService;
import Integrador.prog2.Services.ProductoService;
import Integrador.prog2.Entities.Usuario;
import Integrador.prog2.Entities.Producto;
import Integrador.prog2.Enums.Estado;
import Integrador.prog2.Enums.FormaPago;
import java.util.Arrays;

public class MenuPedidos extends MenuBase {

    private PedidoService pedidoService;
    private UsuarioService usuarioService;
    private ProductoService productoService;

    public MenuPedidos(PedidoService pedidoService, UsuarioService usuarioService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    @Override
    public void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PEDIDOS ---");
            System.out.println("1. Crear pedido");
            System.out.println("2. Listar pedidos");
            System.out.println("3. Editar pedido (estado/forma de pago)");
            System.out.println("4. Eliminar pedido");
            System.out.println("0. Volver");
            opcion = leerOpcion(sc, 0, 4);

            switch (opcion) {
                case 1 -> {
                    Pedido pedido = null;

                    try {

                        System.out.println("Usuarios disponibles:");
                        usuarioService.listar().forEach(System.out::println);

                        System.out.print("Ingrese ID del usuario: ");
                        Long idUser = sc.nextLong();
                        sc.nextLine();

                        var u = usuarioService.findById(idUser);

                        if (u == null || u.isEliminado()) {
                            System.out.println("⚠️ Usuario no encontrado o eliminado");
                            break;
                        }

                        pedido = pedidoService.create(
                                u,
                                Estado.PENDIENTE,
                                FormaPago.EFECTIVO
                        );

                        String seguir = "S";

                        while (seguir.equalsIgnoreCase("S")) {

                            System.out.println("\nProductos disponibles:");
                            productoService.listar().forEach(System.out::println);

                            System.out.print("Ingrese ID del producto: ");
                            Long idProd = sc.nextLong();

                            System.out.print("Cantidad: ");
                            int cantidad = sc.nextInt();
                            sc.nextLine();

                            var p = productoService.findById(idProd);

                            if (p == null || p.isEliminado()) {
                                throw new RuntimeException("⚠️ Producto no encontrado o eliminado");
                            }

                            pedidoService.addDetalle(
                                    pedido.getId(),
                                    cantidad,
                                    p
                            );

                            System.out.print("¿Desea agregar otro producto? (S/N): ");
                            seguir = sc.nextLine().toUpperCase();
                        }

                        System.out.println("✅ Pedido creado correctamente");

                    } catch (Exception e) {

                        if (pedido != null) {
                            pedidoService.cancelarPedido(pedido.getId());
                        }

                        System.out.println("❌ Error al crear pedido: " + e.getMessage());
                        System.out.println("⚠️ Pedido cancelado para evitar inconsistencias.");
                    }
                }

                case 2 -> {
                    var lista = pedidoService.listar();
                    if (lista.isEmpty()) {
                        System.out.println("⚠️ No hay pedidos cargados");
                    } else {
                        lista.forEach(System.out::println);
                    }
                }

                case 3 -> {
                    try {
                        System.out.print("ID del pedido a editar: ");
                        Long id = sc.nextLong();
                        sc.nextLine();

                        var pedido = pedidoService.findById(id);

                        if (pedido == null || pedido.isEliminado()) {
                            System.out.println("⚠️ Pedido no encontrado o eliminado");
                            break;
                        }

                        System.out.println("\nSeleccione nuevo estado:");
                        System.out.println("1. PENDIENTE");
                        System.out.println("2. CONFIRMADO");
                        System.out.println("3. TERMINADO");
                        System.out.println("4. CANCELADO");

                        int opcionEstado = leerOpcion(sc, 1, 4);

                        Estado estado = switch (opcionEstado) {
                            case 1 ->
                                Estado.PENDIENTE;
                            case 2 ->
                                Estado.CONFIRMADO;
                            case 3 ->
                                Estado.TERMINADO;
                            default ->
                                Estado.CANCELADO;
                        };

                        System.out.println("\nSeleccione nueva forma de pago:");
                        System.out.println("1. TARJETA");
                        System.out.println("2. TRANSFERENCIA");
                        System.out.println("3. EFECTIVO");

                        int opcionPago = leerOpcion(sc, 1, 3);

                        FormaPago formaPago = switch (opcionPago) {
                            case 1 ->
                                FormaPago.TARJETA;
                            case 2 ->
                                FormaPago.TRANSFERENCIA;
                            default ->
                                FormaPago.EFECTIVO;
                        };

                        pedidoService.update(id, estado, formaPago);

                        System.out.println("✅ Pedido actualizado correctamente.");

                    } catch (Exception e) {
                        System.out.println("❌ Error al editar pedido: " + e.getMessage());
                    }
                }

                case 4 -> { // Baja lógica con confirmación
                    try {
                        System.out.print("ID a eliminar: ");
                        Long id = sc.nextLong();
                        sc.nextLine();
                        System.out.print("¿Confirma eliminación? (S/N): ");
                        String confirm = sc.nextLine().toUpperCase();
                        if (confirm.equals("S")) {
                            pedidoService.delete(id); // baja lógica
                            System.out.println("✅ Pedido eliminado correctamente.");
                        } else {
                            System.out.println("⚠️ Operación cancelada");
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Error al eliminar pedido: " + e.getMessage());
                    }
                }
            }
        } while (opcion != 0);
    }
}
