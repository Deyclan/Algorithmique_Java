import java.util.List;

public class MakeSpanCalculator {

    static float calculateMakespan(Float[][] matrix) {
        float makespan = matrix[0][0];
        int i = 0;
        int j = 0;
        while (i < matrix.length - 1 || j < matrix[0].length - 1) {
            if (i == matrix.length - 1) {
                makespan += matrix[i][j + 1];
                j++;
            } else if (j == matrix[0].length - 1) {
                makespan += matrix[i + 1][j];
                i++;
            } else {
                if (matrix[i + 1][j] < matrix[i][j + 1]) {
                    makespan += matrix[i][j + 1];
                    j++;
                } else {
                    makespan += matrix[i + 1][j];
                    i++;
                }
            }
        }
        return makespan;
    }
/*
    static int calculateMakespan(Integer[][] matrix) {
        return calculateMakespan(matrix, false);
    }

    static int calculateMakespan(Integer[][] matrix, boolean log) {
        int makespan = matrix[0][0];
        int i = 0;
        int j = 0;
        while (i < matrix.length - 1 || j < matrix[0].length - 1) {
            if (i == matrix.length - 1) {
                makespan += matrix[i][j + 1];
                if (log)
                    System.out.println("+ " + matrix[i][j + 1] + " right"); // DEBUG
                j++;
            }
            else if (j == matrix[0].length - 1) {
                makespan += matrix[i + 1][j];
                if (log)
                    System.out.println("+ " + matrix[i + 1][j] + " up"); // DEBUG
                i++;
            }
            else {
                if (matrix[i + 1][j] < matrix[i][j + 1]) {
                    makespan += matrix[i][j + 1];
                    if (log)
                        System.out.println("+ " + matrix[i][j + 1] + " right");
                    j++;
                }
                else if (matrix[i + 1][j] > matrix[i][j + 1]) {
                    makespan += matrix[i + 1][j];
                    if (log)
                        System.out.println("+ " + matrix[i + 1][j] + " up"); // DEBUG
                    i++;
                }

                // Cas d'égalité du prochain à prendre
                else {
                    if(j + 2 > matrix[0].length - 1){
                        makespan += matrix[i + 1][j];
                        if (log)
                            System.out.println("+ " + matrix[i + 1][j] + " up"); // DEBUG
                        i++;
                    }
                    else {
                        if (matrix[i + 1][j + 1] <= matrix[i][j + 1 + 1]) {
                            makespan += matrix[i][j + 1];
                            if (log)
                                System.out.println("+ " + matrix[i][j + 1] + " right");
                            j++;
                        }
                        else {
                            makespan += matrix[i + 1][j];
                            if (log)
                                System.out.println("+ " + matrix[i + 1][j] + " up"); // DEBUG
                            i++;
                        }
                    }
                }
            }
        }
        if (log)
            System.out.println("");
        return makespan;
    }
*/
    static int calculateMakespan(Integer[] line1, Integer[] line2) {
        Integer[][] matrix = new Integer[2][line1.length];
        matrix[0] = line1;
        matrix[1] = line2;
        return calculateMakespan(matrix);
    }

    static int calculateMakespan(List<Integer[]> listMatrix) {
        // Transform list to array
        Integer[][] solutionMatrix = new Integer[listMatrix.size()][listMatrix.get(0).length];
        for (int i = 0 ; i< listMatrix.size() ; i++){
            solutionMatrix[i] = listMatrix.get(i);
        }
        return calculateMakespan(solutionMatrix);
    }

    static int calculateMakespan(Integer[][] matrix){
        return calculateMakespan(matrix, false);
    }

    static int calculateMakespan(Integer[][] matrix, boolean log){
        Integer[][] start = new Integer[matrix.length][matrix[0].length];
        Integer[][] stop = new Integer[matrix.length][matrix[0].length];

        start[0][0] = 0;
        stop[0][0] = matrix[0][0];

        // Fill first column
        for (int i = 1 ; i < matrix.length ; i++){
            start[i][0] = stop[i-1][0];
            stop[i][0] = start[i][0] + matrix[i][0];
        }

        // Fill first line
        for (int j = 1 ; j < matrix[0].length ; j++){
            start[0][j] = stop[0][j-1];
            stop[0][j] = start[0][j] + matrix[0][j];
        }

        for(int machineIndex = 1 ; machineIndex < matrix.length ; machineIndex++){
            for(int jobIndex = 1 ; jobIndex < matrix[machineIndex].length ; jobIndex++){
                start[machineIndex][jobIndex] = Math.max(stop[machineIndex - 1][jobIndex], stop[machineIndex][jobIndex - 1]);
                stop[machineIndex][jobIndex] = start[machineIndex][jobIndex] + matrix[machineIndex][jobIndex];
            }
        }

        if (log){
            System.out.println("Start time matrix");
            DataPrinter.printMatrix(start);
            System.out.println("Stop time matrix");
            DataPrinter.printMatrix(stop);
        }

        return stop[stop.length-1][stop[0].length -1];
    }

}
