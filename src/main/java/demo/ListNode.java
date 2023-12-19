package demo;

public class ListNode {
    ListNode next;
    ListNode previous;
    Item item;

    ListNode(Item item) {
        this.item = item;
        this.next = null;
        this.previous = null;
    }
}