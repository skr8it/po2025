package animals;

public class Snake extends Animal{
    public Snake() {
        super("Snake", 0);
    }

    @Override
    public String getDescription() {
        return "This is a Snake with " + legs + " legs.";
    }

    @Override
    public void makeSound() {
        System.out.println("A snake hisses.");
    }
}
