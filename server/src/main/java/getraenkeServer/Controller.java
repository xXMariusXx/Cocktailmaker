package getraenkeServer;

import getraenkeServer.model.Rezept;
import getraenkeServer.model.SearchType;
import getraenkeServer.model.Zutat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;


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

    public List<Rezept> gibRezepteNamenIDfuerZutaten(SearchType searchType, Zutat[] zutaten)
    {
        if (zutaten.length < 1) return null;

        String zutatenSet = Arrays.stream(zutaten)
                            .map(zutat -> Integer.toString(zutat.getId()) + ", ")
                            .reduce(String::concat).get();
        zutatenSet = zutatenSet.substring(0, zutatenSet.length() - 2);

        String sql =    "SELECT r.* FROM rezept_zutaten " +
                        "INNER JOIN rezepte r on rezept_zutaten.rezept_id = r.id " +
                        "WHERE zutat_id IN (" + zutatenSet + ") " +
                        "GROUP BY rezept_id HAVING count(*) <= " + zutaten.length +
                        " LIMIT 50";

        MySQLAccess mySQLAccess = new MySQLAccess();

        List<Rezept> rezepte = new ArrayList<>();
        try {
            PreparedStatement statement = mySQLAccess.connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int rezeptId = resultSet.getInt("r.id");
                rezepte.add(new Rezept(rezeptId,
                                       resultSet.getString("r.name"),
                                       resultSet.getString("r.beschreibung"),
                                       gibZutatenFuerRezeptId(rezeptId)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rezepte;
    }


    public ArrayList<Zutat> gibZutatenFuerRezeptId(int id)
    {
        ArrayList<Zutat> list = new ArrayList<>();

        String sql = "SELECT zutat_id, menge, z.name, z.alkohol " +
                "FROM rezept_zutaten INNER JOIN zutaten z " +
                "ON rezept_zutaten.zutat_id = z.id WHERE rezept_id = " + id;
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

            return new Rezept(id,   resultSet.getString("name"),
                                    resultSet.getString("beschreibung"),
                                    gibZutatenFuerRezeptId(id));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            mySQLAccess.disconnect();
        }
    }
}
