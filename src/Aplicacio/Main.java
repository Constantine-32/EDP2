package Aplicacio;

import Dades.*;
import Exceptions.*;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    DynamicList<Subject> subjectList = new DynamicList<>();
    DynamicList<Student> studentList = new DynamicList<>();

    String filePath = "DadesMatricula.csv";
    String regex = "\\d{8};[A-Z '-\u00b7\ufffd]*;\\d{1,2};\\d;\\dQ;URV_\\d{4};[A-Z ]*";

    try (BufferedReader file = new BufferedReader(new FileReader(new File(filePath)))) {

      String line;
      while ((line = file.readLine()) != null) {

        if (!(line.matches(regex))) System.out.println(line);

        Scanner tokens = new Scanner(line).useDelimiter(";");
        while (tokens.hasNext()) {
          int subjectCode = Integer.parseInt(tokens.next());
          String subjectName = tokens.next();
          int subjectCredit = Integer.parseInt(tokens.next());
          int subjectCourse = Integer.parseInt(tokens.next());
          String subjectPeriod = tokens.next();
          Subject subject = new Subject(subjectCode, subjectName, subjectCredit, subjectCourse, subjectPeriod);

          String studentCode = tokens.next();
          String studentName = tokens.next();
          Student student = new Student(studentCode, studentName);

          try {
            subjectList.add(subject);
            studentList.add(student);
          } catch (FullListException e) {
            System.out.println(e.toString());
          }
        }
        tokens.close();
      }
    } catch (IOException e) {
      System.out.println(e.toString());
    }

    System.out.println(subjectList);
    System.out.println(subjectList.size());
    System.out.println(studentList);
    System.out.println(studentList.size());
  }
}
