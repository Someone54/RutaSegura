// Excepción verificada para búsquedas fallidas
class VehiculoNoEncontradoException extends Exception {
    public VehiculoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

// Excepción de tiempo de ejecución para errores de mapa
class UbicacionInvalidaException extends RuntimeException {
    public UbicacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}