package Application;

import DataStructures.*;
import Exceptions.*;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
  // Objecte Scanner per obtenir la informacio de la consola.
  private static Scanner keyboard = new Scanner(System.in);

  // Metode que s'asegura d'obtenir un int de consola i el retorna.
  private static int getOption(int options) {
    int option = -1;
    boolean valid = false;

    while (!valid) {
      try {
        option = Integer.parseInt(keyboard.nextLine());
        if (1 <= option && option <= options) valid = true;
        else System.out.println("Opcio no valida!");
      } catch (NumberFormatException e) {
        System.out.println("Opcio no valida!");
      }
    }
    return option;
  }

  // Main del programa.
  public static void main(String[] args) {
    // Pregunta quina implementacio es vol fer servir.
    System.out.println("Quina implementacio vols fer servir?");
    System.out.println("\t1. Memoria estatica.");
    System.out.println("\t2. Memoria dinamica.");
    System.out.println("\t3. Java LinkedList.");
    int option = getOption(3);

    // Pregunta el nom del fitxer.
    BufferedReader file = null;
    String filePath = "";
    boolean valid = false;
    while (!valid) {
      System.out.println("Indica el nom del fitxer:");
      filePath = keyboard.nextLine();
      try {
        file = new BufferedReader(new FileReader(new File(filePath)));
        valid = true;
      } catch (FileNotFoundException e) {
        System.out.println("No s'ha trobat el fitxer indicat.");
      }
    }

    // Inicialitza les llistes segons la opcio triada.
    StaticList<Subject> subjectList1 = null;
    StaticList<Student> studentList1 = null;
    DynamicList<Subject> subjectList2 = null;
    DynamicList<Student> studentList2 = null;
    LinkedList<Subject> subjectList3 = null;
    LinkedList<Student> studentList3 = null;
    switch (option) {
      case 1:
        subjectList1 = new StaticList<>(1000);
        studentList1 = new StaticList<>(1000);
        break;
      case 2:
        subjectList2 = new DynamicList<>();
        studentList2 = new DynamicList<>();
        break;
      case 3:
        subjectList3 = new LinkedList<>();
        studentList3 = new LinkedList<>();
        break;
      default:
    }

    // Inici d'execucio.
    long start = System.nanoTime();

    // Plena les TADs.
    Matricula matricules = null;
    String line;
    try {
      while ((line = file.readLine()) != null) {
        if (!line.matches("\\d{8};[A-Z '-\u00b7\ufffd]*;\\d{1,2};\\d;\\dQ;URV_\\d{4};[A-Z ]*")) {
          System.out.println("Error! Linia no valida");
          continue;
        }

        Scanner tokens = new Scanner(line).useDelimiter(";");
        int subjectCode = Integer.parseInt(tokens.next());
        String subjectName = tokens.next();
        int subjectCredit = Integer.parseInt(tokens.next());
        int subjectCourse = Integer.parseInt(tokens.next());
        String subjectPeriod = tokens.next();
        String studentCode = tokens.next();
        String studentName = tokens.next();
        tokens.close();

        Subject subject = new Subject(subjectCode, subjectName, subjectCredit, subjectCourse, subjectPeriod);
        Student student = new Student(studentCode, studentName);

        try {
          switch (option) {
            case 1:
              subjectList1.add(subject);
              studentList1.add(student);
              student = studentList1.get(studentList1.indexOf(student));
              subject = subjectList1.get(subjectList1.indexOf(subject));
              break;
            case 2:
              subjectList2.add(subject);
              studentList2.add(student);
              student = studentList2.get(studentList2.indexOf(student));
              subject = subjectList2.get(subjectList2.indexOf(subject));
              break;
            case 3:
              subjectList3.add(subject);
              studentList3.add(student);
              student = studentList3.get(studentList3.indexOf(student));
              subject = subjectList3.get(subjectList3.indexOf(subject));
              break;
            default:
          }
        } catch (FullListException | EmptyListException e) {
          System.out.println(e.toString());
        }

        matricules = new Matricula(student, subject);
      }
    } catch (IOException e) {
      System.out.println(e.toString());
    }

    // Fi d'execucio.
    long end = System.nanoTime();
    long elapsed = end - start;

    // Indica el temps d'execucio.
    System.out.println("Done! Temps d'execucio: "+elapsed / 1000000+" ms");


    try {
      if (matricules != null) {
        Subject[] subjects = matricules.getCol(studentList1.get(1));
        for (Subject subject : subjects) {
          System.out.println(subject);
        }
      }
    } catch (EmptyListException e) {
      System.out.println(e.toString());
    }
  }
}
