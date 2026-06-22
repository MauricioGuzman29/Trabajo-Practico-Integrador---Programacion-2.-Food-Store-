package Integrador.prog2.Menu;

import java.util.Scanner;
import Integrador.prog2.Interfaces.MenuPantalla;

public abstract class MenuBase implements MenuPantalla {

    protected int leerOpcion(Scanner sc, int min, int max) {
        int opcion = -1;
        boolean valido = false;

        while (!valido) {
            System.out.print("Ingrese una opción: ");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion >= min && opcion <= max) {
                    valido = true;
                } else {
                    System.out.println("❌ Opción inválida. Ingrese un número entre " + min + " y " + max + ".");

                }
            } else {
                System.out.println("❌ Entrada inválida. Debe ingresar un número valido.");
                sc.nextLine();
            }
        }
        return opcion;
    }

}
