package getraenkeServer.model;

import java.util.List;

public class Rezept {
    private int id;
    private String name;
    private String beschreibung;
    private List<Zutat> zutaten;

    public Rezept(int id, String name, String beschreibung, List<Zutat> zutaten ) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.zutaten = zutaten;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public List<Zutat> getZutaten() { return zutaten; }
}
