package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private List<E> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {

        while (index > 0 && isLess(index, getParentIndex(index))) {
            Collections.swap(this.elements, index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private boolean isLess(int childIndex, int parentIndex) {
        return getAt(childIndex).compareTo(getAt(parentIndex)) > 0;

    }

    private E getAt(int index) {
        return this.elements.get(index);
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E peek() {

        ensureNonEmpty();
        return getAt(0);
    }

    private void ensureNonEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException("Illegal cal to peek on empty heap");
        }
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        Collections.swap(this.elements, 0, this.elements.size() - 1);
        E toReturn = this.elements.remove(this.elements.size() - 1);

        this.heapiFyDown();

        return toReturn;
    }

    private void heapiFyDown() {
        int index = 0;
        int swapIndex = this.getLeftChildIndex(index);

        while (swapIndex < this.elements.size()) {

            int rightChildIndex = this.getRightChildIndex(index);
            if (rightChildIndex < this.elements.size()) {
                swapIndex = isLess(swapIndex,rightChildIndex)
                        ? swapIndex : rightChildIndex;
            }

            if (isLess(index,swapIndex)){break;}


            Collections.swap(this.elements, index, swapIndex);
            index=swapIndex;
            swapIndex=getLeftChildIndex(index);
        }
    }

    private E getLeftChild(int index) {
        return this.elements.get(this.getLeftChildIndex(index));
    }

    private E getRightChild(int index) {
        return this.elements.get(this.getRightChildIndex(index));
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
}
