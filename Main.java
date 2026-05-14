import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Vehiculo> flota = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Bienvenido a Ruta Segura");
        int opcion = 0;

        while (opcion != 4) {
            opcion = leerOpcionMenu(); // Maneja el try-catch de entrada numérica
            
            switch (opcion) {
                case 1:
                    crearVehiculo();
                    break;
                case 2:
                    try {
                        Buscador.buscarVehiculo(flota);
                    } catch (VehiculoNoEncontradoException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    gestionarDistancia();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    // Solución al InputMismatchException solicitado
    public static int leerOpcionMenu() {
        while (true) {
            try {
                System.out.println("\n1. Crear | 2. Buscar | 3. Distancia | 4. Salir");
                int num = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer tras el número
                return num;
            } catch (InputMismatchException e) {
                System.err.println("Error: ¡Debe ingresar un número entero!");
                scanner.nextLine(); // CRÍTICO: Limpia el buffer del error
            }
        }
    }

    public static void crearVehiculo() {
    String ultPlaca = flota.isEmpty() ? "0" : flota.get(flota.size() - 1).getMatricula();
    System.out.print("Nombre del conductor: ");
    String conductor = scanner.nextLine();
    System.out.print("Marca: ");
    String marca = scanner.nextLine();
    System.out.print("Tipo (Furgon / Camioneta): ");
    String tipo = scanner.nextLine();
    // Bucle de validación usando la lógica de la clase Ubicacion
    String ubicacion;
    while (true) {
        System.out.println("Ingrese la ubicación actual (Medellin, Bogota, Cali, Cartagena, Barranquilla):");
        ubicacion = scanner.nextLine();
        if (Ubicacion.esUbicacionValida(ubicacion)) {
            break; // Salimos si la clase Ubicacion dice que es true
        } else {
            System.err.println("¡Error! La ciudad '" + ubicacion + "' no está en nuestra red de cobertura. Reintente.");
        }
    }
    // Creación final del objeto
    if (tipo.equalsIgnoreCase("Furgon")) {
        flota.add(new Furgon(ultPlaca, conductor, marca, ubicacion));
        System.out.println("Furgón registrado.");
    } else if (tipo.equalsIgnoreCase("Camioneta")) {
        flota.add(new Camioneta(ultPlaca, conductor, marca, ubicacion));
        System.out.println("Camioneta registrada.");
    }
    }   

    public static void gestionarDistancia() {
        try {
            System.out.println("Origen:");
            String origen = scanner.nextLine();
            System.out.println("Destino:");
            String destino = scanner.nextLine();
            int d = Ubicacion.obtenerDistancia(origen, destino);
            System.out.println("Distancia calculada: " + d + " km");
        } catch (UbicacionInvalidaException e) {
            System.err.println("Error de ubicación: " + e.getMessage());
        }
    }
}