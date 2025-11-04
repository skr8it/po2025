package auto;

public abstract class Komponent {
    private String producer;
    private String model;
    private int cena;
    private int waga;
    private String nazwa;
    public Komponent(String nazwa,int waga,int cena,String model,String producer){
        this.cena = cena;
        this.nazwa = nazwa;
        this.waga = waga;
        this.model = model;
        this.producer = producer;
    }
    public String getNazwa(){
        return nazwa;
    }
    public int getWaga(){
        return waga;
    }
    public int getCena(){
        return cena;
    }
    public String getProducer(){
        return producer;
    }
    public String getModel(){
        return model;
    }
}
