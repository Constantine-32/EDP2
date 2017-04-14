package Dades;

public class Matricula {
  private Student student;
  private Subject subject;
  private Matricula nextStudent;
  private Matricula nextSubject;

  public Matricula(Student student, Subject subject) {
    this.student = student;
    this.subject = subject;
    nextStudent = null;
    nextSubject = null;
  }

  public void setNextStudent(Matricula nextStudent) {
    this.nextStudent = nextStudent;
  }

  public void setNextSubject(Matricula nextSubject) {
    this.nextSubject = nextSubject;
  }

  public Student getStudent() {
    return student;
  }

  public Subject getSubject() {
    return subject;
  }

  public Matricula getNextStudent() {
    return nextStudent;
  }

  public Matricula getNextSubject() {
    return nextSubject;
  }
}
