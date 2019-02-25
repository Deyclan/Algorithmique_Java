public class SolverTest {

    public static void main(String[] args) {
        Integer[][] matrix = new Integer[][]{{5,3,9},{7,4,4},{1,7,8}};
        FlowShopSolver flowShopSolver = new FlowShopSolver(matrix);
        flowShopSolver.solveBrutForce();
        // TODO : Résoudre pbm d'égalité de 2 tâches

    }

}
