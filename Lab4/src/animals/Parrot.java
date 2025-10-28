package animals;


public class Parrot extends Animal {
    public Parrot() {
        super("Parrot", 2);
    }

    @Override
    public String getDescription() {
        return "This is a parrot with " + legs + " legs.";
    }
    public void makeSound(){
        System.out.println("A parrot whistles.");
    }
}
