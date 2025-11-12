package auto;

public class Sprzeglo extends Komponent {
    private boolean StanSprzegla;
    public Sprzeglo(String nazwa,int waga,int cena,String model, String producer) {
        super(nazwa, waga, cena, model, producer);
    }
    public void wcisnij(){
        StanSprzegla=true;
    }
    public void zwolnij(){
        StanSprzegla=false;
    }
    public boolean isStanSprzegla(){
        return StanSprzegla;
    }
}
