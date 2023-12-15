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
      input = input.toLowerCase();

      String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

      Integer firstDigit = null;
      Integer lastDigit = null;

      for (int i = 0; i < input.length(); i++) {
          if (firstDigit != null) {
              break;
          }
          if (Character.isDigit(input.charAt(i))) {
              firstDigit = Character.getNumericValue(input.charAt(i));
              continue;
          }
          for (int j = 0; j < numbers.length; j++) {
              if (input.startsWith(numbers[j], i)) {
                  firstDigit = j;
                  i += numbers[j].length() - 1;
                  break;
              }
          }
      }

      for (int i = input.length() - 1; i >= 0; i--) {
          if (lastDigit != null) {
              break;
          }
          if (Character.isDigit(input.charAt(i))) {
              lastDigit = Character.getNumericValue(input.charAt(i));
              continue;
          }
          for (int j = numbers.length - 1; j >= 0; j--) {
              if (i >= numbers[j].length() - 1 && input.substring(i - numbers[j].length() + 1, i + 1).equals(numbers[j])) {
                  lastDigit = j;
                  break;
              }
          }
      }

      if (firstDigit == null || lastDigit == null) {
          return 0;
      }

      return firstDigit * 10 + lastDigit;
  }
}
