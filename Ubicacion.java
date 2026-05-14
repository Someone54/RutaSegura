import java.util.Map;

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

    public static String normalizarCiudad(String ciudad) {
        if (ciudad == null) return null;
        String limpia = ciudad.trim();
        if (limpia.isEmpty()) return limpia;
        return limpia.substring(0, 1).toUpperCase() + limpia.substring(1).toLowerCase();
    }

    public static int obtenerDistancia(String origen, String destino) {
        String origenN = normalizarCiudad(origen);
        String destinoN = normalizarCiudad(destino);

        if (!ubicaciones.containsKey(origenN) || !ubicaciones.containsKey(destinoN)) {
            throw new UbicacionInvalidaException("Una o ambas ciudades no están registradas en el sistema.");
        }

        return mapa[ubicaciones.get(origenN)][ubicaciones.get(destinoN)];
    }

    public static boolean esUbicacionValida(String ciudad) {
        if (ciudad == null || ciudad.isEmpty()) return false;
        String ciudadFormateada = normalizarCiudad(ciudad);
        return ubicaciones.containsKey(ciudadFormateada);
    }
}

