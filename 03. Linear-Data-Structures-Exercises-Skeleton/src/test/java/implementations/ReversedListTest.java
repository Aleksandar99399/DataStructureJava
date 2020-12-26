package implementations;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReversedListTest {
    @Test
    public void ReversedListTest(){
        ReversedList<Integer>list=new ReversedList<>();
        List<Integer>arr=new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.get(0));
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }
}
