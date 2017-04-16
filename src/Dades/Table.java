package Dades;

import Exceptions.*;

public class Table<R extends Comparable<R>, C extends Comparable<C>, E> implements TADGenericTable<R, C, E> {
  private TADGenericList<R> rowList;
  private TADGenericList<C> colList;

  public Table() {
    rowList = new DynamicList<>();
    colList = new DynamicList<>();
  }

  public boolean addRowElement(R r) throws LlistaPlena {
    return rowList.add(r);
  }

  public boolean removeRowElement(R r) throws LlistaBuida {
    return rowList.remove(r);
  }

  public boolean addColElement(C c) throws LlistaPlena {
    return colList.add(c);
  }

  public boolean removeColElement(C c) throws LlistaBuida {
    return colList.remove(c);
  }

  public boolean addRelation(R r, C c) {
    return false;
  }

  public boolean removeRelation(R r, C c) {
    return false;
  }

  public R[] getRow(C c) {

  }

  public C[] getCol(R c) {

  }
}
