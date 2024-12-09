package ec.edu.monster.views;

import ec.edu.monster.controllers.MainController;
import java.util.Scanner;

public class MainView {
    private static Scanner scanner = new Scanner(System.in);
    private static MainController mainController = new MainController();

    public static void main(String[] args) {
        while (true) {
            if (mainController.getUsuarioActual() == null) {
                menuLogin();
            } else {
                menuPrincipal();
            }
        }
    }

    private static void menuLogin() {
        System.out.println("===== BANCO MONSTER =====");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> iniciarSesion();
            case 2 -> {
                System.out.println("Gracias por usar nuestros servicios. ¡Hasta luego!");
                System.exit(0);
            }
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    private static void iniciarSesion() {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        if (mainController.iniciarSesion(username, password)) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Credenciales incorrectas. Intente nuevamente.");
        }
    }

    private static void menuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Realizar Depósito");
        System.out.println("2. Realizar Retiro");
        System.out.println("3. Realizar Transferencia");
        System.out.println("4. Ver Movimientos");
        System.out.println("5. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> realizarDeposito();
            case 2 -> realizarRetiro();
            case 3 -> realizarTransferencia();
            case 4 -> verMovimientos();
            case 5 -> cerrarSesion();
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    private static void realizarDeposito() {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine();
        System.out.print("Ingrese el monto a depositar: ");
        String monto = scanner.nextLine();

        if (mainController.realizarDeposito(cuenta, monto, "DEP", null)) {
            System.out.println("Depósito realizado con éxito.");
        } else {
            System.out.println("Error al realizar el depósito.");
        }
    }

    private static void realizarRetiro() {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine();
        System.out.print("Ingrese el monto a retirar: ");
        String monto = scanner.nextLine();

        if (mainController.realizarRetiro(cuenta, monto)) {
            System.out.println("Retiro realizado con éxito.");
        } else {
            System.out.println("Error al realizar el retiro.");
        }
    }

    private static void realizarTransferencia() {
        System.out.print("Ingrese el número de cuenta origen: ");
        String cuentaOrigen = scanner.nextLine();
        System.out.print("Ingrese el número de cuenta destino: ");
        String cuentaDestino = scanner.nextLine();
        System.out.print("Ingrese el monto a transferir: ");
        String monto = scanner.nextLine();

        if (mainController.realizarTransferencia(cuentaOrigen, cuentaDestino, monto)) {
            System.out.println("Transferencia realizada con éxito.");
        } else {
            System.out.println("Error al realizar la transferencia.");
        }
    }

    private static void verMovimientos() {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine();

        String resultado = mainController.verMovimientos(cuenta);
        System.out.println(resultado);
    }

    private static void cerrarSesion() {
        mainController.cerrarSesion();
        System.out.println("Sesión cerrada exitosamente.");
    }
}
