
package ec.edu.monster.views;

import ec.edu.monster.ws.CuentaService;
import ec.edu.monster.ws.LoginService;
import ec.edu.monster.ws.MovimientoModel;
import ec.edu.monster.ws.MovimientoService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public class MainView {
      private static LoginService loginService = new LoginService();
    private static CuentaService cuentaService = new CuentaService();
    private static MovimientoService movimientoService = new MovimientoService();
    private static Scanner scanner = new Scanner(System.in);
    private static String usuarioActual = null;

    public static void main(String[] args) {
        while (true) {
            if (usuarioActual == null) {
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
            case 1:
                iniciarSesion();
                break;
            case 2:
                System.out.println("Gracias por usar nuestros servicios. ¡Hasta luego!");
                System.exit(0);
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    private static void iniciarSesion() {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        try {
            String hashedPassword = hashPassword(password);
            boolean autenticado = loginService.auth(username, hashedPassword);

            if (autenticado) {
                usuarioActual = username;
                System.out.println("Inicio de sesión exitoso.");
            } else {
                System.out.println("Credenciales incorrectas. Intente nuevamente.");
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error en la autenticación: " + e.getMessage());
        }
    }

    private static void menuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Realizar Depósito");
        System.out.println("2. Ver Movimientos");
        System.out.println("3. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                realizarDeposito();
                break;
            case 2:
                verMovimientos();
                break;
            case 3:
                cerrarSesion();
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    private static void realizarDeposito() {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine();
        System.out.print("Ingrese el monto a depositar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        boolean depositoExitoso = cuentaService.realizarDeposito(cuenta, monto);
        if (depositoExitoso) {
            System.out.println("Depósito realizado con éxito.");
        } else {
            System.out.println("Error al realizar el depósito.");
        }
    }

   private static void verMovimientos() {
    System.out.print("Ingrese el número de cuenta: ");
    String cuenta = scanner.nextLine();

    // Usar el servicio web de movimientos
  
    
    try {
        List<MovimientoModel> movimientos = movimientoService.obtenerMovimientos(cuenta);
        
        if (movimientos != null && !movimientos.isEmpty()) {
            System.out.println("\n===== MOVIMIENTOS DE LA CUENTA " + cuenta + " =====");
            System.out.printf("%-10s %-20s %-15s %-20s %-15s %-15s%n", 
                "Nro", "Fecha", "Tipo Mov.", "Descripción", "Importe", "Cta Referencia");
            System.out.println("------------------------------------------------------------------------");
            
            for (MovimientoModel mov : movimientos) {
                System.out.printf("%-10d %-20s %-15s %-20s %-15.2f %-15s%n", 
                    mov.getNumeroMovimiento(),
                    mov.getFechaMovimiento(),
                    mov.getCodigoTipoMovimiento(),
                    mov.getTipoDescripcion(),
                    mov.getImporteMovimiento(),
                    mov.getCuentaReferencia() != null ? mov.getCuentaReferencia() : "N/A"
                );
            }
            
            // Cálculo de resumen
            double totalIngresos = movimientos.stream()
                .filter(m -> m.getImporteMovimiento() > 0)
                .mapToDouble(MovimientoModel::getImporteMovimiento)
                .sum();
            
            double totalEgresos = movimientos.stream()
                .filter(m -> m.getImporteMovimiento() < 0)
                .mapToDouble(MovimientoModel::getImporteMovimiento)
                .sum();
            
            System.out.println("\nRESUMEN:");
            System.out.printf("Total Ingresos: %.2f%n", totalIngresos);
            System.out.printf("Total Egresos: %.2f%n", Math.abs(totalEgresos));
            System.out.printf("Saldo Neto: %.2f%n", totalIngresos + totalEgresos);
            
        } else {
            System.out.println("No se encontraron movimientos para la cuenta: " + cuenta);
        }
    } catch (Exception e) {
        System.out.println("Error al obtener los movimientos: " + e.getMessage());
        e.printStackTrace();
    }
}

    private static void cerrarSesion() {
        usuarioActual = null;
        System.out.println("Sesión cerrada exitosamente.");
    }

    private static String hashPassword(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}