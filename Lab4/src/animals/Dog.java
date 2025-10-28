package animals;

public class Dog extends Animal{
    public Dog(){
        super("Dog",4);
    }

    @Override
    public String getDescription() {
        return("This is a " +name + " with " +String.valueOf(legs) +" legs");
    }

    @Override
    public void makeSound() {
        System.out.println("A dog barks.");
    }
}


