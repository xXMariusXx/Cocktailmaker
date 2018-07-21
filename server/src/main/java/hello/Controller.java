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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySQLAccess.disconnect();
        }

        return zutaten;
    }

    public ArrayList gibRezepteNamenIDfuerZutaten(Zutat[] zutaten)
    {
        ArrayList<Rezept> passendeRezepte = new ArrayList<Rezept>();

        return passendeRezepte;
    }


    public ArrayList<String> gibRezeptdetailsDurchId(int id)
    {
        ArrayList<String> list = new ArrayList();
        String s = "";
        String sql = "SELECT name, beschreibung FROM rezepte WHERE id = " + id;

        MySQLAccess mySQLAccess = new MySQLAccess();
        try {
            PreparedStatement statement = mySQLAccess.connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            s = s + resultSet.getString("name");
            s = s + resultSet.getString("beschreibung");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySQLAccess.disconnect();
        }

        list.add(s);
        s="";

        sql = "SELECT zutat_id, menge, z.name, z.alkohol " +
                "FROM rezept_zutaten INNER JOIN zutaten z " +
                "ON rezept_zutaten.zutat_id = z.id WHERE rezept_id = " + id;
        mySQLAccess = new MySQLAccess();
        try {
            PreparedStatement statement = mySQLAccess.connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                s = s + resultSet.getString("zutat.id");
                s = s + resultSet.getString("menge");
                s = s + resultSet.getString("z.name");
                s = s + resultSet.getString("z.alkohol");
                list.add(s);
                s = "";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySQLAccess.disconnect();
        }

        return list;

    }
}
