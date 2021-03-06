package DataStructures;

import java.util.Iterator;
import Exceptions.*;

public class DynamicList<E extends Comparable<E>> implements TADGenericList<E> {
  private Node<E> head;
  private int size;

  public DynamicList() {
    head = null;
    size = 0;
  }

  @Override
  public boolean add(E e) throws FullListException {
    if (contains(e)) return false;

    if (isEmpty() || head.getItem().compareTo(e) > 0) {
      // Si la llista esta buida o Si l'element es anterior al head actual.
      head = new Node<>(e, head);
    } else {
      // En cas contrari afegeix l'element normalment.
      Node<E> aux = head;
      while (aux.getNext() != null && aux.getNext().getItem().compareTo(e) < 0) {
        aux = aux.getNext();
      }
      aux.setNext(new Node<>(e, aux.getNext()));
    }
    size++;
    return true;
  }

  @Override
  public boolean remove(E e) throws EmptyListException {
    if (isEmpty()) throw new EmptyListException();
    if (!contains(e)) return false;

    Node<E> aux = head;
    while (aux.getNext() != null && !aux.getNext().getItem().equals(e)) {
      aux = aux.getNext();
    }
    if (aux.getNext() != null) {
      aux.setNext(aux.getNext().getNext());
    } else {
      head = null;
    }
    size--;
    return true;
  }

  @Override
  public E get(int index) throws EmptyListException {
    Node<E> aux = head;
    for (int i = 0; i < index; i++) {
      aux = aux.getNext();
    }
    return aux.getItem();
  }

  @Override
  public int indexOf(E e) {
    int index = 0;
    Node<E> aux = head;
    while (aux != null && !aux.getItem().equals(e)) {
      aux = aux.getNext();
      index++;
    }
    return aux != null ? index : -1;
  }

  @Override
  public boolean contains(E e) {
    return indexOf(e) != -1;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  private class ListIterator implements Iterator<E> {
    private Node<E> current;
    private int index;

    private ListIterator() {
      current = head;
      index = 0;
    }

    public boolean hasNext() {
      return index < size;
    }

    public E next() {
      E aux = current.getItem();
      current = current.getNext();
      index++;
      return aux;
    }
  }

  @Override
  public Iterator<E> iterator() {
    return new ListIterator();
  }

  @Override
  public String toString() {
    if (isEmpty()) return "[]";
    StringBuilder sb = new StringBuilder(size * 10).append('[');
    Node<E> aux = head;
    while (aux.getNext() != null) {
      sb.append(aux.getItem().toString()).append(',').append(' ');
      aux = aux.getNext();
    }
    sb.append(aux.getItem().toString()).append(']');
    return sb.toString();
  }

  private class Node<O extends Comparable<O>> {
    private O item;
    private Node<O> next;

    private Node(O item, Node<O> next) {
      this.item = item;
      this.next = next;
    }

    private O getItem() {
      return item;
    }

    private Node<O> getNext() {
      return next;
    }

    private void setNext(Node<O> next) {
      this.next = next;
    }
  }
}
