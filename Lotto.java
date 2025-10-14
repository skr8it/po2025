import java.util.Random;
import java.util.Arrays;
public class Lotto {
    public static void main(String[] args) {
        Random r= new Random();
        int [] numbers = new int[6];
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
        if (args.length !=6) {
            System.out.print("Wrong number of arguments");

        }
        else {
            int numsum = 0;
            for (int i = 0; i < 6; i++) {
                int number = Integer.parseInt(args[i]);
                if (numbers[i] == number) {
                    numsum++;
                }
            }
            System.out.println("Twoje liczby to: ");
            System.out.println(Arrays.toString(args));
            System.out.println("Wygrywające liczby to: ");
            System.out.println(Arrays.toString(numbers));
            System.out.println("Ilosc trafien to: " + numsum);
        }

    }
}