import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Archivo {
    public static void generarArchivo(Vehiculo vehiculo, String origen, String destino, int distancia, int total){
        String nombreArchivo = "Manifiesto del vehiculo " + vehiculo.getMatricula() +".txt";
        try(PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("Reporte de carga y viaje por Ruta Segura");
            writer.println("Vehiculo: " + vehiculo.getMatricula());
            writer.println("Conductor encargado: " + vehiculo.getConductor());
            writer.println("Origen: " + origen);
            writer.println("Destino: " + destino);
            writer.println("Distancia recorrida: " + distancia + " km");
            writer.println("Total por el costo del viaje: " + total);
        } catch (IOException e) {
            System.out.println("Error al generar el archivo");
        }
}
}
