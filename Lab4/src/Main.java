import zadania.Zoo;
import animals.Animal;
public class Main {
    public static void main(String[] args){
        Zoo krakow = new Zoo();
        krakow.getlegsum();
        Animal[] zwierzeta = krakow.getAnimals();
        for (int i=0;i< zwierzeta.length;i++){
            Animal zwierzak = zwierzeta[i];
            zwierzak.getDescription();
            zwierzak.getLegs();
            zwierzak.makeSound();
        }
    }
}
