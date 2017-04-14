package Aplicacio;

import Dades.*;
import Exceptions.*;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    StaticList<Student> list = new StaticList<>(10);
    Random dice = new Random();

    try {
      for (int i = 0; i < 20; i++)
        list.add(new Student(String.valueOf(dice.nextInt(10000)), String.valueOf(dice.nextInt(10000))));
    } catch (LlistaPlena e) {
      System.out.println(e.toString());
    }

    System.out.println(list);

    try {
      System.out.println(list.get(3));
    } catch (LlistaBuida e) {
      System.out.println(e.toString());
    }
  }
}
