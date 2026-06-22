package Integrador.prog2.Menu;

import java.util.Scanner;
import Integrador.prog2.Services.CategoriaService;
import Integrador.prog2.Services.ProductoService;
import Integrador.prog2.Services.UsuarioService;
import Integrador.prog2.Services.PedidoService;

public class Menu extends MenuBase {

    private CategoriaService categoriaService = new CategoriaService();
    private ProductoService productoService = new ProductoService();
    private UsuarioService usuarioService = new UsuarioService();
    private PedidoService pedidoService = new PedidoService();

    @Override
    public void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Categorías");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            opcion = leerOpcion(sc, 0, 4);

            switch (opcion) {
                case 1 ->
                    new MenuCategorias(categoriaService).mostrar(sc);
                case 2 ->
                    new MenuProductos(productoService, categoriaService).mostrar(sc);
                case 3 ->
                    new MenuUsuarios(usuarioService).mostrar(sc);
                case 4 ->
                    new MenuPedidos(pedidoService, usuarioService, productoService).mostrar(sc);
                case 0 ->
                    System.out.println("Saliendo del sistema...");
            }
        } while (opcion != 0);
    }
}
