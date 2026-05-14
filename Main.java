import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Vehiculo> flota = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Bienvenido a Ruta Segura, que desea realizar?");
        int opcion = 0;
        while (opcion != 4) {
            opcion = mostrarMenu();
            scanner.nextLine(); // Consumir el salto de línea
            switch (opcion) {
                case 1:
                    crearVehiculo();
                    break;
                case 2:
                    Buscador.buscarVehiculo(flota);
                    break;
                case 3:
                    System.out.println("Ingrese la ubicación de origen:");
                    String origen = scanner.nextLine();
                    System.out.println("Ingrese la ubicación de destino:");
                    String destino = scanner.nextLine();
                    System.out.println("La distancia entre " + origen + " y " + destino + " es: " + Ubicacion.obtenerDistancia(origen, destino));
                    break;
                case 4:
                    System.out.println("Gracias por usar Ruta Segura. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }   
        }
        scanner.close();
        
        
        
    }
    public static int mostrarMenu(){
        System.out.println("Seleccione una opción:");
        System.out.println("1. Crear nuevo vehículo");
        System.out.println("2. Buscar vehículo por matrícula");
        System.out.println("3. Calcular distancia entre ubicaciones");
        System.out.println("4. Salir");
        return scanner.nextInt();
    }
    
    public static void enviarVehiculo(Vehiculo vehiculo) {
        System.out.println("Ingrese la ubicación de destino:");
        String destino = scanner.nextLine();
        int distancia = Ubicacion.obtenerDistancia(vehiculo.getUbicacion(), destino);
        int flete = vehiculo.calcularFlete(distancia);
        System.out.println("El flete para el vehículo " + vehiculo.getMatricula() + " es: " + flete);
    }

    public static void crearVehiculo(){
        String ultPlaca = "0";
        if (!flota.isEmpty()){
            ultPlaca = flota.get(flota.size()-1).getMatricula();
        }

        System.out.println("Ingrese el nombre del conductor:");
        String conductor = scanner.nextLine();
        System.out.println("Ingrese la marca del vehículo:");
        String marca = scanner.nextLine();
        System.out.println("Ingrese el tipo de vehículo:");
        String tipo = scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese la ubicación actual del vehículo:");
        String ubicacion = scanner.nextLine();
        
        if (tipo.equalsIgnoreCase("Furgon")){
            flota.add(new Furgon(ultPlaca,conductor, marca, ubicacion));
        } else if (tipo.equalsIgnoreCase("Camioneta")){
            flota.add(new Camioneta(ultPlaca,conductor, marca, ubicacion));
        } else {
            System.out.println("Tipo de vehículo no reconocido. Vehículo no creado.");
        }
    }
}