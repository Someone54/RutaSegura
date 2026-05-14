import java.util.ArrayList;
import java.util.Scanner;

public class Buscador {
    public static void buscarVehiculo(ArrayList<Vehiculo> flota, Scanner scanner) throws VehiculoNoEncontradoException {
        System.out.println("Ingresa la placa de vehiculo que quieras buscar:");
        String placa = scanner.nextLine().trim();

        for (Vehiculo vehiculo : flota) {
            if (vehiculo.getMatricula().equalsIgnoreCase(placa)) {
                System.out.println("Vehículo encontrado:");
                System.out.println("Placa: " + vehiculo.getMatricula());
                System.out.println("Conductor: " + vehiculo.getConductor());
                System.out.println("Marca: " + vehiculo.getMarca());
                System.out.println("Tipo: " + vehiculo.getTipo());
                System.out.println("Carga: " + vehiculo.getCarga());
                System.out.println("Tarifa: " + vehiculo.getTarifa());
                return;
            }
        }

        throw new VehiculoNoEncontradoException("No se encontró el vehículo con placa " + placa);
    }
}

