package exercise4;

import java.util.Scanner;

public class Main {
  public static int findFirstEmptySlot(MusicalInstrument[] orchestra) {
    for (int i = 0; i < orchestra.length; i++) {
      if (orchestra[i] == null) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    MusicalInstrument[] orchestra = new MusicalInstrument[64];

    int choose = -1;
    for (int i = 0; i < orchestra.length; i++) {
      System.out.println("Choose an option:");
      System.out.println("1 - Add a Traverse Flute");
      System.out.println("2 - Add a Straight Flute");
      System.out.println("3 - Add a Piano");
      System.out.println("4 - Add a Violin");
      System.out.println("0 - Exit");
      choose = Integer.parseInt(scanner.nextLine());

     switch (choose) {
      case 0:
        i = orchestra.length;
        break;
      case 1:
        System.out.println("Enter flute brand: ");
        String FluteBrand = scanner.nextLine();
        orchestra[i] = new TraverseFlute(FluteBrand);
        break;
      case 2:
        System.out.println("Enter flute brand: ");
        String FluteBrand2 = scanner.nextLine();
        orchestra[i] = new StraightFlute(FluteBrand2);
        break;
      case 3:
        System.out.println("Enter piano brand: ");
        String PianoBrand = scanner.nextLine();
        orchestra[i] = new Piano(PianoBrand);
        break;
      case 4:
        System.out.println("Enter violin brand: ");
        String ViolinBrand = scanner.nextLine();
        orchestra[i] = new Violin(ViolinBrand);
        break;
     }
    }
    scanner.close();
    System.out.println("Orchestra Instruments:");
    for (MusicalInstrument instrument : orchestra) {
      if (instrument != null) {
        System.out.println(instrument.toString());
      }
    }
  }
}
