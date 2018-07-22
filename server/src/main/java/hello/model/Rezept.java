package hello.model;

import java.util.List;

public class Rezept {
    private final long id;
    List<Zutat> zutaten;

    public Rezept(long id, List<Zutat> zutaten) {
        this.id = id;
        this.zutaten = zutaten;
    }
}
