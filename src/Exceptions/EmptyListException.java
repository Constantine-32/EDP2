package Exceptions;

public class EmptyListException extends Exception {
  private static final long serialVersionUID = 1L;

  public EmptyListException() {
    super("ERROR : Llista buida");
  }
}
