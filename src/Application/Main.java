package Application;

import DataStructures.*;
import Exceptions.*;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
  // Objecte Scanner per obtenir la informacio de la consola.
  private static Scanner keyboard = new Scanner(System.in);

  private static int implementationOption;

  // Llistes propies.
  private static TADGenericList<Subject> subjectList = null;
  private static TADGenericList<Student> studentList = null;

  // Llistes de java.
  private static LinkedList<Subject> subjectListJava = null;
  private static LinkedList<Student> studentListJava = null;

  // Relacions.
  private static Matricula matricules = null;

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

  // A partir d’un codi d’alumne, mostra per pantalla les dades de les
  // assignatures que ha matriculat i a quants crèdits corresponen en total.
  private static int getStudentCredits(String studentCode, boolean log) throws NullPointerException {
    Student student = null;
    switch (implementationOption) {
    case 1: case 2:
      try {
        student = studentList.get(studentList.indexOf(new Student(studentCode, "")));
      } catch (EmptyListException e) {
        System.out.println(e.toString());
      }
      break;
    case 3:
      student = studentListJava.get(studentListJava.indexOf(new Student(studentCode, "")));
      break;
    }

    Subject[] subjects = matricules.getCol(student);
    int credits = 0;
    if (log) System.out.println("Assignatures matriculades:");
    for (Subject subject : subjects) {
      if (log) System.out.println(subject);
      credits += subject.getCredit();
    }
    if (log) System.out.println("Total de credits: "+credits);
    return credits;
  }

  // A partir d’un codi d’assignatura, mostra per pantalla quins alumnes l’han
  // matriculat.
  private static int getSubjectStudents(int subjectCode, boolean log) throws NullPointerException {
    Subject subject = null;
    switch (implementationOption) {
    case 1: case 2:
      try {
        subject = subjectList.get(subjectList.indexOf(new Subject(subjectCode, "", 0, 0, "")));
      } catch (EmptyListException e) {
        System.out.println(e.toString());
      }
      break;
    case 3:
      subject = subjectListJava.get(subjectListJava.indexOf(new Subject(subjectCode, "", 0, 0, "")));
      break;
    }
    Student[] students = matricules.getRow(subject);
    int studentCount = 0;
    if (log) System.out.println("Estudiants matriculats: ");
    for (Student student : students) {
      if (log) System.out.println(student);
      studentCount++;
    }
    if (log) System.out.println("Total d'estudiants: "+studentCount);
    return studentCount;
  }

  // Mostra per pantalla les dades dels alumnes que han matriculat X crèdits o menys.
  private static void getStudentsMat(int x) {
    switch (implementationOption) {
    case 1: case 2:
      for (Student student : studentList) {
        if (getStudentCredits(student.getCode(), false) <= x) {
          System.out.println(student);
        }
      }
      break;
    case 3:
      for (Student student : studentListJava) {
        if (getStudentCredits(student.getCode(), false) <= x) {
          System.out.println(student);
        }
      }
      break;
    }
  }

  // Mostra per pantalla les dades de les assignatures que tenen un mínim de Y
  // estudiants matriculats.
  private static void getSubjectsMat(int x) {
    switch (implementationOption) {
      case 1: case 2:
        for (Subject subject : subjectList) {
          if (getSubjectStudents(subject.getCode(), false) >= x) {
            System.out.println(subject);
          }
        }
        break;
      case 3:
        for (Subject subject : subjectListJava) {
          if (getSubjectStudents(subject.getCode(), false) >= x) {
            System.out.println(subject);
          }
        }
        break;
    }
  }

  // Main del programa.
  public static void main(String[] args) {
    // Pregunta quina implementacio es vol fer servir.
    System.out.println("Quina implementacio vols fer servir?");
    System.out.println("\t1. Memoria estatica.");
    System.out.println("\t2. Memoria dinamica.");
    System.out.println("\t3. Java LinkedList.");
    implementationOption = getOption(3);

    // Pregunta el nom del fitxer.
    BufferedReader file = null;
    String filePath;
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
    switch (implementationOption) {
      case 1:
        subjectList = new StaticList<>(1000);
        studentList = new StaticList<>(1000);
        break;
      case 2:
        subjectList = new DynamicList<>();
        studentList = new DynamicList<>();
        break;
      case 3:
        subjectListJava = new LinkedList<>();
        studentListJava = new LinkedList<>();
        break;
      default:
    }

    // Inici d'execucio.
    long start = System.nanoTime();

    // Plena les TADs.
    String line;
    try {
      while ((line = file.readLine()) != null) {
        if (!line.matches("\\d{8},[A-Z '-\u00b7\ufffd]*,\\d{1,2},\\d,\\dQ,URV_\\d{4},[A-Z ]*")) {
          System.out.println("Error! Linia no valida");
          continue;
        }

        Scanner tokens = new Scanner(line).useDelimiter(",");
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
          switch (implementationOption) {
            case 1: case 2:
              subjectList.add(subject);
              studentList.add(student);
              student = studentList.get(studentList.indexOf(student));
              subject = subjectList.get(subjectList.indexOf(subject));
              break;
            case 3:
              subjectListJava.add(subject);
              studentListJava.add(student);
              student = studentListJava.get(studentListJava.indexOf(student));
              subject = subjectListJava.get(subjectListJava.indexOf(subject));
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


    int num;
    String str;

    // Menu de consultes:
    System.out.println("Quina implementacio vols fer servir?");
    System.out.println("\t1. Informacio d'un alumne.");
    System.out.println("\t2. Informacio d'una assignatura.");
    System.out.println("\t3. Alumnes matriculats a X o menys credits.");
    System.out.println("\t4. Assignatures en Y o mes estudiants.");
    System.out.println("\t5. Sortir.");
    int option = getOption(5);
    while (option != 5) {
      switch (option) {
        case 1:
          System.out.println("Indica el codi d'alumne:");
          str = keyboard.nextLine();
          // Inici d'execucio.
          start = System.nanoTime();
          try {
            getStudentCredits(str, true);
          } catch (NullPointerException e) {
            System.out.println("Codi d'alumne no valid");
          }
          // Fi d'execucio.
          end = System.nanoTime();
          break;
        case 2:
          System.out.println("Indica el codi d'assignatura:");
          num = Integer.parseInt(keyboard.nextLine());
          // Inici d'execucio.
          start = System.nanoTime();
          try {
            getSubjectStudents(num, true);
          } catch (NullPointerException e) {
            System.out.println("Codi d'asignatura no valida");
          }
          // Fi d'execucio.
          end = System.nanoTime();
          break;
        case 3:
          System.out.println("Indica el nombre de credits maxim:");
          num = Integer.parseInt(keyboard.nextLine());
          // Inici d'execucio.
          start = System.nanoTime();
          getStudentsMat(num);
          // Fi d'execucio.
          end = System.nanoTime();
          break;
        case 4:
          System.out.println("Indica el nombre d'alumnes minim:");
          num = Integer.parseInt(keyboard.nextLine());
          // Inici d'execucio.
          start = System.nanoTime();
          getSubjectsMat(num);
          // Fi d'execucio.
          end = System.nanoTime();
          break;
        default:
      }

      // Indica el temps d'execucio.
      elapsed = end - start;
      System.out.println("Done! Temps d'execucio: "+elapsed / 1000000+" ms");

      // Menu de consultes:
      System.out.println("Quina implementacio vols fer servir?");
      System.out.println("\t1. Informacio d'un alumne.");
      System.out.println("\t2. Informacio d'una assignatura.");
      System.out.println("\t3. Alumnes matriculats a X o menys credits.");
      System.out.println("\t4. Assignatures en Y o mes estudiants.");
      System.out.println("\t5. Sortir.");
      option = getOption(5);
    }
  }
}
