package hello;

public class Zutat {
    private int id;
    private String name;
    private int menge;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public void setAlkohol(boolean alkohol) {
        this.alkohol = alkohol;
    }

    private boolean alkohol;

    public Zutat() {
    }

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

    public int getMenge() {
        return menge;
    }
}
