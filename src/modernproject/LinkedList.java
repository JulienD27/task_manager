package modernproject;

import java.io.*;
import java.util.*;

/*
 * Linked List class that will be used for tasks and events. Organize data
 * for saving files and displaying
 * Sorted Doubly Linked List
 */
public class LinkedList<T extends Comparable<T>> {
    Node<T> head;
    Node<T> tail;

    //Constructor class
    //head and tail are sentinels which means that they can be easily found, don't have to deal
    // with null pointers
    //initiates the head and tail nodes to become the start and end of the doubly linked list
    public LinkedList() {
       this.head = new Node<T>(null, null, null, true);
       this.tail = new Node<T>(null, null, head, true);
       head.setNext(tail); //set next to tail
    }

    //counts the amount of nodes inside the list and returns the amount
    public int getSize() {
        Node<T> current = head.next;
        int counter = 0;
        while(!current.sentinel){
            counter++;
            current = current.next;
        }
        return counter;
    }

    // sorts incoming node and inserts
    //inserts the incoming abstract data into a node and making that
    // node apart of the list while inserting it in alphabetical order
    public boolean insert(T data) {
        if(checkDuplicate(data)) {
            return false;
        }
        Node<T> newNode = new Node<T>(data, null, null, false);
        if(head.next == tail) {
            head.setNext(newNode);
            tail.setPrev(newNode);
            newNode.setNext(tail);
            newNode.setPrev(head);
            return true;
        }else {
            Node<T> pointer = head.next;
            boolean inserted = false;
            while(!pointer.sentinel) {
                if(pointer.data.compareTo(data) >= 0) {
                    newNode.setPrev(pointer.prev);
                    newNode.setNext(pointer);
                    pointer.prev.setNext(newNode);
                    pointer.setPrev(newNode);
                    inserted = true;
                    break;
                }
                pointer = pointer.next;
            }
            if(!inserted) {
                newNode.setNext(tail);
                newNode.setPrev(tail.prev);
                tail.prev.setNext(newNode);
                tail.setPrev(newNode);
            }
            return true;
        }
    }

    private boolean checkDuplicate(T data) {
        Node<T> pointer = head.next;
        while(!pointer.sentinel) {
            if(pointer.data.compareTo(data) == 0) {
                return true;
            }
            pointer = pointer.next;
    }
        return false;
    }

    public boolean removeNode(T data) {
        Node<T> pointer = head.next;
        while(!pointer.sentinel) {
            if(pointer.data.compareTo(data) == 0) {
                pointer.prev.setNext(pointer.next);
                pointer.next.setPrev(pointer.prev);
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    public String[] displayNodes(boolean indicator) {
        Node<T> current = head.next;
        String[] names = new String[getSize()];
        Tasks o;
        int count = 0;
        if(indicator) {
            while(!current.sentinel) {
                o = (Tasks) current.data;
                names[count] = o.getTaskName();
                count++;
                current = current.next;
            }
        } else {
            String formatted;
            while(!current.sentinel) {
                o = (Tasks) current.data;
                formatted = "• " + o.getTaskName() + "\n\tdue by " + o.getDueDate();
                names[count] = formatted;
                count++;
                current = current.next;
            }
        }
        return names;
    }

    public String[] displayNodes() {
        Node<T> current = head.next;
        String[] names = new String[getSize()];
        Events o;
        int count = 0;
        while(!current.sentinel) {
            o = (Events) current.data;
            names[count] = o.getEventName() + " on " + o.getEventDate();
            count++;
            current = current.next;
        }
        return names;
    }

    //finds the node that contains the incoming String
    public T findNode(String name) {
        Node<T> current = head.next;
        while(!current.sentinel) {
            if(name.equals(current.data.toString())) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public String[] findDeadline(String date, boolean indicator) {
        Node<T> current = head.next;
        String [] names = new String[getSize()];
        int counter = -1;
        Tasks o;
        Events e;
        if(indicator){
            while(!current.sentinel) {
                o = (Tasks) current.data;
                if(date.equals(o.getDueDate())) {
                    names[++counter] = o.getTaskName();
                }
                current = current.next;
            }
        }else{
            while(!current.sentinel) {
                e = (Events) current.data;
                if(date.equals(e.getEventDate())) {
                    names[++counter] = e.getEventName();
                }
                current = current.next;
            }
        }
        if(counter == -1){
            return null;
        }
        return names;
    }

    //turns the list into a file
    public void listToFile(String fileName) throws IOException{
        File list = new File(ModernProject.directory, fileName);
        Node<T> current = head.next;
        Tasks o;
        Events e;
        try (PrintWriter writer = new PrintWriter(list)) {
            if(fileName.equals("taskList.txt") || fileName.equals("completedList.txt")) {
                while(!current.sentinel){
                    o = (Tasks) current.data;
                    writer.println(o.getTaskName() + "~" + o.getDueDate() + "~" + o.getType()
                            + "~" + o.getDesc());
                    current = current.next;
                }
            }else {
                while(!current.sentinel){
                    e = (Events) current.data;
                    writer.println(e.getEventName() + "~" + e.getEventDate());
                    current = current.next;
                }
            }
        }
    }

    //turns the file into a list
    public void fileToList(String fileName) throws IOException {
        File list = new File(ModernProject.directory, fileName);
        if(list.exists()) {
            Scanner sc = new Scanner(new FileInputStream(list));
            String[] lineSplit;
            while(sc.hasNextLine()) {
                lineSplit = sc.nextLine().split(" *~ *", -1);
                if(fileName.equals("completedList.txt")) {
                    Tasks o = new Tasks(lineSplit);
                    ModernProject.completedLL.insert(o);
                } else if(fileName.equals("eventList.txt")) {
                    Events o = new Events(lineSplit);
                    ModernProject.eventsLL.insert(o);
                } else {
                    Tasks o = new Tasks(lineSplit);
                    ModernProject.taskLL.insert(o);
                    ModernProject.sortedLL.insert(o);
                }
            }
            sc.close();
        }
    }

    public void sort(){
        Node<T> ptr= head.next;
        if(ptr.sentinel)
            return;
        while(!ptr.sentinel){
            Node<T> current = ptr, temp = current.next;
            if(temp.sentinel)
                break;
            int c1 = convertDate((Tasks) current.data), c2 = convertDate((Tasks) temp.data);
            if(c1 > c2){
                current.prev.setNext(temp);
                temp.setPrev(current.prev);
                current.setPrev(temp);
                current.setNext(temp.next);
                temp.setNext(current);
                current.next.setPrev(current);
            }else{
                ptr = ptr.next;
            }
        }
    }

    private int convertDate(Tasks o){
        BuildCalendar cal = new BuildCalendar();
        Scanner read = new Scanner(o.getDueDate());
        read.next();
        int month = cal.monthConverter(read.next()) + 1;
        StringBuilder sb = new StringBuilder(read.next());
        sb.deleteCharAt(2);
        read.close();
        String combine = month + sb.toString() + read.next();
        return Integer.parseInt(combine);
    }

    //Node Class for the Linked List Class
    private class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;
        boolean sentinel;

        //Constructor for the Node Class; accepts parameters
        public Node(E data, Node<E> next, Node<E> prev, boolean sentinel) {
            this.data = data;
            this.next = next;
            this.prev = prev;
            this.sentinel = sentinel;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }
}