import java.util.ArrayList;
import java.util.Scanner;

public class Buscador {
    public static void buscarVehiculo(ArrayList<Vehiculo> flota, Scanner scanner) throws VehiculoNoEncontradoException {
        System.out.println("Ingresa la placa de vehiculo que quieras buscar:");

        //Usamos el scanner de Main para leer la placa del vehiculo junto con trim() para evitar espacios innesesarios
        String placa = scanner.nextLine().trim();

        //Recorremos la flota de vehiculos para encontrar al coincidente con la placa ingresada por el usuario
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

        //En caso de no encontrar el vehiculo correspondiente salta un aviso junto con la placa ingresada
        throw new VehiculoNoEncontradoException("No se encontró el vehículo con placa " + placa);
    }
}

