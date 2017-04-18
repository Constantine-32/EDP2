package DataStructures;

public class Matricula implements TADMultilist<Student, Subject> {
  private Student student;
  private Subject subject;
  private Matricula nextStudent;
  private Matricula nextSubject;

  public Matricula(Student student, Subject subject) {
    this.student = student;
    this.subject = subject;
    linkCol();
    linkRow();
  }

  private void linkCol() {
    Matricula firstMatricula = subject.getFirstStudent();

    if (firstMatricula == null || firstMatricula.student.compareTo(student) >= 0) {
      subject.setFirstStudent(this);
      nextStudent = firstMatricula;
      return;
    }

    Matricula pivot = firstMatricula;
    Matricula previ = pivot;
    while (pivot != null && pivot.student.compareTo(student) < 0) {
      previ = pivot;
      pivot = pivot.nextStudent;
    }
    previ.nextStudent = this;
    nextStudent = pivot;
  }

  private void linkRow() {
    Matricula firstMatricula = student.getFirstSubject();

    if (firstMatricula == null || firstMatricula.subject.compareTo(subject) >= 0) {
      student.setFirstSubject(this);
      nextSubject = firstMatricula;
      return;
    }

    Matricula pivot = firstMatricula;
    Matricula previ = pivot;
    while (pivot != null && pivot.subject.compareTo(subject) < 0) {
      previ = pivot;
      pivot = pivot.nextSubject;
    }
    previ.nextSubject = this;
    nextSubject = pivot;
  }

  @Override
  public boolean exists(Student student, Subject subject) {
    Matricula matricula = student.getFirstSubject();
    while (matricula != null && !matricula.subject.equals(subject)) {
      matricula = matricula.nextSubject;
    }
    return matricula != null;
  }

  @Override
  public Student[] getRow(Subject subject) {
    int size = 0;
    Matricula matricula = subject.getFirstStudent();
    while (matricula != null) {
      matricula = matricula.nextStudent;
      size++;
    }
    Student[] aux = new Student[size];
    matricula = subject.getFirstStudent();
    for (int i = 0; i < size; i++) {
      try {
        aux[i] = (Student) matricula.student.clone();
        matricula = matricula.nextStudent;
      } catch (CloneNotSupportedException e) {
        System.out.println(e.toString());
      }
    }
    return aux;
  }

  @Override
  public Subject[] getCol(Student student) {
    int size = 0;
    Matricula matricula = student.getFirstSubject();
    while (matricula != null) {
      matricula = matricula.nextSubject;
      size++;
    }
    Subject[] aux = new Subject[size];
    matricula = student.getFirstSubject();
    for (int i = 0; i < size; i++) {
      try {
        aux[i] = (Subject) matricula.subject.clone();
        matricula = matricula.nextSubject;
      } catch (CloneNotSupportedException e) {
        System.out.println(e.toString());
      }
    }
    return aux;
  }

  @Override
  public String toString() {
    return '['+student.toString()+", "+subject.toString()+']';
  }
}
