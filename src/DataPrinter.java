public class DataPrinter {

    static <E> void printMatrix(E[][] matrix){
        StringBuilder builder = new StringBuilder();
        builder.append("matrix :");
        for (int i = 0 ; i < matrix.length ; i++){
            builder.append("\n");
            for (int j = 0; j < matrix[0].length ; j++){
                builder.append(matrix[i][j]).append("\t");
            }
        }
        builder.append("\n");
        System.out.println(builder.toString());
    }

}
