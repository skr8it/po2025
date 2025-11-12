package auto;

public class Silnik extends Komponent {
    private int max_obroty;
    private int obroty;
    public Silnik(String nazwa,int waga,int cena,String model, String producer,int max_obroty){
        super(nazwa,waga,cena,model,producer);
        this.obroty=0;
        this.max_obroty=max_obroty;

    }
    public void uruchom(){
        obroty = 1000;
    }
    public void zatrzymaj(){
        obroty = 0;
    }
    public void zwiekszObroty(int ilosc){
        if (obroty+ilosc >= max_obroty){
            obroty = max_obroty;
        }
        else{
            obroty = obroty + ilosc;
        }

    }
    public void zmniejszObroty(int ilosc){
        if (obroty-ilosc <= 0){
            obroty = 0;
        }
        else{
            obroty = obroty - ilosc;
        }
    }
}
