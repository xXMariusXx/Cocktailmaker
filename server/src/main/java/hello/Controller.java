package hello;

import javax.json.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    public void gibZutatenliste()
    {
        MySQLAccess mySQLAccess = new MySQLAccess();

        String sql = "SELECT * FROM zutaten";

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objectBuilder;

        try {
            PreparedStatement statement = mySQLAccess.connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id", resultSet.getInt("id"));
                objectBuilder.add("name", resultSet.getString("name"));
                objectBuilder.add("alkohol", resultSet.getInt("alkohol"));
                arrayBuilder.add(objectBuilder);
            }
            System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mySQLAccess.disconnect();
        }

        JsonArray jsonArray = arrayBuilder.build();
    }
}
