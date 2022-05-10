package ex04;

import java.util.Objects;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private int size = 0;
    private Node head = null;
    private Node last = null;

    public void add(Transaction transaction) {
        if (Objects.isNull(transaction)) {
            System.err.println("transaction is null");
            throw new NullPointerException();
        }
        Node add = new Node(last, transaction, null);
        if (Objects.isNull(head))
            head = add;
        else
            last.next = add;
        last = add;
        size++;
    }

    public void remove(UUID id) {
        Node delete = find(id);
        if (!Objects.isNull(delete)) {
            if (head.equals(delete))
                head = delete.next;
            else if (last.equals(delete))
                last = delete.prev;
            else
                delete.prev.next = delete.next;
            size--;
        }
        else
            throw new TransactionNotFoundException();
    }

    private Node find(UUID id) {
        Node temp = head;
        while (!Objects.isNull(temp)) {
            if (temp.item.getId().equals(id))
                return temp;
            temp = temp.next;
        }
        return null;
    }

    public Transaction[] toArray() {
        Transaction[] res = new Transaction[size];
        Node temp = head;

        for (int i = 0; i < size && !Objects.isNull(temp); i++, temp = temp.next)
            res[i] = temp.item;
        return res;
    }

    private static class Node {
        Transaction item;
        Node next;
        Node prev;

        Node(Node prev, Transaction element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

}
