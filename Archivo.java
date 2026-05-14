import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Archivo {

    //Metodo para crear un archivo con la información del viaje del vehiculo
    public static void generarArchivo(Vehiculo vehiculo, String origen, String destino, int distancia, int total){

        //Creamos el nombre del archivo con la placa del vehiculo
        String nombreArchivo = "Manifiesto del vehiculo " + vehiculo.getMatricula() +".txt";

        //Usamos try para escribir en el archivo 
        try(PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("Reporte de carga y viaje por Ruta Segura");
            writer.println("Vehiculo: " + vehiculo.getMatricula());
            writer.println("Conductor encargado: " + vehiculo.getConductor());
            writer.println("Origen: " + origen);
            writer.println("Destino: " + destino);
            writer.println("Distancia recorrida: " + distancia + " km");
            writer.println("Total por el costo del viaje: " + total);
        } 
        
        //En caso de algun error al generar el archivo se muestra un mensaje de error
        catch (IOException e) {
            System.out.println("Error al generar el archivo");
        }
}
}
