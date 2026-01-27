package auto;

public class Skrzynia extends Komponent {
    private int aktualny_bieg ;
    private int ilosc_biegow;
    private float aktualne_przelozenie;
    private Sprzeglo sprzeglo;
    public Skrzynia(String nazwa,int waga,int cena,String model, String producer,Sprzeglo sprzeglo,int ilosc_biegow) {
        super(nazwa, waga, cena, model, producer);
        this.ilosc_biegow=ilosc_biegow;
        this.sprzeglo = sprzeglo;
        this.aktualny_bieg=0;
    }
    public void zwiekszBieg(){
        if (aktualny_bieg < ilosc_biegow && sprzeglo.isStanSprzegla()){
            aktualny_bieg = (aktualny_bieg + 1);}
    }
    public void zmniejszBieg(){
        if (aktualny_bieg != 0 && sprzeglo.isStanSprzegla()) {
            aktualny_bieg = (aktualny_bieg - 1);
        }
        }
    public int getAktualny_bieg(){
        return aktualny_bieg;
    }
    public void setAktualny_bieg(int y){
        this.aktualny_bieg=y;
    }
    public float getAktualne_przelozenie(){
        return (1f/this.ilosc_biegow)*this.aktualny_bieg;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }
}
