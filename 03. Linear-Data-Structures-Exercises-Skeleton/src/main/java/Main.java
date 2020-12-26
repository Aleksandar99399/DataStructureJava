import implementations.ArrayDeque;

public class Main {
    public static void main(String[] args) {

        ArrayDeque<Integer>deque=new ArrayDeque<>();
        deque.push(2);
        deque.push(5);
        System.out.println(deque.peek());
    }
}
