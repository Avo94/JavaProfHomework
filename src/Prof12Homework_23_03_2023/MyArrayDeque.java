package Prof12Homework_23_03_2023;

import java.util.NoSuchElementException;

public class MyArrayDeque implements MyDeque {

    private Integer[] elements;
    private int numberOfElements;
    private int indexHead = -1;
    private int indexTail;

    /*
    indexHead = 0;
    _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    0                           10
    addToHead(2);  // add by indexHead and indexHead++;
    addToHead(4);
    addToHead(6);

     2 4 6 _ _ _ _ _ _ _ _ _ _ _ _
     0                           10

    indexTail = elements.length -1;
    addToTail(3);  indexTail--
    addToTail(5);
    addToTail(1);

    2 4 6 7 4 3 9 8 1 5 3
    0                           10

    --------------------->>>>>>
    ////  -> 9 8 1 5 3 2 4 6 7 4 3<-

     */

    public MyArrayDeque(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Integer[initialCapacity];
            indexTail = elements.length;
        }

    }

    private void expandArray() {
        Integer[] extendedArray = new Integer[elements.length + (elements.length / 2)];
        for (int i = 0; i < elements.length; i++) {
            if (i <= indexHead)
                extendedArray[i] = elements[i];
            if (i >= indexTail)
                extendedArray[(extendedArray.length - elements.length) + i] = elements[i];
        }
        indexTail = (extendedArray.length - elements.length) + indexTail;
        this.elements = extendedArray;
    }

    public int elementsCount() {
        return numberOfElements;
    }

    public int arraySize() {
        return elements.length;
    }

    @Override
    public void addToHead(Integer element) {
        if (numberOfElements == elements.length)
            expandArray();
        indexHead++;
        elements[indexHead] = element;
        numberOfElements++;
    }

    @Override
    public void addToTail(Integer element) {
        if (numberOfElements == elements.length)
            expandArray();
        indexTail--;
        elements[indexTail] = element;
        numberOfElements++;
    }

    @Override
    public Integer removeHead() {
        if (numberOfElements == 0) {
            throw new NoSuchElementException();
        }
        Integer integer = peekHead();
        elements[indexHead] = null;
        indexHead--;
        numberOfElements--;

        return integer;
    }

    @Override
    public Integer removeTail() {
        if (numberOfElements == 0) {
            throw new NoSuchElementException();
        }
        Integer integer = peekTail();
        elements[indexTail] = null;
        indexTail++;
        numberOfElements--;

        return integer;
    }

    @Override
    public Integer peekHead() {
        if (numberOfElements == 0) {
            throw new NoSuchElementException();
        }

        return elements[indexHead];
    }

    @Override
    public Integer peekTail() {
        if (numberOfElements == 0) {
            throw new NoSuchElementException();
        }

        return elements[indexTail];
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public boolean contains(Integer element) {
        if (numberOfElements == 0) {
            return false;
        }

        boolean result = false;
        for (Integer integer : elements) {
            if (element.equals(integer)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                if (i <= indexHead) result.append(elements[indexHead - i]).append(", ");
                else result.append(elements[elements.length - 1 + indexTail - i]).append(", ");
            }
        }
        result = new StringBuilder(result.substring(0, result.length() - 2));
        return result.toString();
    }
}