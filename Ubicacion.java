import java.util.Map;
// colocar el private de string de origen y destino
public class Ubicacion {
    private static Map<String, Integer> ubicaciones = Map.ofEntries(
    Map.entry("Medellin", 0),
    Map.entry("Bogota", 1),
    Map.entry("Cali", 2),
    Map.entry("Cartagena", 3),
    Map.entry("Barranquilla", 4)
    );
    public static int[][] mapa = {
        {0, 150, 300, 450, 600},
        {150, 0, 200, 350, 500},
        {300, 200, 0, 250, 400},
        {450, 350, 250, 0, 150},
        {600, 500, 400, 150, 0}
    };
    public static int obtenerDistancia(String origen, String destino) {
        // Verificamos si las ciudades existen antes de acceder al arreglo
        if (!ubicaciones.containsKey(origen) || !ubicaciones.containsKey(destino)) {
            throw new UbicacionInvalidaException("Una o ambas ciudades no están registradas en el sistema.");
        }
        
        return mapa[ubicaciones.get(origen)][ubicaciones.get(destino)];
    }
    public static boolean esUbicacionValida(String ciudad) {
        if (ciudad == null || ciudad.isEmpty()) return false;
        
        // Formatear: "medellin" -> "Medellin" para que coincida con el Map
        String ciudadFormateada = ciudad.substring(0, 1).toUpperCase() + ciudad.substring(1).toLowerCase();
        
        return ubicaciones.containsKey(ciudadFormateada);
    }
}

