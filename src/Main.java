import model.Reserva;
import repository.ReservaRepository;
import repository.ReservaRepositoryArchivo;
import repository.ReservaRepositoryMySQL;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== SISTEMA DE RESERVAS DE HOTEL ===");
        System.out.println("Seleccione la fuente de almacenamiento:");
        System.out.println("1. MySQL");
        System.out.println("2. Archivo de texto");
        System.out.print("Opción: ");
        int fuente = sc.nextInt(); sc.nextLine();

        ReservaRepository repo = fuente == 1
                ? new ReservaRepositoryMySQL()
                : new ReservaRepositoryArchivo();

        ReservaRepository repoArchivo = new ReservaRepositoryArchivo();
        ReservaRepository repoMySQL = new ReservaRepositoryMySQL();

        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar reserva");
            System.out.println("2. Modificar reserva");
            System.out.println("3. Eliminar reserva");
            System.out.println("4. Listar reservas");
            System.out.println("5. Sincronizar archivo → MySQL");
            System.out.println("6. Sincronizar MySQL → archivo");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nombre cliente: ");
                    String nombre = sc.nextLine();
                    System.out.print("Fecha ingreso (dd/mm/yyyy): ");
                    String ingreso = sc.nextLine();
                    System.out.print("Fecha salida (dd/mm/yyyy): ");
                    String salida = sc.nextLine();
                    System.out.print("Habitación: ");
                    String hab = sc.nextLine();
                    repo.agregarReserva(new Reserva(id, nombre, ingreso, salida, hab));
                    break;

                case 2:
                    System.out.print("ID a modificar: ");
                    int idMod = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo nombre cliente: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nueva fecha ingreso: ");
                    String nuevoIngreso = sc.nextLine();
                    System.out.print("Nueva fecha salida: ");
                    String nuevoSalida = sc.nextLine();
                    System.out.print("Nueva habitación: ");
                    String nuevoHab = sc.nextLine();
                    repo.modificarReserva(new Reserva(idMod, nuevoNombre, nuevoIngreso, nuevoSalida, nuevoHab));
                    break;

                case 3:
                    System.out.print("ID a eliminar: ");
                    int idElim = sc.nextInt();
                    repo.eliminarReserva(idElim);
                    break;

                case 4:
                    List<Reserva> lista = repo.listarReservas();
                    if (lista.isEmpty()) {
                        System.out.println("No hay reservas registradas.");
                    } else {
                        lista.forEach(System.out::println);
                    }
                    break;

                case 5: // Archivo → MySQL
                    List<Reserva> desdeArchivo = repoArchivo.listarReservas();
                    for (Reserva r : desdeArchivo) repoMySQL.agregarReserva(r);
                    System.out.println("Sincronización archivo → MySQL completada.");
                    break;

                case 6: // MySQL → Archivo
                    List<Reserva> desdeMySQL = repoMySQL.listarReservas();
                    for (Reserva r : desdeMySQL) repoArchivo.agregarReserva(r);
                    System.out.println("Sincronización MySQL → archivo completada.");
                    break;

                case 7:
                    System.out.println("Hasta luego.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);

        sc.close();
    }
}