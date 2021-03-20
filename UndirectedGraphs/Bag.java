package UndirectedGraphs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<E> implements Iterable<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public Bag() {
        head = tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (head == null && tail == null) {
            return "[]";
        } else if (head == tail) {
            return "[" + head.toString() + "]";
        } else {
            Node<E> pointer = head;
            String result = "[";
            while (pointer != null) {
                result += pointer.toString() + ", ";
                pointer = pointer.next;
            }

            return result.substring(0, result.length() - 2) + "]";
        }
    }

    public int size() {
        return size;
    }

//    public void clear() {
//        head = tail = null;
//        size = 0;
//    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E data) {
        if (head == null && tail == null) {
            head = tail = new Node<>(data);

        } else {
            Node<E> newNode = new Node<>(data);
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addLast(E data) {
        if (head == null && tail == null) {
            head = tail = new Node<>(data);
        } else {
            Node<E> newNode = new Node<>(data);
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node<E> newNode = new Node<>(data);
            Node<E> pointer = head;
            int counter = 0;
            while (counter < index - 1) {
                pointer = pointer.next;
                counter++;
            }

            newNode.next = pointer.next;
            pointer.next = newNode;
        }
        size++;
    }

//    public void removeFirst() {
//        if (size == 0) throw new NoSuchElementException();
//
//        if (size == 1) {
//            head = tail = null;
//            size = 0;
//        } else {
//            head = head.next;
//            size--;
//        }
//    }

//    public void removeLast() {
//        if (size == 0) throw new NoSuchElementException();
//
//        if (size == 1) {
//            head = tail = null;
//            size = 0;
//        } else {
//            Node<E> pointer = head;
//            while (pointer.next != tail){
//                pointer = pointer.next;
//            }
//
//            tail = pointer;
//            tail.next = null;
//            size--;
//        }
//    }

//    public void remove(int index) {
//        if(index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException();
//
//        if (size == 0) throw new NoSuchElementException();
//
//        if (index == 0) {
//            removeFirst();
//        } else if(index == (size - 1)) {
//            removeLast();
//        } else {
//            Node<E> pointer = head;
//            int i = 0;
//
//            while (i < index - 1){
//
//                pointer = pointer.next;
//                i++;
//            }
//
//            pointer.next = pointer.next.next;
//            size--;
//        }
//    }

//    public E getFirst(){
//        return head.data;
//    }
//
//    public E getLast(){
//        return tail.data;
//    }
//
//    public E get(int index) {
//        if(!(index >= 0 && index < size)) throw new ArrayIndexOutOfBoundsException();
//
//        if (size == 0) throw new NoSuchElementException();
//
//        Node<E> pointer = head;
//        int i = 0;
//
//        while (i < index) {
//
//            pointer = pointer.next;
//            i++;
//        }
//
//        return pointer.data;
//    }

//    public void set(int index,E data){
//        if(!(index >= 0 && index < size)) throw new ArrayIndexOutOfBoundsException();
//
//        if (size == 0) throw new NoSuchElementException();
//
//        Node<E> pointer = head;
//        int i = 0;
//
//        while (i < index) {
//
//            pointer = pointer.next;
//            i++;
//        }
//
//        pointer.data = data;
//    }

//    public boolean contains(E data){
//        Node<E> pointer = head;
//        while (pointer != null){
//            if(pointer.data.equals(data)){
//                return true;
//            }
//            pointer = pointer.next;
//        }
//        return false;
//    }

//    public int indexOf(E data){
//        int i = 0;
//        Node<E> pointer = head;
//        while (pointer != null){
//            if(pointer.data.equals(data)){
//                return i;
//            }
//            i++;
//            pointer = pointer.next;
//        }
//
//        return -1;
//    }
//
//    public int  lastIndexOf(E data){
//        int i = 0;
//        int index = -1;
//        Node<E> pointer = head;
//        while (pointer != null){
//            if(pointer.data.equals(data)){
//                index = i;
//            }
//            i++;
//            pointer = pointer.next;
//        }
//
//
//        return index;
//    }

    @Override
    public Iterator<E> iterator() {
        return new OurLinkedListIterator();
    }

    class OurLinkedListIterator implements Iterator<E>{
        private Node<E> pointer = head;
        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        @Override
        public E next() {
            E data = pointer.data;
            pointer = pointer.next;
            return data;
        }
    }

    class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
