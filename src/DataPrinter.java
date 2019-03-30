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

    static void drawSheme(Integer[][] matrix, int makeSpan){
        drawSheme(matrix, makeSpan, 400, 225, new StringBuilder().append("FlowGraph - MakeSpan = ").append(makeSpan).toString());
    }

    static void drawSheme(Integer[][] matrix, int makeSpan, String title){
        drawSheme(matrix, makeSpan, 400, 225, title);
    }


    static void drawSheme(Integer[][] matrix, int makeSpan, int width, int height, String title){
        com.sun.javafx.application.PlatformImpl.startup(new Runnable() {
            @Override
            public void run() {
                try {
                    MainApplication<Integer> mainApplication = new MainApplication<>();
                    mainApplication.start(new ChainBoxGraph(matrix, width, height, makeSpan, title));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
