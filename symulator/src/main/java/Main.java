import auto.*;

public class Main {
    public static void main(String[] args){
        Sprzeglo spring = new Sprzeglo("siglo",10,20,"siglo","siegboy");
        Skrzynia chest = new Skrzynia("chesti",100,200,"sigchests","boy",spring,6);
        Silnik engine = new Silnik("sigmik",100,200,"sigmaengine","sigmaboy",5000);
        Samochód samochod = new Samochód("SIGB0Y","SIEGAUTO",200,engine,chest);

        samochod.wlacz();
        System.out.println(samochod.getAktualnapozycja());
        spring.wcisnij();
        chest.zwiekszBieg();
        spring.zwolnij();
        chest.zwiekszBieg();
        spring.wcisnij();
        chest.zwiekszBieg();
        chest.zwiekszBieg();
        System.out.println(samochod.getAktualnapozycja());
        Pozycja cel = new Pozycja(100,200);
        samochod.przeniesc(cel);
        System.out.println(samochod.getAktualnapozycja());
        }
    }

