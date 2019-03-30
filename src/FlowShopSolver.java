import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public Integer[][] solveBrutForce(){
        return solveBrutForceInteger(false, false);
    }

    public Integer[][] solveBrutForceInteger(boolean consoleLog, boolean drawAllScheme){
        Integer[] list;

        list = new Integer[integerMatrix.length];
        for (int i = 0; i<list.length ; i++ ){
            list[i] = i;
        }
        Permutation<Integer> permutation = new Permutation<>(list);
        int count = 0;

        Integer[][] bestSolution = null;
        int tmpMakeSpan = 0 ;
        int currentMinMakespan = Integer.MAX_VALUE;
        Integer[][] tmp = new Integer[integerMatrix.length][integerMatrix[0].length];

        while (permutation.next()) {
            for (int i = 0; i < integerMatrix.length; i++) {
                tmp[i] = integerMatrix[list[i]];
            }
            tmpMakeSpan = MakeSpanCalculator.calculateMakespan(tmp);

            // LOGS
            if (consoleLog) {
                System.out.println(Arrays.toString(list));
                DataPrinter.printMatrix(tmp);
                System.out.println("Makespan = " + tmpMakeSpan);
                System.out.println();
            }

            if (tmpMakeSpan < currentMinMakespan) {
                bestSolution = tmp;
                currentMinMakespan = tmpMakeSpan;
            }

            String listString = Arrays.toString(list);
            int finalTmpMakeSpan = tmpMakeSpan;
            Integer[][] finalTmp = new Integer[tmp.length][tmp[0].length];
            for (int x = 0; x < tmp.length; x++) {
                finalTmp[x] = Arrays.copyOf(tmp[x], tmp[x].length);
            }
            if (drawAllScheme) {
                DataPrinter.drawSheme(finalTmp, finalTmpMakeSpan,  new StringBuilder().append("[GREEDY] FlowGraph - MakeSpan = ").append(finalTmpMakeSpan).toString());
            }
        }

        return bestSolution;
    }

    /*
    // TODO : Code à clean
    public void solveBrutForce(boolean consoleLog, boolean drawScheme){
        Integer[] list;

        if (isInteger){
            list = new Integer[integerMatrix.length];
        }
        else {
            list = new Integer[floatMatrix.length];
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
                for (int i = 0; i < integerMatrix.length; i++) {
                    tmp[i] = integerMatrix[list[i]];
                }
                tmpMakeSpan = MakeSpanCalculator.calculateMakespan(tmp);

                // LOGS
                if (consoleLog) {
                    System.out.println(Arrays.toString(list));
                    DataPrinter.printMatrix(tmp);
                    System.out.println("Makespan = " + tmpMakeSpan);
                    System.out.println();
                }

                if (tmpMakeSpan < currentMinMakespan) {
                    bestSolution = tmp;
                    currentMinMakespan = tmpMakeSpan;
                }

                String listString = Arrays.toString(list);
                int finalTmpMakeSpan = tmpMakeSpan;
                Integer[][] finalTmp = new Integer[tmp.length][tmp[0].length];
                for (int x = 0; x < tmp.length; x++) {
                    finalTmp[x] = Arrays.copyOf(tmp[x], tmp[x].length);
                }
                if (drawScheme) {
                    DataPrinter.drawSheme(finalTmp, finalTmpMakeSpan,  new StringBuilder().append("[GREEDY] FlowGraph - MakeSpan = ").append(finalTmpMakeSpan).toString());
                }
            }
        }
        else {
            Float[][] tmp = new Float[floatMatrix.length][floatMatrix[0].length];
            while (permutation.next()) {
                // TODO
            }
        }
    }
    */

    public Integer[][] solveByLineAppendInteger(){
        List<Integer[]> allLines = new ArrayList<>();
        List<Integer[]> solution = new ArrayList<>();

        allLines.addAll(Arrays.asList(integerMatrix));

        if (allLines.size() <= 0) {
            System.out.println("allLines list size = 0 in FlowShopSolver.solveByLineAppendInteger()");
            System.out.println("function output is null");
            return null;
        }

        // Choose the shortest starter
        Integer[] bestStarter = allLines.get(0);
        for (Integer[] i : allLines){
            if(i[0] < bestStarter[0]){
                bestStarter = i;
            }
        }
        solution.add(bestStarter);
        allLines.remove(bestStarter);

        while (allLines.size() > 0){
            Integer[] lastSolutionLine = solution.get(solution.size()-1);
            Integer[] bestLeftLine = allLines.get(0);
            int bestLeftLineMakeSpan = Integer.MAX_VALUE;
            for (Integer[] i: allLines) {
                int tmpMakeSpan = MakeSpanCalculator.calculateMakespan(lastSolutionLine, i);
                if (tmpMakeSpan <= bestLeftLineMakeSpan){
                    bestLeftLine = i;
                    bestLeftLineMakeSpan = tmpMakeSpan;
                }
            }
            solution.add(bestLeftLine);
            allLines.remove(bestLeftLine);
        }

        // Transform list to array
        Integer[][] solutionMatrix = new Integer[solution.size()][solution.get(0).length];
        for (int i = 0 ; i< solution.size() ; i++){
            solutionMatrix[i] = solution.get(i);
        }
        return solutionMatrix;
    }


    public Integer[][] solveByLineInsertionInteger(){
        List<Integer[]> allLines = new ArrayList<>();
        List<Integer[]> tmpSolution = new ArrayList<>();
        List<Integer[]> bestSolution = new ArrayList<>();

        allLines.addAll(Arrays.asList(integerMatrix));

        if (allLines.size() <= 0) {
            System.out.println("allLines list size = 0 in FlowShopSolver.solveByLineAppendInteger()");
            System.out.println("function output is null");
            return null;
        }

        // Add arbitrary the first line
        tmpSolution.add(allLines.remove(0));

        while (allLines.size() > 0){
            Integer[] lineToInsert = allLines.remove(0);
            tmpSolution.add(0, lineToInsert);
            bestSolution = tmpSolution;
            int bestMakespan = MakeSpanCalculator.calculateMakespan(bestSolution);
            for (int i = 0; i < tmpSolution.size()-1 ; i++){
                MatrixManipulator.permuteRow(tmpSolution, i, i+1);
                int tmpMakespan = MakeSpanCalculator.calculateMakespan(tmpSolution);
                if (tmpMakespan < bestMakespan){
                    bestMakespan = tmpMakespan;
                    bestSolution = tmpSolution;
                }
            }

        }

        // Transform list to array
        Integer[][] solutionMatrix = new Integer[bestSolution.size()][bestSolution.get(0).length];
        for (int i = 0 ; i< bestSolution.size() ; i++){
            solutionMatrix[i] = bestSolution.get(i);
        }
        return solutionMatrix;
    }
}
