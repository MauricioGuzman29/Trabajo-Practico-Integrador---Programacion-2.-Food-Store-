package Integrador.prog2.Menu;

import java.util.Scanner;
import Integrador.prog2.Services.CategoriaService;

public class MenuCategorias extends MenuBase {

    private CategoriaService service;

    public MenuCategorias(CategoriaService service) {
        this.service = service;
    }

    @Override
    public void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ CATEGORÍAS ---");
            System.out.println("1. Crear categoría");
            System.out.println("2. Listar categorías");
            System.out.println("3. Editar categoría");
            System.out.println("4. Eliminar categoría");
            System.out.println("0. Volver");
            opcion = leerOpcion(sc, 0, 4);

            switch (opcion) {
                case 1 -> {
                    try {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Descripción: ");
                        String desc = sc.nextLine();
                        service.create(nombre, desc);
                        System.out.println("✅ Categoría creada correctamente.");
                    } catch (Exception e) {
                        System.out.println("❌ Error al crear categoría: " + e.getMessage());
                    }
                }
                case 2 -> {
                    var lista = service.listar();
                    if (lista.isEmpty()) {
                        System.out.println(" ⚠️ No hay categorías cargadas");
                    } else {
                        lista.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    try {
                        System.out.print("ID a editar: ");
                        Long id = sc.nextLong();
                        sc.nextLine();
                        System.out.print("Nuevo nombre (Enter para mantener actual): ");
                        String nombre = sc.nextLine();
                        System.out.print("Nueva descripción (Enter para mantener actual): ");
                        String desc = sc.nextLine();
                        service.update(id, nombre, desc);
                        System.out.println("✅ Categoría actualizada correctamente.");
                    } catch (Exception e) {
                        System.out.println("❌ Error al editar categoría: " + e.getMessage());
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
                            service.delete(id);
                            System.out.println("✅ Categoría eliminada correctamente.");
                        } else {
                            System.out.println("❌ Operación cancelada.");
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Error al eliminar categoría: " + e.getMessage());
                    }
                }
            }
        } while (opcion != 0);
    }
}
