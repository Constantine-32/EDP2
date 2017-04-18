package DataStructures;

/**
 * Interface per a definir el TAD Multillista.
 * @param <R> el tipus d'elements de les files.
 * @param <C> el tipus d'elements de les columnes.
 */
public interface TADMultilist<R, C> {

  /**
   * Reotrna si existeix o no la relacio.
   * @param r Element a.
   * @param c Element b.
   * @return si existeix o no la relacio.
   */
  boolean exists(R r, C c);

  /**
   * Retorna una array amb tots els elements de la fila indicada.
   * @param c Element que seleciona la fila.
   * @return array amb tots els elements de la fila indicada.
   */
  R[] getRow(C c);

  /**
   * Retorna una array amb tots els elements de la columna indicada.
   * @param r Element que selecciona la columna.
   * @return array amb tots els elements de la columna indicada.
   */
  C[] getCol(R r);
}
