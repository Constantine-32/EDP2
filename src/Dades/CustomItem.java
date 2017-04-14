package Dades;

/**
 * Interface per definir els elements de les llistes generiques.
 * @param <T> el tipus del objecte.
 */
public interface CustomItem<T> extends Comparable<T> {
  /**
   * Compara l'objecte amb l'objecte que es pasa per parametre a partir de l'atribut identificador.
   * @param o - objecte a comparar.
   * @return cert si son iguals, fals en cas contrari.
   */
  boolean isEqual(T o);
}
