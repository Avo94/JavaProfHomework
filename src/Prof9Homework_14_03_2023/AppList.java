package Prof9Homework_14_03_2023;

import java.util.Iterator;
import java.util.ListIterator;

public class AppList {

    public static void main(String[] args) {

        Node node1 = new Node(3, null);
        Node node2 = new Node(5, node1);
        Node node3 = new Node(2, node2);
        Node head = new Node(4, node3);


        MyLinkedList myLinkedList = new MyLinkedList();

        myLinkedList.add(5); // 5-------
        myLinkedList.add(2); // 2 5 ----
        myLinkedList.add(4); // 4 2 5 --

        myLinkedList.pushToTail(3); // 4 2 5 3
        myLinkedList.pushToTail(6); // 4 2 5 3 6
        myLinkedList.pushToTail(1); // 4 2 5 3 6 1

        myLinkedList.print();
        System.out.println("Size = " + myLinkedList.size());
        // 4 2 5 3 6 1

        int temp = myLinkedList.get(5);
        System.out.println(temp);
        System.out.println(myLinkedList.get(4));

        System.out.println();
        System.out.println("Homework");
        //Homework
        myLinkedList.addTo(4, 0);
        // 4 2 5 3 0 6 1
        myLinkedList.addTo(0, 7);
        // 7 4 2 5 3 0 6 1
        myLinkedList.addTo(7, 9);
        // 7 4 2 5 3 0 6 9 1
        myLinkedList.remove(2);
        // 7 4 5 3 0 6 9 1
        myLinkedList.remove(0);
        // 4 5 3 0 6 9 1
        myLinkedList.remove(6);
        // 4 5 3 0 6 9
        myLinkedList.print();

        System.out.println();
        System.out.println("List Iterator");
        Iterator<Node> nodeIterator = myLinkedList.iterator;
        while (nodeIterator.hasNext()) {
            System.out.println(nodeIterator.next());
        }

        System.out.println();
        System.out.println("Node List Iterator forward");
        // after execution set index at 0
        ListIterator<Node> nodeListIterator = myLinkedList.listIterator;
        while (nodeListIterator.hasNext()) {
            System.out.println(nodeListIterator.next());
        }

        System.out.println();
        System.out.println("Node List Iterator back");
        while (nodeListIterator.hasPrevious()) {
            System.out.println(nodeListIterator.previous());
        }

        System.out.println();
        nodeListIterator.next();
        nodeListIterator.next();
        System.out.println("Next index = " + nodeListIterator.nextIndex());
        System.out.println("Previous index = " + nodeListIterator.previousIndex());
        // 4 5 ->3 0 6 9

        System.out.println();
        nodeListIterator.remove();
        myLinkedList.print();
        // 4 5 ->0 6 9

        nodeListIterator.previous();
        nodeListIterator.set(new Node(8, null));
        myLinkedList.print();
        // 4 ->8 0 6 9

        nodeListIterator.previous();
        nodeListIterator.add(new Node(1, null));
        myLinkedList.print();
        // ->1 4 8 0 6 9
    }
}