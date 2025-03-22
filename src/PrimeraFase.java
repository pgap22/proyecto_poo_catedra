import com.multiworks.clases.Usuario;

import java.util.Scanner;

public class PrimeraFase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = null;

        while(true) {
            System.out.println("\n1. Login\n2. Exit");
            int choice = scanner.nextInt();

            if(choice == 1) {
                System.out.println("Enter email:");
                String email = scanner.next();
                System.out.println("Enter password:");
                String password = scanner.next();

                usuario = new Usuario("Admin", password, email);
                if(usuario.autenticar(password)) {
                    manageSystem(usuario, scanner);
                }
            } else {
                break;
            }
        }
    }

    private static void manageSystem(Usuario usuario, Scanner scanner) {
        while(true) {
            System.out.println("\n1. Manage Clients\n2. Manage Employees\n3. Create Quote\n4. Exit");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    // Cliente management logic
                    break;
                case 2:
                    // Empelados management logic
                    break;
                case 3:
                    // Cotizacion creation logic
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}