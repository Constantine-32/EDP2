package Dades;

import Exceptions.*;

public class StaticList<E extends CustomItem<E>> implements TADGenericList<E> {
  private E[] list;
  private int size;

  @SuppressWarnings("unchecked")
  public StaticList(int dim) {
    list = (E[]) new CustomItem[dim];
    size = 0;
  }

  @Override
  public boolean add(E e) throws LlistaPlena {
    if (isFull()) throw new LlistaPlena();
    if (contains(e)) return false;

    int index = 0;
    while (index < size && list[index].compareTo(e) < 0) {
      index++;
    }
    insert(index, e);
    return true;
  }

  /**
   * Metode privat per inserir un element a la posicio indicada.
   * @param index index on inserir l'element
   * @param e element a inserir
   */
  private void insert(int index, E e) {
    System.arraycopy(list, index, list, index + 1, size - index);
    list[index] = e;
    size++;
  }

  @Override
  public boolean remove(E e) throws LlistaBuida {
    if (isEmpty()) throw new LlistaBuida();
    if (!contains(e)) return false;

    int index = indexOf(e);
    System.arraycopy(list, index + 1, list, index, size - index);
    size--;
    return true;
  }

  @Override
  public int indexOf(E e) {
    int index = 0;
    while (index < size && !list[index].isEqual(e)) {
      index++;
    }
    return index < size ? index : -1;
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
    return size == list.length;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public String toString() {
    if (isEmpty()) return "[]";

    StringBuilder sb = new StringBuilder(size * 10).append("[");
    for (int i = 0; i < size - 1; i++) {
      sb.append(list[i].toString()).append(", ");
    }
    sb.append(list[size-1].toString());
    return sb.append("]").toString();
  }
}
