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
        int distancia = mapa[ubicaciones.get(origen)][ubicaciones.get(destino)];
        return distancia;
    }
}

