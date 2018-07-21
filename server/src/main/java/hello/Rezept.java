package hello;

public class Rezept {
    private int id;
    private String name;
    private Zutat[] zutaten;
    private int[] mengen;
    private String beschreibung;

    public Rezept(int id, String name, Zutat[] zutaten, int[] mengen, String beschreibung) {
        this.id = id;
        this.name = name;
        this.zutaten = zutaten;
        this.mengen = mengen;
        this.beschreibung = beschreibung;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Zutat[] getZutaten() {
        return zutaten;
    }

    public int[] getMengen() {
        return mengen;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

}
