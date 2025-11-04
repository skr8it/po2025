package auto;

public class Silnik extends Komponent {
    private int max_obroty;
    private int obroty;
    public Silnik(){
        super("sigmik",1,2,"sigmasilnik","sigmaboy");
    }
    public void uruchom(){
        obroty = 1000;
    }
    public void zatrzymaj(){
        obroty = 0;
    }
    public int zwiekszObroty(int ilosc){
        return (obroty+ilosc);
    }
    public int zmniejszObroty(int ilosc){
        return (obroty-ilosc);
    }
}
