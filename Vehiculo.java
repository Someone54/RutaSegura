public class Vehiculo {
    private String matricula;
    private String conductor;
    private String marca;
    private String tipo;
    private String ubicacion;
    private int carga;
    private int tarifa;

    // Constructor
    public Vehiculo(String ultMatricula,String conductor, String marca, String tipo, String ubicacion, int carga, int tarifa) {
        this.matricula = generarPlaca(ultMatricula);
        this.conductor = conductor;
        this.marca = marca;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.carga = carga;
        this.tarifa = tarifa;
    }
    
    // Getters para los atributos
    public String getMatricula(){ return matricula; }
    public String getConductor(){ return conductor; }
    public String getMarca(){ return marca; }
    public String getTipo(){ return tipo; }
    public String getUbicacion(){ return ubicacion; }
    public int getCarga(){ return carga; }
    public int getTarifa(){ return tarifa; }
    
    
    // Calcular el flete
    public int calcularFlete(int distancia) {
        double trafico = 1 + (Math.random() * 0.2);
        int total = (int) ((distancia * this.tarifa) * trafico); 
        return total;
    }

    //Generamos una placa nueva para el vehiculo dependiendo de las que ya se han segistrado anteriormente
    public static String generarPlaca(String ultimaPlaca) {
        String nuevaPlaca = "";
        if (ultimaPlaca == null || "0".equals(ultimaPlaca)) {
            nuevaPlaca = "AAA000";
        }else{
            String letras = ultimaPlaca.substring(0, 3).toUpperCase();
            int numeros = Integer.parseInt(ultimaPlaca.substring(3));
            numeros++;
            if (numeros > 999) {
                numeros = 0;
                letras = incrementarLetras(letras);
            }nuevaPlaca = letras + String.format("%03d", numeros);
        }
        return nuevaPlaca;
    }

    //Pasamos a mayuscula las letras de la placa
    private static String incrementarLetras(String letras) {
        char[] c = letras.toCharArray();
        for (int i = 2; i >= 0; i--) {
            if (c[i] < 'Z') {
                c[i]++;
                return String.valueOf(c);
            }
            c[i] = 'A';
        }
        return "LIMITE_ALCANZADO"; 
    }
}
