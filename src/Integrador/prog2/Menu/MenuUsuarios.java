package Integrador.prog2.Menu;

import java.util.Scanner;
import Integrador.prog2.Services.UsuarioService;
import Integrador.prog2.Enums.Rol;

public class MenuUsuarios extends MenuBase {

    private UsuarioService service;

    public MenuUsuarios(UsuarioService service) {
        this.service = service;
    }

    @Override
    public void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ USUARIOS ---");
            System.out.println("1. Crear usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("0. Volver");
            opcion = leerOpcion(sc, 0, 4);

            switch (opcion) {
                case 1 -> {
                    try {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = sc.nextLine();
                        System.out.print("Mail: ");
                        String mail = sc.nextLine();
                        System.out.print("Celular: ");
                        String celular = sc.nextLine();
                        System.out.print("Contraseña: ");
                        String contrasenia = sc.nextLine();

                        System.out.print("Rol (ADMIN/USUARIO): ");
                        String rolStr = sc.nextLine().toUpperCase();
                        Rol rol;
                        try {
                            rol = Rol.valueOf(rolStr);
                        } catch (IllegalArgumentException e) {
                            System.out.println("⚠️ Rol inválido, se asigna USUARIO por defecto ");
                            rol = Rol.USUARIO;
                        }

                        service.create(nombre, apellido, mail, celular, contrasenia, rol);
                        System.out.println("✅ Usuario creado correctamente.");
                    } catch (Exception e) {
                        System.out.println("❌ Error al crear usuario: " + e.getMessage());
                    }
                }

                case 2 -> {
                    var lista = service.listar();
                    if (lista.isEmpty()) {
                        System.out.println("⚠️ No hay usuarios cargados ");
                    } else {
                        lista.forEach(System.out::println);
                    }
                }

                case 3 -> { 
                    try {
                        System.out.print("ID a editar: ");
                        Long id = sc.nextLong();
                        sc.nextLine();
                        var u = service.findById(id);
                        if (u == null || u.isEliminado()) {
                            System.out.println("⚠️ Usuario no encontrado o eliminado");
                            break;
                        }
                        System.out.print("Nuevo nombre (Enter para mantener actual): ");
                        String nombre = sc.nextLine();
                        System.out.print("Nuevo apellido (Enter para mantener actual): ");
                        String apellido = sc.nextLine();
                        System.out.print("Nuevo mail (Enter para mantener actual): ");
                        String mail = sc.nextLine();
                        System.out.print("Nuevo celular (Enter para mantener actual): ");
                        String celular = sc.nextLine();

                        service.update(id, nombre, apellido, mail, celular);
                        System.out.println("✅ Usuario actualizado correctamente.");
                    } catch (Exception e) {
                        System.out.println("❌ Error al editar usuario: " + e.getMessage());
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
                            service.delete(id); 
                            System.out.println("✅ Usuario eliminado correctamente.");
                        } else {
                            System.out.println("⚠️ Operación cancelada");
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Error al eliminar usuario: " + e.getMessage());
                    }
                }
            }
        } while (opcion != 0);
    }
}