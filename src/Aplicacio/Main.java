package Aplicacio;

import Dades.*;
import Exceptions.*;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    StaticList<Subject> subjectList = new StaticList<>(10);
    StaticList<Student> studentList = new StaticList<>(10);

    Student student0 = new Student("00000", "Aaaaa");
    Student student1 = new Student("00001", "Bbbbb");
    Student student2 = new Student("00002", "Ccccc");
    Student student3 = new Student("00003", "Ddddd");
    Student student4 = new Student("00004", "Eeeee");

    Subject subject0 = new Subject(1, "S1", 6, 1, "1Q");
    Subject subject1 = new Subject(2, "S2", 6, 1, "2Q");
    Subject subject2 = new Subject(3, "S3", 6, 2, "1Q");
    Subject subject3 = new Subject(4, "S4", 6, 2, "2Q");
    Subject subject4 = new Subject(5, "S5", 6, 3, "1Q");

    try {
      studentList.add(student0);
      studentList.add(student1);
      studentList.add(student2);
      studentList.add(student3);
      studentList.add(student4);

      subjectList.add(subject0);
      subjectList.add(subject1);
      subjectList.add(subject2);
      subjectList.add(subject3);
      subjectList.add(subject4);

    } catch (FullListException e) {
      System.out.println(e.toString());
    }

    Relation relation0 = new Relation(student0, subject0);
    Relation relation1 = new Relation(student0, subject4);
    Relation relation2 = new Relation(student0, subject3);

    System.out.println("Hello World.");


//    String filePath = "DadesMatricula.csv";
//    String regex = "\\d{8};[A-Z '-\u00b7\ufffd]*;\\d{1,2};\\d;\\dQ;URV_\\d{4};[A-Z ]*";
//
//    try (BufferedReader file = new BufferedReader(new FileReader(new File(filePath)))) {
//
//      String line;
//      while ((line = file.readLine()) != null) {
//
//        if (!(line.matches(regex))) System.out.println(line);
//
//        Scanner tokens = new Scanner(line).useDelimiter(";");
//        while (tokens.hasNext()) {
//          int subjectCode = Integer.parseInt(tokens.next());
//          String subjectName = tokens.next();
//          int subjectCredit = Integer.parseInt(tokens.next());
//          int subjectCourse = Integer.parseInt(tokens.next());
//          String subjectPeriod = tokens.next();
//          Subject subject = new Subject(subjectCode, subjectName, subjectCredit, subjectCourse, subjectPeriod);
//
//          String studentCode = tokens.next();
//          String studentName = tokens.next();
//          Student student = new Student(studentCode, studentName);
//
//          try {
//            subjectList.add(subject);
//            studentList.add(student);
//          } catch (FullListException e) {
//            System.out.println(e.toString());
//          }
//        }
//        tokens.close();
//      }
//    } catch (IOException e) {
//      System.out.println(e.toString());
//    }
//
//    System.out.println(subjectList);
//    System.out.println(subjectList.size());
//    System.out.println(studentList);
//    System.out.println(studentList.size());
  }
}
