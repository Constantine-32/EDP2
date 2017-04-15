package Dades;

import Exceptions.LlistaPlena;

public class Table<R extends Comparable<R>, C extends Comparable<C>, E> implements TADGenericTable<R, C, E> {
  private TADGenericList<R> rowList;
  private TADGenericList<C> colList;

  public Table() {
    rowList = new DynamicList<>();
    colList = new DynamicList<>();
  }

  public boolean addRowElement(R e) throws LlistaPlena {
    return rowList.add(e);
  }

  public boolean addColElement(C e) throws LlistaPlena {
    return colList.add(e);
  }

  public void addRelation(R r, C c) {

  }
}
