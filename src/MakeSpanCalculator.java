public class MakeSpanCalculator {

    static float calculateMakeSpan(Float[][] matrix){
        float makespan = matrix[0][0];
        int i = 0;
        int j = 0;
        while (i < matrix.length -1 || j < matrix[0].length -1){
            if(i == matrix.length -1){
                makespan += matrix[i][j+1];
                j++;
            }
            else if ( j == matrix[0].length -1){
                makespan += matrix[i+1][j];
                i++;
            }
            else {
                if (matrix[i+1][j] < matrix[i][j+1]){
                    makespan += matrix[i][j+1];
                    j++;
                }
                else{
                    makespan += matrix[i+1][j];
                    i++;
                }
            }
        }
        return makespan;
    }

    static int calculateMakeSpan(Integer[][] matrix){
        int makespan = matrix[0][0];
        int i = 0;
        int j = 0;
        while (i < matrix.length -1 || j < matrix[0].length -1){
            if(i == matrix.length -1){
                makespan += matrix[i][j+1];
                System.out.println("A " + matrix[i][j+1]); // DEBUG
                j++;
            }
            else if ( j == matrix[0].length -1){
                makespan += matrix[i+1][j];
                System.out.println("B " + matrix[i+1][j]); // DEBUG
                i++;
            }
            else {
                if (matrix[i+1][j] < matrix[i][j+1]){
                    makespan += matrix[i][j+1];
                    System.out.println("C " + matrix[i][j+1]);
                    j++;
                }
                else{
                    makespan += matrix[i+1][j];
                    System.out.println("D " + matrix[i+1][j]);
                    i++;
                }
            }
        }
        return makespan;
    }
}
