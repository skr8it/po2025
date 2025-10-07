import java.util.Random;
public class Lotto {
    public static void main(String[] args) { 
		Random r= new Random();
		numbers = new int[6];
		for (int i = 0; i <= 5; i++) {
			int randomInt = r.nextInt(49);
			randomInt++;
			int prev = randomInt;
			for (int j = 0; j < i; j++) {
			if (randomInt == numbers[j]) {
				while ( randomInt == prev) {
					int randomInt = r.nextInt(49);
					randomInt++;
				}
			}
			}
			}
			numbers[i] = randomInt;
            System.out.print(randomInt);
			System.out.println();
		
        }
} 