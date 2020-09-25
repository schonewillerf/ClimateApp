package hu.adsd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MaterialDatabaseParser
{
    public ArrayList<Material> getMaterialList()
    {
        try
        {
            return parseDatabase();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private ArrayList<Material> parseDatabase() throws Exception
    {
        ArrayList<Material> materialArrayList = new ArrayList<>();

        try
        {
            Connection c = null;
            Statement stmt = null;

            Class.forName( "org.sqlite.JDBC" );
            c = DriverManager.getConnection( "jdbc:sqlite:src/main/resources/database_sqlite.db" );

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM products;" );

            while ( rs.next() )
            {
                int id = rs.getInt( "id" );
                String name = rs.getString( "name" );
                String carbon = rs.getString( "co2" );
                int quantity = rs.getInt( "units" );


                Material material = new Material( id, name, carbon, CirculationType.NEW, quantity );
                materialArrayList.add( material );
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        return materialArrayList;
    }
}
