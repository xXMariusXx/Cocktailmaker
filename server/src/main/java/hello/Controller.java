package hello;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    public ArrayList<Zutat> gibZutatenliste()
    {
        MySQLAccess mySQLAccess = new MySQLAccess();

        String sql = "SELECT * FROM zutaten";

        ArrayList<Zutat> zutaten = new ArrayList<>();

        try {
            PreparedStatement statement = mySQLAccess.connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                boolean hatAlk = false;
                if (resultSet.getInt("alkohol") > 0)
                    hatAlk = true;
                zutaten.add(new Zutat(resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    hatAlk));

            }
            System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySQLAccess.disconnect();
        }

        return zutaten;
    }

    public ArrayList gibRezepteNamenIDfuerZutaten(Zutat[] zutaten)
    {
        ArrayList passendeRezepte = new ArrayList();

        for ()
        passendeRezepte.add(new Rezept())
    }


    public Rezept gibRezeptdetailsDurchId(int id)
    {
        String sql = "SELECT rezept from rezept";
    }
}
