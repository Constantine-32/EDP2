package Dades;

public class Node<E extends CustomItem<E>> {
  private E item;
  private Node<E> next;

  public Node(E item, Node<E> next) {
    this.item = item;
    this.next = next;
  }

  public E getItem() {
    return item;
  }

  public Node<E> getNext() {
    return next;
  }

  public void setNext(Node<E> next) {
    this.next = next;
  }
}
