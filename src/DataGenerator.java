import java.util.Random;

public class DataGenerator {

    static Float[][] generateMatrix(int row, int column, float minValue, float maxValue){
        Float[][] matrix = new Float[row][column];
        Random random = new Random();
        float amplitude = maxValue - minValue;
        for (int i=0; i < row; i++){
            for (int j=0; j < column ; j++){
                matrix[i][j] = random.nextFloat() * amplitude + minValue;
            }
        }
        return matrix;
    }

    static Integer[][] generateMatrix(int row, int column, int minValue, int maxValue, boolean isMaxValueIncluded){
        Integer[][] matrix = new Integer[row][column];
        Random random = new Random();
        int amplitude = maxValue - minValue;
        if (isMaxValueIncluded)
            amplitude++;
        for (int i=0; i < row; i++){
            for (int j=0; j < column ; j++){
                matrix[i][j] = random.nextInt(amplitude) + minValue;
            }
        }
        return matrix;
    }


}
