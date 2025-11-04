package auto;

public class Skrzynia extends Komponent {
    private int aktualny_bieg =1;
    private int ilosc_biegow;
    private float aktualne_przelozenie;
    private Sprzeglo sprzeglo;
    public Skrzynia(){
        super("chest",1,2,"chesticle","sigmaboy");
    }
    public int zwiekszBieg(){
        if (aktualny_bieg < ilosc_biegow){
            return (aktualny_bieg+1);}
        return aktualny_bieg;
    }
    public int zmniejszBieg(){
        if (aktualny_bieg != 0) {
            return (aktualny_bieg - 1);
        }
        return aktualny_bieg;
        }
    public int getAktualny_bieg(){
        return aktualny_bieg;
    }
    public float getAktualne_przelozenie(){
        return aktualne_przelozenie;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }
}
