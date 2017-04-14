package Dades;

public class Student implements CustomItem<Student> {
  private String code;
  private String name;
  private Matricula firstSubject;

  public Student(String code, String name) {
    this.code = code;
    this.name = name;
    firstSubject = null;
  }

  @Override
  public int compareTo(Student o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean isEqual(Student o) {
    return code.equals(o.code);
  }

  @Override
  public String toString() {
    return name;
  }
}
