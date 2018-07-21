package hello;

public class Rezept {
    private int id;
    private String name;
    private String beschreibung;

    public Rezept(int id, String name, String beschreibung, Zutat[] zutaten, int[] mengen ) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
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

}
