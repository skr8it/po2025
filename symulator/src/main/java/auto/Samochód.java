package auto;

public class Samochód {
    private Pozycja aktualnapozycja;
    private boolean StanWlaczenia;
    private String nrRejest;
    private String model;
    private int predkosc_max;
    private Silnik silnik;
    private Skrzynia skrzynia;
    public void wlacz(){
        silnik.uruchom();
    }
    public void wylacz(){
        silnik.zatrzymaj();
        //skrzynia.getAktualny_bieg()=0;
    }
    public int getWaga(){
        return silnik.getWaga() + skrzynia.getWaga() + skrzynia.getSprzeglo().getWaga();
    }
    public float getAktPredkosc(){
        return skrzynia.getAktualne_przelozenie()*predkosc_max;
    }
    public Pozycja getAktualnapozycja(){
        return aktualnapozycja;
    }


}
