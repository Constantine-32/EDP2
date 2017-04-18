package Dades;

public class Relation implements TADMultilist {
  private Student student;
  private Subject subject;
  private Relation nextStudent;
  private Relation nextSubject;

  public Relation(Student student, Subject subject) {
    this.student = student;
    this.subject = subject;

    Relation pivot, previ;

    // Link vertically
    pivot = subject.getFirstStudent();
    previ = pivot;
    if (pivot == null) {
      subject.setFirstStudent(this);
    } else {
      while (pivot != null && pivot.getStudent().compareTo(student) < 0) {
        previ = pivot;
        pivot = pivot.getNextStudent();
      }
      previ.setNextStudent(this);
    }
    nextStudent = pivot;

    // Link horitzontally
    pivot = student.getFirstSubject();
    previ = pivot;
    if (pivot == null) {
      student.setFirstSubject(this);
    } else {
      while (pivot != null && pivot.getSubject().compareTo(subject) < 0) {
        previ = pivot;
        pivot = pivot.getNextSubject();
      }
      previ.setNextSubject(this);
    }
    nextSubject = pivot;
  }

  public void setNextStudent(Relation nextStudent) {
    this.nextStudent = nextStudent;
  }

  public void setNextSubject(Relation nextSubject) {
    this.nextSubject = nextSubject;
  }

  public Student getStudent() {
    return student;
  }

  public Subject getSubject() {
    return subject;
  }

  public Relation getNextStudent() {
    return nextStudent;
  }

  public Relation getNextSubject() {
    return nextSubject;
  }

  @Override
  public String toString() {
    return '['+student.toString()+", "+subject.toString()+']';
  }
}
