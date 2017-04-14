package Dades;

public class Subject implements CustomItem<Subject> {
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
  public boolean isEqual(Subject o) {
    return code == o.code;
  }
}
