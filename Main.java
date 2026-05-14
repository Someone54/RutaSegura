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
            opcion = leerOpcionMenu();

            switch (opcion) {
                case 1:
                    crearVehiculo();
                    break;
                case 2:
                    try {
                        Buscador.buscarVehiculo(flota, scanner);
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
                case 5:
                    generarArchivo();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    public static int leerOpcionMenu() {
        while (true) {
            try {
                System.out.println("\n1. Crear | 2. Buscar | 3. Distancia | 4. Salir | 5. Generar archivo");
                int num = scanner.nextInt();
                scanner.nextLine();
                return num;
            } catch (InputMismatchException e) {
                System.err.println("Error: ¡Debe ingresar un número entero!");
                scanner.nextLine();
            }
        }
    }

    public static void crearVehiculo() {
        String ultPlaca = flota.isEmpty() ? "0" : flota.get(flota.size() - 1).getMatricula();
        System.out.print("Nombre del conductor: ");
        String conductor = scanner.nextLine().trim();
        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();
        System.out.print("Tipo (Furgon / Camioneta): ");
        String tipo = scanner.nextLine().trim();

        String ubicacion;
        while (true) {
            System.out.println("Ingrese la ubicación actual (Medellin, Bogota, Cali, Cartagena, Barranquilla):");
            ubicacion = scanner.nextLine();
            if (Ubicacion.esUbicacionValida(ubicacion)) {
                ubicacion = Ubicacion.normalizarCiudad(ubicacion);
                break;
            }
            System.err.println("¡Error! La ciudad '" + ubicacion + "' no está en nuestra red de cobertura. Reintente.");
        }

        if (tipo.equalsIgnoreCase("Furgon")) {
            flota.add(new Furgon(ultPlaca, conductor, marca, ubicacion));
            System.out.println("Furgón registrado.");
        } else if (tipo.equalsIgnoreCase("Camioneta")) {
            flota.add(new Camioneta(ultPlaca, conductor, marca, ubicacion));
            System.out.println("Camioneta registrada.");
        } else {
            System.err.println("Tipo no válido. Debe ser 'Furgon' o 'Camioneta'.");
        }
    }

    public static void gestionarDistancia() {
        try {
            System.out.println("Origen:");
            String origen = scanner.nextLine().trim();
            System.out.println("Destino:");
            String destino = scanner.nextLine().trim();
            int d = Ubicacion.obtenerDistancia(origen, destino);
            System.out.println("Distancia calculada: " + d + " km");
        } catch (UbicacionInvalidaException e) {
            System.err.println("Error de ubicación: " + e.getMessage());
        }
    }
    
    public static void generarArchivo() {
        if (flota.isEmpty()) {
            System.err.println("No hay vehículos registrados para generar el archivo.");
            return;
        }

        try {
            System.out.println("Ingresa la placa del vehículo:");
            String placa = scanner.nextLine().trim();
            Vehiculo vehiculo = buscarVehiculoPorPlaca(placa);

            System.out.println("Origen:");
            String origen = scanner.nextLine().trim();
            System.out.println("Destino:");
            String destino = scanner.nextLine().trim();

            int distancia = Ubicacion.obtenerDistancia(origen, destino);
            int total = vehiculo.calcularFlete(distancia);

            Archivo.generarArchivo(vehiculo, Ubicacion.normalizarCiudad(origen), Ubicacion.normalizarCiudad(destino), distancia, total);
            System.out.println("Archivo generado correctamente.");
        } catch (VehiculoNoEncontradoException e) {
            System.err.println(e.getMessage());
        } catch (UbicacionInvalidaException e) {
            System.err.println("Error de ubicación: " + e.getMessage());
        }
    }

    public static Vehiculo buscarVehiculoPorPlaca(String placa) throws VehiculoNoEncontradoException {
        for (Vehiculo vehiculo : flota) {
            if (vehiculo.getMatricula().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }
        throw new VehiculoNoEncontradoException("No se encontró el vehículo con placa " + placa);
    }
}
