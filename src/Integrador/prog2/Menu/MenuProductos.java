package Integrador.prog2.Menu;

import java.util.Scanner;
import Integrador.prog2.Services.ProductoService;
import Integrador.prog2.Services.CategoriaService;
import Integrador.prog2.Entities.Categoria;

public class MenuProductos extends MenuBase {

    private ProductoService productoService;
    private CategoriaService categoriaService;

    public MenuProductos(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @Override
    public void mostrar(Scanner sc) {
        int opcion;

        do {
            System.out.println("\n--- MENÚ PRODUCTOS ---");
            System.out.println("1. Crear producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Editar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("0. Volver");

            opcion = leerOpcion(sc, 0, 4);

            switch (opcion) {

                case 1 -> {
                    try {

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Precio: ");
                        double precio = sc.nextDouble();

                        System.out.print("Stock: ");
                        int stock = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Descripción: ");
                        String desc = sc.nextLine();

                        System.out.print("Imagen: ");
                        String imagen = sc.nextLine();

                        System.out.print("Disponible (true/false): ");
                        boolean disponible = sc.nextBoolean();
                        sc.nextLine();

                        System.out.println("\nCategorías disponibles:");
                        categoriaService.listar().forEach(System.out::println);

                        System.out.print("Ingrese ID de la categoría: ");
                        Long idCat = sc.nextLong();
                        sc.nextLine();

                        Categoria cat = categoriaService.findById(idCat);

                        if (cat == null || cat.isEliminado()) {
                            System.out.println("⚠️ Categoría no encontrada o eliminada");
                            break;
                        }

                        productoService.create(nombre, precio, desc, stock, imagen, disponible, cat);

                        System.out.println("✅ Producto creado correctamente.");

                    } catch (Exception e) {
                        System.out.println("❌ Error al crear producto: " + e.getMessage());
                    }
                }

                case 2 -> {
                    var lista = productoService.listar();

                    if (lista.isEmpty()) {
                        System.out.println("⚠️ No hay productos cargados");
                    } else {
                        lista.forEach(System.out::println);
                    }
                }

                case 3 -> {
                    try {

                        System.out.print("ID a editar: ");
                        Long id = Long.parseLong(sc.nextLine());

                        System.out.print("Nuevo nombre (Enter para mantener actual): ");
                        String nombre = sc.nextLine();

                        System.out.print("Nueva descripción (Enter para mantener actual): ");
                        String desc = sc.nextLine();

                        System.out.print("Nuevo precio (Enter para mantener actual): ");
                        String precioStr = sc.nextLine();

                        System.out.print("Nuevo stock (Enter para mantener actual): ");
                        String stockStr = sc.nextLine();

                        System.out.println("\nCategorías disponibles:");
                        categoriaService.listar().forEach(System.out::println);

                        System.out.print("Nuevo ID categoría (Enter para mantener actual): ");
                        String idCategoriaStr = sc.nextLine();

                        Double precio = null;
                        Integer stock = null;
                        Categoria nuevaCategoria = null;

                        if (!precioStr.isBlank()) {
                            precio = Double.parseDouble(precioStr);
                        }

                        if (!stockStr.isBlank()) {
                            stock = Integer.parseInt(stockStr);
                        }

                        if (!idCategoriaStr.isBlank()) {
                            Long idCategoria = Long.parseLong(idCategoriaStr);

                            nuevaCategoria = categoriaService.findById(idCategoria);

                            if (nuevaCategoria == null || nuevaCategoria.isEliminado()) {
                                System.out.println("⚠️ Categoría no encontrada o eliminada");
                                break;
                            }
                        }

                        productoService.update(id, nombre, desc, precio, stock, nuevaCategoria);

                        System.out.println("✅ Producto actualizado correctamente.");

                    } catch (Exception e) {
                        System.out.println("❌ Error al editar producto: " + e.getMessage());
                    }
                }

                case 4 -> {
                    try {

                        System.out.print("ID a eliminar: ");
                        Long id = sc.nextLong();
                        sc.nextLine();

                        System.out.print("¿Confirma eliminación? (S/N): ");
                        String confirm = sc.nextLine().toUpperCase();

                        if (confirm.equals("S")) {
                            productoService.delete(id);
                            System.out.println("✅ Producto eliminado correctamente.");
                        } else {
                            System.out.println("⚠️ Operación cancelada");
                        }

                    } catch (Exception e) {
                        System.out.println("❌ Error al eliminar producto: " + e.getMessage());
                    }
                }
            }

        } while (opcion != 0);
    }
}
