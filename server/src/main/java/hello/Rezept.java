package hello;

public class Rezept {
    private int id;
    private String name;
    private String beschreibung;

    public Rezept(int id, String name, String beschreibung) {
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
