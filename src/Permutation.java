public class Permutation<T> {

    private int[] state = null;
    private int   position = 0;
    private T[]   array = null;

    public Permutation(T[] data) {
        this.array = data;
        this.state = new int[data.length];
        this.position = 0;
    }

    public boolean next() {
        if (position==0) {position++; return true;}
        while(position<state.length) {
            if (state[position]<position) {
                int index = (position%2)==0 ? 0 : state[position];
                swap(position,index);
                state[position]++; position=1;
                return true;
            } else {
                state[position]=0;
                position++;
            }
        }
        return false;
    }

    private void swap(int i, int j) {
        T tmp = array[i];
        array[i]=array[j];
        array[j]=tmp;
    }
}