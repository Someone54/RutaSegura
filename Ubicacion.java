import java.util.Random;
public class Ubicacion {
    public int[][] mapa = new int[10][10];
    public Ubicacion(){
        Random random = new Random();
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = random.nextInt(1,3);
            }
        }
    }

}
