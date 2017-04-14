package Dades;

public class Student implements Comparable<Student> {
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
  public boolean equals(Object o) {
    return o instanceof Student && code.equals(((Student) o).code);
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
