package implementations;

import java.util.Iterator;

public class ReversedList<E> implements interfaces.ReversedList<E> {
    private  final int Def_Capacity = 2;
    private int size;

    private Object[] internArr;

    public ReversedList()
    {
        this.internArr = new Object[Def_Capacity];
        this.size = 0;
    }
    @Override
    public void add(E element) {
        CheckCapacity();
        this.internArr[this.size] = element;
        this.size++;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.internArr.length;
    }

    @Override
    public E get(int index) {
        if (this.size <=0 || !(IsValidIndex(index))){
            return null;
        }

        int realIndex = GetRealIndex(index);
        return GetAt(realIndex);
    }

    @Override
    public E removeAt(int index) {

        if(!(IsValidIndex(index)) || this.size <= 0)
        {
            throw new IllegalArgumentException();
        }
        int realIndex = index;
        E element = GetAt(realIndex);
        RemoveElement(realIndex);
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>()  {
            private int current = size -1;
            @Override
            public boolean hasNext() {
                return current>=0;
            }

            @Override
            public E next() {
                return GetAt(current--);
            }
        };
    }

    private void CheckCapacity() {
        if (capacity() == this.size) {
            GrowInnerArray();
        }
    }

    private E GetAt(int realIndex){
        return (E)this.internArr[realIndex];
    }

    private int GetRealIndex(int index){
        return ((this.size -1)-index);
    }

    private void GrowInnerArray(){
        Object[] temp = new Object[(capacity()*2)];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.internArr[i];
        }
        this.internArr = temp;
    }

    private boolean IsValidIndex(int index){
        if(index >= this.size || index < 0){
            return false;
        }
        return true;
    }

    private void RemoveElement(int realIndex){

        for (int i = realIndex; i < this.size; i++) {
            this.internArr[i] = this.internArr[i+1];
        }
        this.internArr[size -1] = null;
        this.size--;
    }
}