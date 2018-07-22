package getraenkeServer;

import getraenkeServer.model.Rezept;
import getraenkeServer.model.Zutat;

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
                                    resultSet.getString("name"),0,
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
        ArrayList<Rezept> passendeRezepte = new ArrayList<>();

        return passendeRezepte;
    }


    public ArrayList<Zutat> gibZutatenFuerRezeptId(int id)
    {
        ArrayList<Zutat> list = new ArrayList<>();

        String sql = "SELECT zutat_id, menge, z.name, z.alkohol " +
                "FROM rezept_zutaten INNER JOIN zutaten z " +
                "ON rezept_zutaten.zutat_id = z.id WHERE rezept_id = " + Integer.toString(id);
        MySQLAccess mySQLAccess = new MySQLAccess();
        try {
            PreparedStatement statement = mySQLAccess.connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                boolean hatAlk = false;
                if (resultSet.getInt("alkohol") > 0)
                    hatAlk = true;
                list.add(new Zutat(resultSet.getInt("zutat_id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("menge"),
                                    hatAlk));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySQLAccess.disconnect();
        }

        return list;
    }

    public Rezept gibRezeptFuerId(int id) {
        String sql = "SELECT name, beschreibung FROM rezepte WHERE id = " + id;

        MySQLAccess mySQLAccess = new MySQLAccess();



        try {
            PreparedStatement statement = mySQLAccess.connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");
            String beschr = resultSet.getString("beschreibung");
            System.out.println(name);
            System.out.println(beschr);
            return new Rezept(id,   name,
                                    beschr,
                                    gibZutatenFuerRezeptId(id));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            mySQLAccess.disconnect();
        }
    }
}
