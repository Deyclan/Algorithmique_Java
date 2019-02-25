import java.util.Arrays;

public class FlowShopSolver {

    private Integer[][] integerMatrix;
    private Float[][] floatMatrix;

    private boolean isInteger;

    public FlowShopSolver(Integer[][] matrix){
        this.integerMatrix = matrix;
        this.isInteger = true;
    }

    public FlowShopSolver(Float[][] matrix){
        this.floatMatrix = matrix;
        this.isInteger = false;
    }


    // TODO : Code à clean
    public void solveBrutForce(){
        Integer[] list;
        if (isInteger){
            list = new Integer[integerMatrix[0].length];
        }else {
            list = new Integer[floatMatrix[0].length];
        }

        for (int i = 0; i<list.length ; i++ ){
            list[i] = i;
        }

        Permutation<Integer> permutation = new Permutation<>(list);
        int count = 0;
        if (isInteger) {
            Integer[][] bestSolution;
            int tmpMakeSpan = 0 ;
            int currentMinMakespan = Integer.MAX_VALUE;
            Integer[][] tmp = new Integer[integerMatrix.length][integerMatrix[0].length];
            while (permutation.next()) {
                System.out.println(Arrays.toString(list));
                for (int i = 0 ; i< integerMatrix[0].length ; i++){
                    tmp[i] = integerMatrix[list[i]];
                }
                tmpMakeSpan = MakeSpanCalculator.calculateMakeSpan(tmp);
                DataPrinter.printMatrix(tmp);
                System.out.println("Makespan = " + tmpMakeSpan);
                System.out.println();

                if (tmpMakeSpan < currentMinMakespan){
                    bestSolution = tmp;
                    currentMinMakespan = tmpMakeSpan;
                }

                String listString = Arrays.toString(list);
                int finalTmpMakeSpan = tmpMakeSpan;
                Integer[][] finalTmp = new Integer[tmp.length][tmp[0].length];
                for(int x = 0 ; x < tmp.length ; x++){
                    finalTmp[x] = Arrays.copyOf(tmp[x], tmp[x].length);
                }
                com.sun.javafx.application.PlatformImpl.startup(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            MainApplication<Integer> mainApplication = new MainApplication<>();
                            mainApplication.start(new ChainBoxGraph(finalTmp, 400, 225, finalTmpMakeSpan, new StringBuilder().append("FlowGraph - ").append(listString).append(" - MakeSpan = ").append(finalTmpMakeSpan).toString()));
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }else {
            Float[][] tmp = new Float[floatMatrix.length][floatMatrix[0].length];
            while (permutation.next()) {
                // TODO
            }
        }
    }

}
