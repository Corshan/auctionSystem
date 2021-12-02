package utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class ConnectedList<E> implements Iterable<E>{
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public void add(E element){
        Node<E> n = new Node<>();
        n.setContents(element);
        if(tail != null) {
            tail.next = n;
        }
        n.prev = tail;
        if (size() == 0) {
            head = n;
        }
        tail = n;
        size += 1;
    }

    public void addElement(E element){
        Node<E> n = new Node<>();
        n.setContents(element);
        head.prev = head;
        n.next = head;
        head = n;

    }

    public void remove(int index){
        int i = 0;
        Node<E> temp = head;
        temp.prev = null;

        if (size == 1){
            head.prev = null;
            size -= 1;
            return;
        }

        if (index == 0){
            head = temp.next;
            head.prev = null;
            size -= 1;
            return;
        }

        if (index == size - 1){
            tail.prev.next = null;
            tail = tail.prev;
            size -= 1;
            return;
        }

        while (i++ < index && temp != null)
            temp = temp.next;

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size -= 1;
    }

    public void remove(E e){
        Node<E> temp = head;
        temp.prev = null;

        if (head.contents.equals(e)){
            head = temp.next;
            size -= 1;
            return;
        }

        if (temp.next == null){
            tail.prev.next = null;
            tail = tail.prev;
            size -= 1;
            return;
        }

        while (!temp.contents.equals(e))
            temp = temp.next;

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size -= 1;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public E get(int index) {
        Node<E> temp = head;
        E contents = null;
        int i = 0;

        while (temp != null){
            if (i == index){
                contents = temp.contents;
                break;
            }
            temp = temp.next;
            i++;
        }
        if (temp != null) {
            return contents;
        } else {
            return null;
        }
    }

    public void set(int index, E element) {
        Node<E> temp = head;
        int i = 0;

        while (temp != null){
            if (i == index){
                temp.contents = element;
                break;
            }
            temp = temp.next;
            i++;
        }
    }

    /*
    public int size(){
        Node<E> temp = new Node<>();
        int i = 0;
        temp.next = head;

        while (temp != null){
            temp = temp.next;
            i++;
        }

        return i;
    }
    */

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object o){
        boolean found = false;
        Node<E> temp = new Node<>();
        temp.next = head;

        while (temp.next != null){
            temp = temp.next;
            if (o.equals(temp.getContents())) {
                found = true;
                break;
            }
        }

        return found;
    }

    public String listAll(){
        String list = "";
        Node<E> temp = new Node<>();
        temp.next = head;

        while (temp.next != null){
            temp = temp.next;
            list = list + temp.getContents().toString() + "\n";
        }
        return list;
    }

    public void mergeSort(Comparator<E> comparator){
        if (size > 1){

            int x = size/2, y = size - x;
            ConnectedList<E> list1 = new ConnectedList<>();
            ConnectedList<E> list2 = new ConnectedList<>();

            int i;
            for(i = 0; i < x; i++){
                list1.add(get(i));
            }
            for(int i2 = 0; i2 < y; i2++, i++){
                list2.add(get(i));
            }

            list1.mergeSort(comparator);
            list2.mergeSort(comparator);

            i = 0;
            int xai = 0, yai = 0;

            while (xai < list1.size && yai < list2.size){
                set(i++,comparator.compare(list1.get(xai),list2.get(yai))< 0 ? list1.get(xai++) : list2.get(yai++));
            }

            while (xai< list1.size){
                set(i++, list1.get(xai++));
            }

            while (yai< list2.size){
                set(i++, list2.get(yai++));
            }
        }
    }



    @Override
    public Iterator<E> iterator() {
        return new ConnectedIterator<>(head);
    }

    public static class Node<N>{
        protected Node<N> next = null;
        protected Node<N> prev = null;
        private N contents;

        public Node<N> getNext() {
            return next;
        }

        public void setNext(Node<N> next) {
            this.next = next;
        }

        public Node<N> getPrev() {
            return prev;
        }

        public void setPrev(Node<N> prev) {
            this.prev = prev;
        }

        public N getContents() {
            return contents;
        }

        public void setContents(N contents) {
            this.contents = contents;
        }
    }
}
