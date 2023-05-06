package Prof9Homework_14_03_2023;

import java.util.Iterator;
import java.util.ListIterator;
public class MyLinkedList {

    private Node head;
    private Node cursor;

    //O(n) where n - size out list
    public void pushToTail(int i) {
        Node node = new Node(i, null);

        if (head == null) {
            head = node;
            cursor = node;
            return;
        }

        Node last = head;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        last.setNext(node);
    }

    // O(1)
    public void add(int i) {
        Node node = new Node(i, null);

        if (head == null) {
            head = node;
            cursor = node;
            return;
        }

        node.setNext(head);
        head = node;
        cursor = node;
    }

    public void addTo(int index, int i) {
        Node node = new Node(i, null);

        if (head == null) {
            head = node;
            cursor = node;
            return;
        }

        if (index >= size() || index < 0) {
            return;
        }

        Node curr = head;
        Node tail = head;
        int count = -1;
        while (count < size()) {
            count++;
            if (index == 0) {
                curr = node;
                curr.setNext(tail);
                head = curr;
                cursor = curr;
            } else if (count == index - 1) {
                tail = curr.getNext();
                curr.setNext(node);
            }
            if (count == index) {
                curr.setNext(tail);
                return;
            }
            curr = curr.getNext();
        }
    }

    public void remove(int index) {
        if (head == null) {
            return;
        }

        if (index >= size() || index < 0) {
            return;
        }

        Node curr = head;
        Node tail;
        int count = -1;
        while (count < size()) {
            count++;
            if (index == 0) {
                head = curr.getNext();
                cursor = curr.getNext();
                return;
            } else if (count == index - 1) {
                tail = curr.getNext().getNext();
                curr.setNext(tail);
                return;
            }
            curr = curr.getNext();
        }
    }

    public void print() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.getData() + " ");
            curr = curr.getNext();
        }
        System.out.println();
    }

    public int size() {
        int size = 0;
        if (head == null) {
            return 0;
        }

        Node curr = head;
        while (curr != null) {
            size++;
            curr = curr.getNext();
        }

        return size;
    }

    public int get(int index) {
        if (head == null) {
            return -1;
        }
        if (index >= size() || index < 0) {
            return -1;
        }
        int count = -1;
        Node curr = head;
        while (curr != null) {
            count++;
            if (count == index) {
                return curr.getData();
            }
            curr = curr.getNext();
        }
        return -1;
    }

    Iterator<Node> iterator = new Iterator<>() {
        @Override
        public boolean hasNext() {
            if (head == null) {
                return false;
            }

            if (cursor.getNext() != null) {
                return true;
            } else {
                cursor = head;
                return false;
            }
        }

        @Override
        public Node next() {
            if (head == null) {
                return null;
            }
            if (cursor != null) {
                cursor = cursor.getNext();
                return cursor;
            } else {
                return null;
            }
        }
    };

    ListIterator<Node> listIterator = new ListIterator<>() {
        @Override
        public boolean hasNext() {
            if (head == null) {
                return false;
            }

            return cursor.getNext() != null;
        }

        @Override
        public Node next() {
            if (head == null) {
                return null;
            }

            if (cursor != null) {
                cursor = cursor.getNext();
                return cursor;
            } else {
                return null;
            }
        }

        @Override
        public boolean hasPrevious() {
            if (head == null) {
                return false;
            }

            if (cursor != null && cursor != head) {
                Node prev = head;
                for (int i = 0; i < size(); i++) {
                    if (prev.getNext() == cursor) {
                        break;
                    }
                    prev = prev.getNext();
                }
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Node previous() {
            if (head == null) {
                return null;
            }

            if (cursor != null && cursor != head) {
                Node prev = head;
                for (int i = 0; i < size(); i++) {
                    if (prev.getNext() == cursor) {
                        cursor = prev;
                        break;
                    }
                    prev = prev.getNext();
                }
                return cursor;
            } else {
                return null;
            }
        }

        @Override
        public int nextIndex() {
            if (head == null && cursor.getNext() == null) {
                return -1;
            }

            int index = 0;
            Node indexCursor = cursor.getNext();
            while (indexCursor != null) {
                index++;
                indexCursor = indexCursor.getNext();
            }
            return size() - index;
        }

        @Override
        public int previousIndex() {
            if (head == null && head == cursor) {
                return -1;
            }

            int index = 0;
            Node indexCursor = cursor;
            while (indexCursor != null) {
                index++;
                indexCursor = indexCursor.getNext();
            }
            return size() - index - 1;
        }

        @Override
        public void remove() {
            if (head == null) {
                return;
            }

            if (cursor == null) {
                return;
            }

            Node tail = cursor.getNext();
            Node prev = head;
            for (int i = 0; i < size(); i++) {
                if (cursor == head) {
                    head = tail;
                    break;
                } else if (prev.getNext() == cursor) {
                    cursor = prev;
                    break;
                }
                prev = prev.getNext();
            }
            cursor.setNext(tail);
            cursor = cursor.getNext();
        }

        @Override
        public void set(Node node) {
            if (head == null) {
                head = node;
                cursor = node;
                return;
            }

            if (cursor == null) {
                return;
            }

            Node tail = cursor.getNext();
            Node prev = head;
            if (nextIndex() == 1) {
                cursor.setNext(node);
                cursor = cursor.getNext();
                head = cursor;
                cursor.setNext(tail);
            } else {
                for (int i = 0; i < size(); i++) {
                    if (prev.getNext() == cursor) {
                        cursor = prev;
                        break;
                    }
                    prev = prev.getNext();
                }
                cursor.setNext(node);
                cursor = cursor.getNext();
                cursor.setNext(tail);
            }
        }

        @Override
        public void add(Node node) {
            if (head == null) {
                head = node;
                cursor = node;
                return;
            }

            if (cursor == null) {
                return;
            }

            Node tail = cursor;
            Node prev = head;
            if (nextIndex() == 1) {
                node.setNext(tail);
                head = node;
                cursor = head;
            } else {
                for (int i = 0; i < size(); i++) {
                    if (prev.getNext() == cursor) {
                        cursor = prev;
                        break;
                    }
                    prev = prev.getNext();
                }
                cursor.setNext(node);
                cursor = cursor.getNext();
                cursor.setNext(tail);
            }
        }
    };
}