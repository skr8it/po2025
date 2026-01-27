package auto;

import java.util.ArrayList;
import java.util.List;

public class Samochód extends Thread {
    public Pozycja aktualnapozycja;
    public boolean StanWlaczenia;
    public String nrRejest;
    public String model;
    public int predkosc_max;
    public Silnik silnik;
    public Skrzynia skrzynia;

    private Pozycja cel = null; // Pole reprezentujące cel podróży
    private List<Listener> listeners = new ArrayList<>(); // Lista subskrybentów wzorca Obserwator

    public Samochód(String nrRejest, String model, int predkosc_max, Silnik silnik, Skrzynia skrzynia) {
        this.silnik = silnik;
        this.model = model;
        this.nrRejest = nrRejest;
        this.predkosc_max = predkosc_max;
        this.skrzynia = skrzynia;
        this.StanWlaczenia = false;
        this.aktualnapozycja = new Pozycja(0, 0); // Inicjalizacja aktualnej pozycji

        this.start(); // Wątek startuje pod sam koniec konstruktora
    }

    // Metoda ustawiająca cel podróży
    public void jedzDo(Pozycja nowaPozycja) {
        this.cel = nowaPozycja;
    }

    @Override
    public void run() {
        double deltat = 0.1; // Krok czasowy 100 ms

        while (true) {
            if (cel != null) {
                // 1. Obliczamy odległość do celu
                double dystans = Math.sqrt(Math.pow(cel.x - aktualnapozycja.x, 2) +
                        Math.pow(cel.y - aktualnapozycja.y, 2));

                // 2. Obliczamy, jaki dystans auto pokona w tej klatce (prędkość * czas)
                double krok = getAktPredkosc() * deltat;

                // 3. Sprawdzamy, czy nie przeskoczymy celu
                if (dystans <= krok) {
                    // Jesteśmy blisko celu - "doskakujemy" idealnie w punkt i zatrzymujemy się
                    aktualnapozycja.x = cel.x;
                    aktualnapozycja.y = cel.y;
                    cel = null; // Cel osiągnięty, nullujemy go
                    System.out.println("Dotarłem do celu!");
                } else {
                    // Jesteśmy daleko - wykonujemy normalny ruch w stronę celu
                    double dx = krok * (cel.x - aktualnapozycja.x) / dystans;
                    double dy = krok * (cel.y - aktualnapozycja.y) / dystans;

                    aktualnapozycja.x += dx;
                    aktualnapozycja.y += dy;
                }

                // Powiadomienie słuchaczy o zmianie (zawsze, gdy się ruszamy lub dotarliśmy)
                notifyListeners();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    // Implementacja wzorca Obserwator
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }

    public void wlacz() {
        silnik.uruchom();
    }

    public void wylacz() {
        silnik.zatrzymaj();
        skrzynia.setAktualny_bieg(0);
    }

    public int getWaga() {
        return silnik.getWaga() + skrzynia.getWaga() + skrzynia.getSprzeglo().getWaga();
    }

    public float getAktPredkosc() {
        return Math.min(skrzynia.getAktualne_przelozenie() * predkosc_max, predkosc_max);
    }

    public Pozycja getPozycja() {
        return aktualnapozycja;
    }

    public String getAktualnapozycjaStr() {
        return (String.valueOf(this.aktualnapozycja.x) + ',' + String.valueOf(this.aktualnapozycja.y));
    }
    @Override
    public String toString() {
        return model + " (" + nrRejest + ")";
    }
}