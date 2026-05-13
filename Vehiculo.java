public class Vehiculo {
    private String matricula;
    private String conductor;
    private String marca;
    private String tipo;
    private String ubicacion;
    private int carga;
    private int tarifa;
    public Vehiculo(String matricula, String conductor, String marca, String tipo, String ubicacion, int tarifa) {
        this.matricula = matricula;
        this.conductor = conductor;
        this.marca = marca;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.tarifa = tarifa;
    }
}
