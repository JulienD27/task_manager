package modernproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class StackLinkedList {

    //Node Class of the StackLinkedList
    private class Node{
        Entry data;
        Node next, prev;
        boolean sentinel;
        String entry;

        //Constructor of the Node Class
        private Node(Entry data, Node next, Node prev, boolean sentinel, String entry) {
            this.data = data;
            this.next = next;
            this.prev = prev;
            this.sentinel = sentinel;
            this.entry = entry;
        }

        private void setNext(Node next){
            this.next = next;
        }

        private void setPrev(Node prev){
            this.prev = prev;
        }
    }

    Node top, bottom;

    //Constructor for the class, initiates the top and bottom nodes with
    // the corresponding Node information as the Node constructor
    public StackLinkedList() {
        this.top = new Node(null, null, null, false, null);
        this.bottom = new Node(null, top, null, false, null);
        top.setPrev(bottom);
    }

    public int getSize() {
        Node current = bottom.next;
        int count = 0;
        while(current.sentinel) {
            count++;
            current = current.next;
        }
        return count;
    }

    //pushes the an Entry object on top of the bottom node or
    // the last node that was pushed
    public void push(Entry o) {
        Node newNode = new Node(o, null, null, true,
                "Journal Entry #" + (getSize() + 1));
        if (bottom.next == top) {
            bottom.setNext(newNode);
            top.setPrev(newNode);
            newNode.setNext(top);
            newNode.setPrev(bottom);
        }else {
            Node current = top.prev;
            top.setPrev(newNode);
            current.setNext(newNode);
            newNode.setNext(top);
            newNode.setPrev(current);
        }
    }

    //returns true if bottom.next = top
    public boolean isEmpty() {
        return bottom.next == top;
    }

    public Entry peek() {
        if (!isEmpty()) {
            Node current = top.prev;
            return current.data;
        } else {
            return null;
        }
    }

    //pops off the latest node that was pushed
    public boolean pop() {
        if (isEmpty()) {
            return false;
        } else {
            Node current = top.prev;
            top.setPrev(current.prev);
            current.prev.setNext(top);
            return true;
        }
    }

    public Entry findNode(String entryNum) {
        Node current = bottom.next;
        while(current.sentinel) {
            if(entryNum.equals(current.entry)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public String[] displayNodes() {
        String[] entries = new String[getSize()];
        if (top == bottom) {
            return null;
        } else {
            int counter = 0;
            int numEntries = getSize();
            Node current = bottom.next;
            while(current.sentinel) {
                entries[counter++] = "Journal Entry #" + numEntries--;
                current = current.next;
            }
        }
        return entries;
    }

    public void listToFile() throws IOException {
        File list = new File(ModernProject.directory, "journal.txt");
        Node current = bottom.next;
        Entry o;
        try (PrintWriter writer = new PrintWriter(list)) {
            while(current.sentinel){
                o = current.data;
                writer.println(o.getEntryName() + "~" + o.getEntry());
                current = current.next;
            }
        }
    }

    public void fileToList() throws IOException {
        File list = new File(ModernProject.directory, "journal.txt");
        if(list.exists()){
            Scanner sc = new Scanner(new FileInputStream(list));
            String[] lineSplit;
            while(sc.hasNextLine()) {
                lineSplit = sc.nextLine().split(" *~ *", -1);
                Entry o = new Entry(lineSplit);
                ModernProject.journalLL.push(o);
            }
            sc.close();
        }
    }

}
