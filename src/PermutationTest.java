import java.util.Arrays;

public class PermutationTest {

    public static void main(String[] args) {
        Integer[] list = new Integer[4];
        for (int i = 0; i<list.length ; i++ ){
            list[i] = i+1;
        }

        Permutation<Integer> permutation = new Permutation<>(list);
        int count = 0;
        while (permutation.next()){
            System.out.println(Arrays.toString(list));
            count++;
        }

        System.out.println("count " + count);
    }

}
