package DataStructures;

import Exceptions.*;

/**
 * Interface per a definir el contenidor/col·leccio llista generica.
 * @param <E> el tipus dels elements continguts a la Llista.
 */
public interface TADGenericList<E> extends Iterable<E> {

  /**
   * Afegeix l'element especificat a la Llista.
   * @param e element a afegir.
   * @return si l'element s'ha afegit com a resultat d'aquesta crida.
   * @throws FullListException la Llista esta plena i no es pot afegir l'element.
   */
  boolean add(E e) throws FullListException;

  /**
   * Elimina l'element de la Llista.
   * @param e element a eliminar.
   * @return si l'element s'ha eliminat com a resultat d'aquesta crida.
   * @throws EmptyListException la Llista esta buida i no hi ha cap element per a retornar.
   */
  boolean remove(E e) throws EmptyListException;

  /**
   * Retorna l'element de la posicio indicada.
   * @param index posicio de l'element a retornar.
   * @return l'element indicat.
   * @throws EmptyListException la Llista esta buida i no hi ha cap element per a retornar.
   */
  E get(int index) throws EmptyListException;

  /**
   * Retorna l'index de l'element dins la llista.
   * @param e element a buscar.
   * @return l'index de l'element en la llista, -1 si l'element no existeix.
   */
  int indexOf(E e);

  /**
   * Retorna si l'element esta present en la llista.
   * @param e element a buscar.
   * @return si l'element esta present en la llista.
   */
  boolean contains(E e);

  /**
   * Retorna si la Llista esta buida.
   * @return cert si la Llista esta buida, fals en cas contrari.
   */
  boolean isEmpty();

  /**
   * Retorna si la Llista esta plena.
   * @return cert si la Llista esta plena, fals en cas contrari.
   */
  boolean isFull();

  /**
   * Retorna el numero d'elements guardats a la Llista.
   * @return numero d'elements guardats a la Llista.
   */
  int size();
}
