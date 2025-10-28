package zadania;
import animals.*;
import java.util.Random;
public class Zoo {
    Random r = new Random();
    Animal[] animals = new Animal[100];
    public Zoo(){
        for (int i = 0; i < 100; i++){
            int randomAnimal = r.nextInt(3);
            switch(randomAnimal){
                case 0:
                    animals[i] = new Parrot();
                    break;
                case 1:
                    animals[i]= new Dog();
                    break;
                case 2:
                    animals[i] = new Snake();
                    break;
            }
        }
    }
    public int getlegsum(){
        int sum = 0;
        for (int i=0;i<100;i++){
            sum = sum + animals[i].getLegs();
        }
        return sum;
    }

    public Animal[] getAnimals() {
        return animals;
    }
}
