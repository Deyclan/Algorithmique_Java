public class SolverTest {

    public static void main(String[] args) {
        //Integer[][] matrix = new Integer[][]{{5,3,9},{7,4,4},{1,7,8}};
        //Integer[][] matrix = new Integer[][]{{5,3,9,7},{7,8,4,1},{1,7,8,4}};
        //Integer[][] matrix = new Integer[][]{{9,7},{4,1},{8,4}};

        Integer[][] matrix = DataGenerator.generateMatrix(5, 10, 1, 9, true);
        //Integer[][] matrix = new Integer[][]{{10,8,12,15,8},{8,10,12,9,16}};

        solve(matrix, false);
    }


    /** Résolution en brut force et avec l'algorithme
     *
     * @param matrix
     * @param log
     */
    static void solve(Integer[][] matrix, boolean log){
        FlowShopSolver flowShopSolver = new FlowShopSolver(matrix);

        Integer[][] solutionBrutForce = flowShopSolver.solveBrutForceInteger(false, false);
        int makespanBrutForce = MakeSpanCalculator.calculateMakespan(solutionBrutForce);
        DataPrinter.drawSheme(solutionBrutForce,
                makespanBrutForce,
                scaleScreen(400, matrix[0].length),
                scaleScreen(225, matrix.length),
                new StringBuilder().append("[GREEDY] FlowGraph - MakeSpan = ").append(makespanBrutForce).toString());

        if (log) {
            DataPrinter.printMatrix(solutionBrutForce);
            System.out.println("Makespan = " + makespanBrutForce);
        }

        Integer[][] solutionAppend = flowShopSolver.solveByLineAppendInteger();
        int makespanAppend = MakeSpanCalculator.calculateMakespan(solutionAppend);
        DataPrinter.drawSheme(solutionAppend,
                makespanAppend,
                scaleScreen(400, matrix[0].length),
                scaleScreen(225, matrix.length),
                new StringBuilder().append("[SOLVER APPEND] FlowGraph - MakeSpan = ").append(makespanAppend).toString());

        if (log) {
            DataPrinter.printMatrix(solutionAppend);
            System.out.println("Makespan = " + makespanAppend);
        }

        Integer[][] solutionInsertion = flowShopSolver.solveByLineInsertionInteger();
        int makespanInsertion = MakeSpanCalculator.calculateMakespan(solutionInsertion);
        DataPrinter.drawSheme(solutionInsertion,
                makespanInsertion,
                scaleScreen(400, matrix[0].length),
                scaleScreen(225, matrix.length),
                new StringBuilder().append("[SOLVER INSERT] FlowGraph - MakeSpan = ").append(makespanInsertion).toString());

        if (log) {
            DataPrinter.printMatrix(solutionInsertion);
            System.out.println("Makespan = " + makespanInsertion);
        }
    }


    static int scaleScreen(int intToScale, int valueToScaleWith){
        return intToScale * (int) ((Math.log((double)valueToScaleWith))/(Math.log(2)));
    }
}
