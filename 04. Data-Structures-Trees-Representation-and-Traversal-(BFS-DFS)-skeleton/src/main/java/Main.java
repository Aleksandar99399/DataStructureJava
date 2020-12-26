import implementations.Tree;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Tree<Integer> tree=new Tree<>(7,
                new Tree<>(19,
                        new Tree<>(1),
                        new Tree<>(12),
                        new Tree<>(31)),
                new Tree<>(21),
                new Tree<>(14,
                        new Tree<>(23),
                        new Tree<>(6))
        );
        List<Integer>result=new ArrayList<>();
        for (Integer integer : orderedDfs(tree, result)) {
            System.out.println(integer);
        }
        ;

    }

    public static List<Integer>orderedBfs(Tree<Integer>tree){
        List<Integer>result=new ArrayList<>();
        Deque<Tree<Integer>>queue=new ArrayDeque<>();

        queue.offer(tree);

        while (!queue.isEmpty()){
            Tree<Integer>curr=queue.poll();

            result.add(curr.getValue());

            for (Tree<Integer> child : curr.getChildren()) {
                queue.offer(child);
            }

        }


        return result;
    }

    public static List<Integer>orderedDfs(Tree<Integer>tree,List<Integer>result){

        for (Tree<Integer> child : tree.getChildren()) {
            orderedDfs(child,result);
        }

        result.add(tree.getValue());

        return result;
    }
}
