package implementations;
import org.junit.Test;



public class ArrayDequeTest {

    @Test
    public void testArrayDeque(){
        ArrayDeque<Integer> deque=new ArrayDeque<>();

        deque.addFirst(1);
        deque.remove(0);


        for (Integer integer : deque) {
            System.out.println(integer);
        }


    }


}

