package Exceptions;

public class FullListException extends Exception {
  private static final long serialVersionUID = 1L;

  public FullListException() {
    super("ERROR : Llista plena");
  }
}
