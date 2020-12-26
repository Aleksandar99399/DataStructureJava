package implementations;

import interfaces.AbstractTree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {

    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key) {

        this.key = key;
        this.parent = null;
        this.children = new ArrayList<>();

    }

    public Tree() {
        this.children = new ArrayList<>();
    }


    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;

    }

    @Override
    public String getAsString() {
        StringBuilder builder = new StringBuilder();

        traverseTreeWithRecurrence(builder, 0, this);
        return builder.toString().trim();
    }

    private String getPadding(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private void traverseTreeWithRecurrence(StringBuilder builder,
                                            int indent, Tree<E> tree) {
        builder
                .append(this.getPadding(indent))
                .append(tree.getKey())
                .append(System.lineSeparator());

        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrence(builder, indent + 2, child);

        }

    }

    @Override
    public List<E> getLeafKeys() {
        return traverseWithBFS().stream()
                .filter(tree -> tree.children.size() == 0)
                .map(Tree::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<E> getMiddleKeys() {
        List<Tree<E>> allNodes = new ArrayList<>();
        traverseTreeWithRecurrence(allNodes, this);

        return allNodes.stream()
                .filter(tree -> tree.parent != null && tree.children.size() > 0)
                .map(Tree::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {

        List<Tree<E>> deepestLeftMostNode = new ArrayList<>();
        int[] maxPath = new int[1];

        deepestLeftMostNode.add(new Tree<E>());
        int max = 0;

        findDeepestNodeDFS(deepestLeftMostNode, maxPath, this, max);


        // for (Tree<E> tree : trees) {
        //     if (tree.isLeaf()){
        //         int currentPath= getStepsFromLeafToRootCount(tree);
        //         if (currentPath> maxPath){
        //             maxPath=currentPath;
        //             deepestLeftMostNode=tree;
        //         }
        //     }
        // }

        return deepestLeftMostNode.get(0);
    }

    private void findDeepestNodeDFS(List<Tree<E>> deepestLeftMostNode, int[] maxPath, Tree<E> tree, int max) {

        if (max > maxPath[0]) {
            maxPath[0] = max;
            deepestLeftMostNode.set(0, tree);
        }



        for (Tree<E> child : tree.children) {

            findDeepestNodeDFS(deepestLeftMostNode, maxPath, child, max+1);
        }


    }

    private int getStepsFromLeafToRootCount(Tree<E> tree) {
        int counter = 0;

        Tree<E> current = tree;
        while (current.parent != null) {
            counter++;
            current = current.parent;
        }
        return counter;
    }

    private boolean isLeaf() {
        return this.parent != null && this.children.size() == 0;
    }

    @Override
    public List<E> getLongestPath() {
        List<E> saveLongestPath=new ArrayList<>();

        Tree<E>current=getDeepestLeftmostNode();

        while (current!=null){
            saveLongestPath.add(current.key);
            current=current.parent;
        }

        Collections.reverse(saveLongestPath);
        return saveLongestPath;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<E>saveList=new ArrayList<>();
        List<List<E>>tokens=new ArrayList<>();
        int []size=new int[1];
        //stack.add(this.key);
        traverseTreeWithRecurrence(saveList, this, sum, tokens, size);


        return tokens;
    }

    private void traverseTreeWithRecurrence(List<E> collection, Tree<E> tree, int sum, List<List<E>>tokens, int[]size) {
        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrence(collection, child,sum,tokens,size);
        }
        int child= (int) tree.key;
        Tree<E>parent=tree.parent;
        if (tree.parent==null){
            return;
        }
        if (parent.parent != null) {
            int firstParent= (int) tree.parent.key;
            int secondParent = (int) parent.parent.key;
            if (child + firstParent + secondParent == sum) {

                collection.add(parent.parent.key);
                collection.add(tree.parent.key);
                collection.add(tree.key);

                tokens.add(new ArrayList<>(collection));
            }
            collection.clear();
        }
    }

    public List<Tree<E>>/*String*/ traverseWithBFS() {
        StringBuilder builder = new StringBuilder();
        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(this);

        int indent = 0;

        List<Tree<E>> allNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();
            allNodes.add(tree);

            //if (tree.getParent() != null && tree.getParent().getKey().equals(this.getKey())) {
            //    indent = 2;
            //}else if (tree.children.size() == 0) {
            //    indent = 4;
            //}

            //builder.append(getPadding(indent))
            //        .append(tree.getKey())
            //        .append(System.lineSeparator());

            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }

        }
        return allNodes;
        //return builder.toString().trim();
    }
    private void traverseTreeWithRecurrence(List<Tree<E>> collection, Tree<E> tree) {
        for (Tree<E> child : tree.children) {
            traverseTreeWithRecurrence(collection, child);
        }
        collection.add(tree);

    }

   //private int getSum(Tree<E>tree,int sum){
   //    if (tree==null){
   //        return 0;
   //    }

   //}

    private int doDfs(Tree<E> node,int target, int current,List<Tree<E>>roots) {
        if (node==null){
            return 0;
        }
        current= (int) node.key;

        for (Tree<E> child : node.children) {
            current+=doDfs(child,target,current,roots);
        }
        if (current==target){
            roots.add(node);
        }
        return current;

    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>>roots=new ArrayList<>();

        doDfs(this,sum,0,roots);

        return roots;

    }
}



