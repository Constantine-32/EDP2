package Aplicacio;

import Dades.*;
import Exceptions.*;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    DynamicList<Student> list = new DynamicList<>();
    Random dice = new Random();

    for (int i = 0; i < 20; i++) {
      try {
        list.add(new Student(String.valueOf(dice.nextInt(10000)), String.valueOf(dice.nextInt(10000))));
      } catch (LlistaPlena e) {
        System.out.println(e);
      }
    }

    System.out.println(list);

    for (Student student : list) {
      System.out.println(student);
    }
  }
}
