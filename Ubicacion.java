import java.util.HashMap;
import java.util.Map;
// colocar el private de string de origen y destino
public class Ubicacion {
    
    public static Map<String, Integer> ubicaciones ;
    public int[][] mapa = {
        {0, 150, 300, 450, 600},
        {150, 0, 200, 350, 500},
        {300, 200, 0, 250, 400},
        {450, 350, 250, 0, 150},
        {600, 500, 400, 150, 0}
    }
    public int obtenerDistancia(String origen, String destino) {
        int distancia = mapa[ubicaciones.get(origen)][ubicaciones.get(destino)];
        return distancia;
    }
}

