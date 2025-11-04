package auto;

public class Sprzeglo extends Komponent {
    private boolean StanSprzegla;
    public Sprzeglo(){
        super("smeglo",1,2,"XX132","sigmaboy");
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
