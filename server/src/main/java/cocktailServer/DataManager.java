package cocktailServer;

import cocktailServer.model.Rezept;
import cocktailServer.model.SearchType;
import cocktailServer.model.Zutat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DataManager {
    public static ArrayList<Zutat> gibZutatenliste()
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

    public static List<Rezept> gibRezepteNamenIDfuerZutaten(SearchType searchType, Zutat[] zutaten)
    {
        if (zutaten.length < 1) return null;

        String zutatenSet = Arrays.stream(zutaten)
                            .map(zutat -> Integer.toString(zutat.getId()) + ", ")
                            .reduce(String::concat).get();
        zutatenSet = zutatenSet.substring(0, zutatenSet.length() - 2);

        /*String sql =    "SELECT r.* FROM rezept_zutaten " +
                        "INNER JOIN rezepte r on rezept_zutaten.rezept_id = r.id " +
                        "WHERE zutat_id IN (" + zutatenSet + ") " +
                        "GROUP BY rezept_id HAVING count(*) <= " + zutaten.length +
                        " LIMIT 50";*/
        /*String sql =    "SELECT rezept_zutaten.rezept_id FROM rezept_zutaten " +
                        "LEFT JOIN (SELECT id FROM zutaten WHERE id IN ("+zutatenSet+")) z on rezept_zutaten.zutat_id = z.id " +
                        "GROUP BY rezept_zutaten.rezept_id " +
                        "HAVING count(z.id) = (SELECT COUNT(*) FROM zutaten WHERE id IN (" + zutatenSet + ")) LIMIT 10";
        */
        String sql = "SELECT * FROM rezept_zutaten";

        MySQLAccess mySQLAccess = new MySQLAccess();

        List<Rezept> rezepte = new ArrayList<>();
        try {
            PreparedStatement statement = mySQLAccess.connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            Map<Integer, List<Integer>> badIdea = new HashMap<>();

            while (resultSet.next()) {
                int rezept_id = resultSet.getInt("rezept_id");
                int zutat_id = resultSet.getInt("zutat_id");

                if (badIdea.containsKey(rezept_id)) {
                    badIdea.get(rezept_id).add(zutat_id);
                } else {
                    badIdea.put(rezept_id, new ArrayList<>(zutat_id));
                }
            }

            List<Integer> zutatenIDsGesucht = Arrays.stream(zutaten)
                                                .map(Zutat::getId)
                                                .collect(Collectors.toList());

            badIdea = badIdea.entrySet()
                    .stream()
                    .filter(integerListEntry ->
                                    zutatenIDsGesucht.containsAll(integerListEntry.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            badIdea.forEach((rezept_id, zutaten_ids) -> rezepte.add(gibRezeptFuerId(rezept_id)));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rezepte;
    }


    public static ArrayList<Zutat> gibZutatenFuerRezeptId(int id)
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

    public static Rezept gibRezeptFuerId(int id) {
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
