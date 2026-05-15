import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Vehiculo> flota = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Bienvenido a Ruta Segura");
        int opcion = 0;

        //Le damos al usuarios distintas opciones de que quiere relizar en el sistema
        while (opcion != 7) {
            opcion = leerOpcionMenu();

            switch (opcion) {
                case 1:
                    crearVehiculo();
                    break;
                case 2:
                    try {
                        Buscador.buscarVehiculo(flota, scanner);
                    } catch (VehiculoNoEncontradoException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    gestionarDistancia();
                    break;
                case 4:
                    generarArchivo();
                    break;
                case 5:
                    revisarFlota();
                    break;
                case 6:
                    eliminarVehiculo();
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;

                    //Si el usuario ingresa una opción invalida salta un mensje de error
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    //Menu de opciones para el usuario
    public static int leerOpcionMenu() {
        while (true) {
            try {
                System.out.println("______________________________________________________________________________________");
                System.out.println("\n1. Crear | 2. Buscar | 3. Distancia | 4. Generar archivo | 5. Revisar flota | 6. Eliminar vehículo | 7. Salir");
                int num = scanner.nextInt();
                scanner.nextLine();
                return num;
            } catch (InputMismatchException e) {
                System.err.println("Error: ¡Debe ingresar un número entero!");
                scanner.nextLine();
            }
        }
    }

    //Metodo para crear un vehiculo nuevo
    public static void crearVehiculo() {
        String ultPlaca = flota.isEmpty() ? "0" : flota.get(flota.size() - 1).getMatricula();
        String conductor;
        while (true) {
            System.out.print("Nombre del conductor: ");
            conductor = scanner.nextLine().trim();
            if (conductor.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.err.println("Error: El nombre del conductor solo debe contener letras y espacios. Intente nuevamente.");
            }
        }
        
        System.out.print("Marca(Chevrolet, Ford, Mercedes-Benz, Foton, Volkswagen): ");
        String marca=" ";
        while (marca==" ") {
            marca = verificarMarca(scanner.nextLine().trim());
            if (marca.equals(" ")) {
                System.err.println("Marca no válida. Debe ser Chevrolet, Ford, Mercedes-Benz, Foton o Volkswagen. Intente nuevamente.");
            }
        }

        System.out.print("Tipo (Furgon / Camioneta / Camion): ");
        String tipo = " ";
        while (tipo == " ") {
            tipo = verificarTipo(scanner.nextLine().trim());
            if (tipo.equals(" ")) {
                System.err.println("Tipo no válido. Debe ser 'Furgon', 'Camioneta' o 'Camion'. Intente nuevamente.");
            }
        }

        String ubicacion;
        while (true) {
            System.out.println("Ingrese la ubicación actual (Medellin, Bogota, Cali, Cartagena, Barranquilla):");
            ubicacion = scanner.nextLine();
            if (Ubicacion.esUbicacionValida(ubicacion)) {
                ubicacion = Ubicacion.normalizarCiudad(ubicacion);
                break;
            }
            System.err.println("¡Error! La ciudad '" + ubicacion + "' no está en nuestra red de cobertura. Reintente.");
        }

        if (tipo.equalsIgnoreCase("Furgon")) {
            Vehiculo vehiculo = new Furgon(ultPlaca, conductor, marca, ubicacion);
            flota.add(vehiculo);
            System.out.println("Creado con éxito. Placa asignada: " + vehiculo.getMatricula());
        } else if (tipo.equalsIgnoreCase("Camioneta")) {
            Vehiculo vehiculo = new Camioneta(ultPlaca, conductor, marca, ubicacion);
            flota.add(vehiculo);
            System.out.println("Creado con éxito. Placa asignada: " + vehiculo.getMatricula());
        } else if (tipo.equalsIgnoreCase("Camion")) {
            Vehiculo vehiculo = new Camion(ultPlaca, conductor, marca, ubicacion);
            flota.add(vehiculo);
            System.out.println("Creado con éxito. Placa asignada: " + vehiculo.getMatricula());
        } else {
            System.err.println("Tipo no válido. Debe ser 'Furgon', 'Camioneta' o 'Camion'.");
        }
    }

    //Metodo para calcular la distancia entre las dos ciudades ingresadas por el usuario
    public static void gestionarDistancia() {
        try {
            System.out.println("Origen(Medellin, Bogota, Cali, Cartagena, Barranquilla):");
            String origen = scanner.nextLine().trim();
            System.out.println("Destino(Medellin, Bogota, Cali, Cartagena, Barranquilla):");
            String destino = scanner.nextLine().trim();
            int d = Ubicacion.obtenerDistancia(origen, destino);
            System.out.println("Distancia calculada: " + d + " km");
        } catch (UbicacionInvalidaException e) {
            System.err.println("Error de ubicación: " + e.getMessage());
        }
    }
    
    //Metodo para generar el archivo con la información del flete basado en la placa del vehiculo
    public static void generarArchivo() {

        //Si la flota no tiene vehiculos registrados salta un aviso de error
        if (flota.isEmpty()) {
            System.err.println("No hay vehículos registrados para generar el archivo.");
            return;
        }

        //Se ejecuta el try catch pidiendole al usuario ingresar los datos necesarios encaso de que el filtro anterior pase sin problemas
        try {
            System.out.println("Ingresa la placa del vehículo:");
            String placa = scanner.nextLine().trim();
            Vehiculo vehiculo = buscarVehiculoPorPlaca(placa);
            System.out.println("Destino(Medellin, Bogota, Cali, Cartagena, Barranquilla):");
            String destino = scanner.nextLine().trim();

            int distancia = Ubicacion.obtenerDistancia(vehiculo.getUbicacion(), destino);
            int total = vehiculo.calcularFlete(distancia);

            Archivo.generarArchivo(vehiculo, Ubicacion.normalizarCiudad(vehiculo.getUbicacion()), Ubicacion.normalizarCiudad(destino), distancia, total);
            vehiculo.setUbicacion(destino);
            flota.set(buscarVehiculoEnFlota(placa), vehiculo);
            System.out.println("Archivo generado correctamente.");
        } catch (VehiculoNoEncontradoException e) {
            System.err.println(e.getMessage());
        } catch (UbicacionInvalidaException e) {
            System.err.println("Error de ubicación: " + e.getMessage());
        }
    }
    
    //Si la placa ingresara no coincide con ningun vehiculo registrado salta la excepcion de vehiculo no encontrado
    public static Vehiculo buscarVehiculoPorPlaca(String placa) throws VehiculoNoEncontradoException {
        for (Vehiculo vehiculo : flota) {
            if (vehiculo.getMatricula().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }
        throw new VehiculoNoEncontradoException("No se encontró el vehículo con placa " + placa);
    }
    
    //Metodo para buscar la pocicion de un vehiculo en la flota
    public static int buscarVehiculoEnFlota(String placa) throws VehiculoNoEncontradoException {
        for (int i = 0; i < flota.size(); i++) {
            if (flota.get(i).getMatricula().equalsIgnoreCase(placa)) {
                return i;
            }
        }
        throw new VehiculoNoEncontradoException("No se encontró el vehículo con placa " + placa);
    }
    
    //Metodo para imprir toda la informacion de los vehiculos de la flota
    public static void revisarFlota() {
        if (flota.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            System.out.println("Vehículos registrados:");
            for (Vehiculo v : flota) {
                System.out.println("- " + v.getMatricula() + " | Conductor: " + v.getConductor() + " | Marca: " + v.getMarca() + " | Tipo: " + v.getTipo() + " | Ubicación: " + v.getUbicacion());
            }
        }
    }

    public static void eliminarVehiculo() {
        if (flota.isEmpty()) {
            System.out.println("No hay vehículos registrados para eliminar.");
            return;
        }
        try {
            System.out.println("Ingresa la placa del vehículo a eliminar:");
            String placa = scanner.nextLine().trim();
            int index = buscarVehiculoEnFlota(placa);
            flota.remove(index);
            System.out.println("Vehículo con placa " + placa + " eliminado correctamente.");
        } catch (VehiculoNoEncontradoException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String verificarMarca(String marca) {
        String[] marcasValidas = {"Chevrolet", "Ford", "Mercedes-Benz", "Foton", "Volkswagen"};
        for (String m : marcasValidas) {
            if (m.equalsIgnoreCase(marca)) {
                return m;
            }
        }
        return " ";
    }

    public static String verificarTipo(String tipo) {
        String[] tiposValidos = {"Furgon", "Camioneta", "Camion"};
        for (String t : tiposValidos) {
            if (t.equalsIgnoreCase(tipo)) {
                return t;
            }
        }
        return " ";
    }
}
