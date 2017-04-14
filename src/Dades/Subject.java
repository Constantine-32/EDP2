package Dades;

public class Subject implements Comparable<Subject>, Cloneable {
  private int code;
  private String name;
  private int credit;
  private int course;
  private int period;
  private Matricula firstStudent;

  public Subject(int code, String name, int credit, int course, int period) {
    this.code = code;
    this.name = name;
    this.credit = credit;
    this.course = course;
    this.period = period;
    firstStudent = null;
  }

  @Override
  public int compareTo(Subject o) {
    int aux = course - o.course;
    if (aux == 0) aux = period - o.period;
    if (aux == 0) aux = name.compareTo(o.name);
    return aux;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Subject && code == ((Subject) o).code;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public String toString() {
    return name;
  }
}
