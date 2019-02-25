public class Main {

    public static void main(String[] args) {
        System.out.println("Start");

        //Integer[][] matrix = new Integer[][]{ {1,2,3}, {4,5,6}, {7,8,9}};
        //DataPrinter.printMatrix(matrix);

        //Float[][] floatMatrix = DataGenerator.generateMatrix(5,3,0f,5f);
        //DataPrinter.printMatrix(floatMatrix);

        //Integer[][] matrix = new Integer[][]{{5,3,9},{7,4,4},{1,7,8}};
        long start = System.currentTimeMillis();
        Integer[][] matrix = DataGenerator.generateMatrix(10,10, 1, 10, true);
        //Float[][] matrix = DataGenerator.generateMatrix(10,10, 1f, 10f);
        long generation = System.currentTimeMillis() - start;
        //DataPrinter.printMatrix(matrix);
        System.out.println("Makespan = " + MakeSpanCalculator.calculateMakeSpan(matrix));

        long a = System.currentTimeMillis();
        MatrixManipulator.permuteColumn(matrix, 0,1);
        long spanA = System.currentTimeMillis();
        spanA -= a;
        //DataPrinter.printMatrix(matrix);
        int makeSpan = MakeSpanCalculator.calculateMakeSpan(matrix);
        //float makeSpan = MakeSpanCalculator.calculateMakeSpan(matrix);
        System.out.println("Makespan = " + makeSpan);

        long b = System.currentTimeMillis();
        MatrixManipulator.permuteRow(matrix, 0,1);
        long spanB = System.currentTimeMillis();
        spanB -= b;
        //DataPrinter.printMatrix(matrix);

        System.out.println("Generation took " + generation + " ms");
        System.out.println("Permute column : " + spanA + " ms.");
        System.out.println("Permute row : " + spanB + " ms.");


        /*
        Integer[][] matrix = new Integer[][]{{5,3,9},{7,4,4},{1,7,8}};
        int makeSpan = MakeSpanCalculator.calculateMakeSpan(matrix);
        System.out.println("MakeSpan = " + makeSpan);
        */

        MainApplication<Integer> mainApplication = new MainApplication<>();

        startup(new Runnable() {
            @Override
            public void run() {
                try {
                    mainApplication.start(new ChainBoxGraph(matrix, 1600, 900, makeSpan));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static void startup(Runnable r) {
        com.sun.javafx.application.PlatformImpl.startup(r);
    }

}
