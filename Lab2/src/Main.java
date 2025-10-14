import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        int[] numbers = new int[6];
        for (int i = 0; i <= 5; i++) {
            int randomInt = r.nextInt(49);
            randomInt++;
            int prev = randomInt;
            for (int j = 0; j < i; j++) {
                if (randomInt == numbers[j]) {
                    while (randomInt == prev) {
                        randomInt = r.nextInt(49);
                        randomInt++;
                    }
                }
            }
            numbers[i] = randomInt;
            System.out.print(randomInt);
            System.out.println();
        }
        System.out.println("Wygrywające liczby to: ");
        System.out.println(Arrays.toString(numbers));
        System.out.println("Rozpoczynam proces losowania liczb do trafienia.");
        long start = System.nanoTime();
        int numsum = 0;
        int repeatsum = 0;
        while (numsum != 6) {
            numsum = 0;
            int[] newnumbers = new int[6];
            repeatsum++;
            for (int i = 0; i <= 5; i++) {
                int newrandint = r.nextInt(49);
                newrandint++;
                int prev = newrandint;
                for (int j = 0; j < i; j++) {
                    if (newrandint == numbers[j]) {
                        while (newrandint == prev) {
                            newrandint = r.nextInt(49);
                            newrandint++;
                        }
                    }
                }
                newnumbers[i] = newrandint;

            }
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k <= 5; k++) {
                    if (newnumbers[j] == numbers[k]) {
                    numsum++;
                    }
                }
            }
        }
        long end = System.nanoTime();
        long timeElapsed = end - start;
        System.out.println("Gratulacje! Ilość powtórzeń do trafienia to " + repeatsum + ".");
        System.out.println("Czas wykonania to: " + timeElapsed/1000000);
    }
}