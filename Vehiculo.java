public class Vehiculo {
    private String matricula;
    private String conductor;
    private String marca;
    private String tipo;
    private int carga;
    private int tarifa;

    // Constructor
    public Vehiculo(String matricula, String conductor, String marca, String tipo, int carga, int tarifa) {
        this.matricula = matricula;
        this.conductor = conductor;
        this.marca = marca;
        this.tipo = tipo;
        this.carga = carga;
        this.tarifa = tarifa;
    }
    
    // Getters para los atributos
    public String getMatricula(){ return matricula; }
    public String getConductor(){ return conductor; }
    public int getCarga(){ return carga; }
    public int getTarifa(){ return tarifa; }
    public String getMarca(){ return marca; }
    public String getTipo(){ return tipo; }
    
    // Calcular el flete
    public int calcularFlete(int distancia) {
        double trafico = 1 + (Math.random() * 0.2);
        int total = (int) ((distancia * this.tarifa) * trafico); 
        return total;
    }
}
