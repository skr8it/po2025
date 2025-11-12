package auto;

public class Pozycja {
    protected double x;
    protected double y;
    public Pozycja(double x,double y){
        this.x = x;
        this.y = y;
    }
    public void aktualizujPozycje(double deltaX,double deltaY){
        x=deltaX;
        y=deltaY;
    }
    public double[] getPozycja(){
        return new double[]{this.x, this.y};
    }
}
