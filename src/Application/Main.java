package Application;

import DataStructures.*;
import Exceptions.*;

import java.io.*;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    DynamicList<Subject> subjectList = new DynamicList<>();
    DynamicList<Student> studentList = new DynamicList<>();
    Matricula matricules = null;

    String filePath = "DadesMatricula.csv";
    try (BufferedReader file = new BufferedReader(new FileReader(new File(filePath)))) {
      String line;
      while ((line = file.readLine()) != null) {
        Scanner tokens = new Scanner(line).useDelimiter(";");
        int    subjectCode    = Integer.parseInt(tokens.next());
        String subjectName    = tokens.next();
        int    subjectCredit  = Integer.parseInt(tokens.next());
        int    subjectCourse  = Integer.parseInt(tokens.next());
        String subjectPeriod  = tokens.next();
        String studentCode    = tokens.next();
        String studentName    = tokens.next();
        tokens.close();

        Subject subject = new Subject(subjectCode, subjectName, subjectCredit, subjectCourse, subjectPeriod);
        Student student = new Student(studentCode, studentName);

        try {
          subjectList.add(subject);
          studentList.add(student);
          student = studentList.get(studentList.indexOf(student));
          subject = subjectList.get(subjectList.indexOf(subject));
        } catch (FullListException | EmptyListException e) {
          System.out.println(e.toString());
        }

        matricules = new Matricula(student, subject);
      }
    } catch (IOException e) {
      System.out.println(e.toString());
    }

    try {
      if (matricules != null) {
        Subject[] subjects = matricules.getCol(studentList.get(1));
        for (int i = 0; i < subjects.length; i++) {
          System.out.println(subjects[i]);
        }
      }
    } catch (EmptyListException e) {
      System.out.println(e.toString());
    }
  }
}
