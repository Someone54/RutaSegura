import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Archivo {

    //Metodo para crear un archivo con la información del viaje del vehiculo
    public static void generarArchivo(Vehiculo vehiculo, String origen, String destino, int distancia, int total){
        String nCarpeta = "Manifiestos";
        File carpeta = new File(nCarpeta);
        if (!carpeta.exists()) {
        carpeta.mkdir(); // Crea la carpeta si no existe
        }
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
        String fechaHora = LocalDateTime.now().format(formato);
        //Creamos el nombre del archivo con la placa del vehiculo en la carpeta especificada
        String nombreArchivo = nCarpeta + File.separator + "Manifiesto_"+ vehiculo.getMatricula() + "-" + fechaHora + ".txt";

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
