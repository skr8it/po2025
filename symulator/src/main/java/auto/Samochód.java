package auto;

public class Samochód {
    private Pozycja aktualnapozycja;
    private boolean StanWlaczenia;
    private String nrRejest;
    private String model;
    private int predkosc_max;
    private Silnik silnik;
    private Skrzynia skrzynia;
    private int predkosc;
    public Samochód(String nrRejest, String model, int predkosc_max,Silnik silnik, Skrzynia skrzynia){
        this.silnik = silnik;
        this.model = model;
        this.nrRejest = nrRejest;
        this.predkosc_max=predkosc_max;
        this.skrzynia=skrzynia;
        this.StanWlaczenia = false;
        this.aktualnapozycja = new Pozycja(0,0);
    }
    public void wlacz(){
        silnik.uruchom();
    }
    public void wylacz(){
        silnik.zatrzymaj();
        skrzynia.setAktualny_bieg(0);
    }
    public int getWaga(){
        return silnik.getWaga() + skrzynia.getWaga() + skrzynia.getSprzeglo().getWaga();
    }
    public float getAktPredkosc(){
        return skrzynia.getAktualne_przelozenie()*predkosc_max;
    }
    public String getAktualnapozycja(){
        return (String.valueOf(this.aktualnapozycja.x) + ',' + String.valueOf(this.aktualnapozycja.y));
    }
    public float getPredkosc(){
        return this.skrzynia.getAktualne_przelozenie()*this.predkosc_max;
    }
    public void przeniesc(Pozycja pos){
        float speed = this.skrzynia.getAktualne_przelozenie()*this.predkosc_max;
        int krok = 0;
        while (aktualnapozycja.x != pos.x && aktualnapozycja.y != pos.y){
            krok+=1;
            aktualnapozycja.aktualizujPozycje(
                    Math.min(aktualnapozycja.x  + speed * Math.signum(pos.getPozycja()[0]), pos.getPozycja()[0]),
                    Math.min(aktualnapozycja.y + speed * Math.signum(pos.getPozycja()[1]), pos.getPozycja()[1])
        );
        System.out.println("Krok numer " + String.valueOf(krok));
        }
    }


}
