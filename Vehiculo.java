public class Vehiculo {
    private String matricula;
    private String conductor;
    private String marca;
    private String tipo;
    private int carga;
    private int tarifa;
    public Vehiculo(String matricula, String conductor, String marca, String tipo, int tarifa) {
        this.matricula = matricula;
        this.conductor = conductor;
        this.marca = marca;
        this.tipo = tipo;
        this.tarifa = tarifa;
    }
    public int calcularFlete(int distancia, int tarifa) {
        double trafico = 1 + (Math.random() * 0.2);
        int total = (distancia * tarifa) * trafico; 
        return total;
    }
}
