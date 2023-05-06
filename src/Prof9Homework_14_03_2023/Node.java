package Prof9Homework_14_03_2023;

public class Node {

    private Integer data;

    private Node next;

    public Node(Integer data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Integer getData() {
        return data;
    }

    @Override
    public String toString() {
        return "current Node: " + data +
                ", next Node contains: {" + next + '}';
    }
}
