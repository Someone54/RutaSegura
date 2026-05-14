import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Vehiculo> flota = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Input de datos para un vehículo nuevo
        if (flota.isEmpty()){
            System.out.println("Ingrese la matrícula del vehículo:");
        String matricula = scanner.nextLine();
        System.out.println("Ingrese el nombre del conductor:");
        String conductor = scanner.nextLine();
        System.out.println("Ingrese la marca del vehículo:");
        String marca = scanner.nextLine();
        System.out.println("Ingrese el tipo de vehículo:");
        String tipo = scanner.nextLine();
        System.out.println("Ingrese la tarifa por kilómetro:");
        int tarifa = scanner.nextInt();
        flota.add(new Vehiculo(matricula, conductor, marca, tipo, tarifa));
        }
        
        // Input de ubicacion
        System.out.println("Ingrese la ubicación actual del vehículo:");
        String ubicacion = scanner.nextLine();
        System.out.println("Ingrese la ubicación de destino:");
        String destino = scanner.nextLine();
        Ubicacion ubicacionObj = new Ubicacion();
    }
}