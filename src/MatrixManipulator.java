public class MatrixManipulator {

    static <E> void permuteRow(E[][] matrix, int rowIndex, int rowTarget){
        if (rowIndex < 0 || rowIndex > matrix.length){
            System.out.println("Invalid RowIndex");
        }
        if (rowTarget < 0 || rowTarget > matrix.length){
            System.out.println("Invalid RowTarget");
        }

        E[] row = matrix[rowTarget];
        matrix[rowTarget] = matrix[rowIndex];
        matrix[rowIndex] = row;
        System.out.println("Permutated row number " + rowIndex + " with number " + rowTarget);
    }

    static <E> void permuteColumn(E[][] matrix, int colIndex, int colTarget){
        if (colIndex < 0 || colIndex > matrix[0].length){
            System.out.println("Invalid colIndex");
        }
        if (colTarget < 0 || colTarget > matrix[0].length){
            System.out.println("Invalid colTarget");
        }

        for(int i = 0; i<matrix.length ; i++){
            E element = matrix[i][colIndex];
            matrix[i][colIndex] = matrix[i][colTarget];
            matrix[i][colTarget] = element;
        }
        System.out.println("Permutated column number " + colIndex + " with number " + colTarget);
    }

}
