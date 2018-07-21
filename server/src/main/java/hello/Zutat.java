package hello;

public class Zutat {
    private int id;
    private String name;
    private boolean alkohol;

    public Zutat(int id, String name, boolean alkohol)
    {
        this.id = id;
        this.alkohol = alkohol;
        this.name = name;
    }

    public int getId(){return id;}

    public String getName(){return name;}

    public boolean isAlkohol() {
        return alkohol;
    }
}
