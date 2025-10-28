package animals;

public abstract class Animal {
    protected String name;
    protected int legs;

    // Konstruktor klasy bazowej
    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }

    // Metoda abstrakcyjna – każda klasa pochodna musi ją zaimplementować
    public abstract String getDescription();
    public int getLegs(){
        return legs;
    }
    public abstract void makeSound();
}

