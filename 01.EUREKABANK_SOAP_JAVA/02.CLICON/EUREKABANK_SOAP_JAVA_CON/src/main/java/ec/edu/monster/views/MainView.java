
package ec.edu.monster.views;

import ec.edu.monster.ws.LoginService;
import java.util.Scanner;

public class MainView {
       public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        LoginService loginService = new LoginService();
        boolean running = true;

        while (running) {
            System.out.println("=== MENÚ LOGIN ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese su nombre de usuario: ");
                    String username = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String password = scanner.nextLine();

                    boolean isAuthenticated = loginService.auth(username, password);

                    if (isAuthenticated) {
                        System.out.println("¡Inicio de sesión exitoso!");
                    } else {
                        System.out.println("Usuario o contraseña incorrectos. Inténtelo de nuevo.");
                    }
                    break;

                case 2:
                    System.out.println("Saliendo del sistema...");
                    running = false;
                    break;

                default:
                    System.out.println("Opción no válida. Por favor intente de nuevo.");
            }

            System.out.println(); // Línea en blanco para separar iteraciones del menú
        }

        scanner.close();
    }}