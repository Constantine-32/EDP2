package Dades;

import Exceptions.*;

/**
 * Interface per a definir el contenidor/col·leccio taula generica.
 * @param <R> el tipus dels elements continguts a la Llista.
 * @param <C> el tipus dels elements continguts a la Llista.
 * @param <E> el tipus dels elements continguts a la Llista.
 */
public interface TADGenericTable<R, C, E> {

  /**
   *
   * @param r
   * @return
   * @throws LlistaPlena
   */
  boolean addRowElement(R r) throws LlistaPlena;

  boolean removeRowElement(R r) throws LlistaBuida;

  boolean addColElement(C c) throws LlistaPlena;

  boolean removeColElement(C c) throws LlistaBuida;

  boolean addRelation(R r, C c);

  boolean removeRelation(R r, C c);

  R[] getRow(C c);

  C[] getCol(R c);
}
