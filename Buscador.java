import java.util.ArrayList;
import java.util.Scanner;

public class Buscador{
    public static void buscarVehiculo(ArrayList<Vehiculo> flota){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa la placa de vehiculo que quieras buscar:");
        String placa = scanner.nextLine();

        // Recorro la flota de vehiculos
        for(Vehiculo vehiculo : flota){

            // Se compara la placa ingresada con las de los vehiculos en la flota
            if(vehiculo.getMatricula().equalsIgnoreCase(placa)){
                System.out.println("Vehículo encontrado:");
                System.out.println("Placa: " + vehiculo.getMatricula());
                System.out.println("Conductor: " + vehiculo.getConductor());
                System.out.println("Marca: " + vehiculo.getMarca());
                System.out.println("Tipo: " + vehiculo.getTipo());
                System.out.println("Carga: " + vehiculo.getCarga());
                System.out.println("Tarifa: " + vehiculo.getTarifa());
                break;
            }else{
                System.out.println("Vehículo no encontrado.");// Si no se encuentra el vehiculo se le avisa al usuario
            }
        }
    }
}