
import java.util.*;
import java.util.function.Consumer;

public class Main {
    private static class HeapMax implements Iterable<Integer> {
        List<Integer> queue;

        public HeapMax() {
            queue = new ArrayList<>();
        }

        //max
        public void offer(Integer item) {
            this.queue.add(item);
            heapifyUp();
        }

        public void heapifyUp() {
            int index = queue.size() - 1;
            int parentIndex = parentIndex(index);

            while (index > 0 && isGreater(index, parentIndex)) {
                Collections.swap(queue, index, parentIndex);
            }

        }

        public  boolean isGreater(int currIndex, int parentIndex) {
            return this.queue.get(currIndex) > this.queue.get(parentIndex);
        }

        public  int parentIndex(int index) {
            return (index - 1) / 2;
        }

        public Integer getAt(int index) {
            return this.queue.get(index);
        }


        @Override
        public Iterator<Integer> iterator() {
            return  new Iterator<Integer>() {
                int index=0;
                @Override
                public boolean hasNext() {
                    return index<queue.size();
                }

                @Override
                public Integer next() {
                    return queue.get(index++);
                }
            };
        }

        private class Hep implements Iterator<java.lang.Integer>{

            int index = 0;

            @Override
            public boolean hasNext() {
                return this.index<queue.size();
            }

            @Override
            public java.lang.Integer next() {
                return queue.get(index++);
            }
        }

    }

    public static void main(String[] args) {

        HeapMax heapMax=new HeapMax();
        heapMax.offer(10);

        heapMax.offer(20);
        heapMax.offer(15);
        for (Integer max : heapMax) {
            System.out.println(max);
        }

    }
}
