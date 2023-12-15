import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    List<String> inputs = new ArrayList<>();

    try {
      File file = new File("inputs.txt");
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
          inputs.add(scanner.nextLine());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return;
    }

    int sum = 0;
    for (String input : inputs) {
        sum += findCalValue(input);
    }
    System.out.println(sum);
  }

  private static int findCalValue(String input) {
      String digits = input.replaceAll("[^0-9]", "");
      if (digits.length() == 0) return 0;
      int firstDigit = Character.getNumericValue(digits.charAt(0));
      int lastDigit = Character.getNumericValue(digits.charAt(digits.length() - 1));

      return firstDigit * 10 + lastDigit;
  }
}
