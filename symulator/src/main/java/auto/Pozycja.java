package auto;

public class Pozycja {
    protected double x;
    private double y;
    public void aktualizujPozycje(double deltaX,double deltaY){
        x=deltaX;
        y=deltaY;
    }
    public String getPozycja(){
        return (String.valueOf(x) + ' ' + String.valueOf(y));
    }
}
