package hello.model;

public class Zutat {
    private final long id;
    private final String name;

    public Zutat(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
